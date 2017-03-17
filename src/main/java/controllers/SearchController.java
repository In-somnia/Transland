package controllers;


import dao.DaoManager;
import model.Translator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {

    private DaoManager daoManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoManager = (DaoManager)config.getServletContext().getAttribute("DaoManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        long id = (long) request.getSession().getAttribute("authorized");
        if ((id != -1) && (request.getSession().getAttribute("authorized") != null)) {
            boolean isRemoved = (boolean) request.getSession().getAttribute("removed");
            if (!isRemoved) {
                int resultsPerPage = 4;
                int pageNumber = Integer.parseInt(request.getParameter("page"));
                request.getSession().setAttribute("currentPage", pageNumber);
                List<Long> allTranslatorIds = daoManager.getTranslatorDao().getAllButSelfId(id);
                List<Translator> curPageTranslators;
                List<Translator> foundColleagues = new ArrayList<>();
                if (pageNumber != 0) {
                    curPageTranslators  = daoManager.getTranslatorDao().getCurrentPageTranslators
                            (resultsPerPage, pageNumber, allTranslatorIds);

                    request.getSession().setAttribute("thisPageTranslators", curPageTranslators);
                    request.getRequestDispatcher("WEB-INF/searchPage.jsp").forward(request, response);

                } else {
                    Map<Object, Object> paramMap = new HashMap<>();
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String middleName = request.getParameter("middleName");
                    String city = request.getParameter("city");
                    String cell = request.getParameter("cell");
                    String email = request.getParameter("email");

                    if (!firstName.isEmpty() && firstName.length() > 0 && firstName.length() < 21) {
                        paramMap.put("first_name", "\'" + firstName + "\'");
                    }
                    if (!lastName.isEmpty() && lastName.length() > 0 && lastName.length() < 21) {
                        paramMap.put("last_name", "\'" + lastName + "\'");
                    }
                    if (!middleName.isEmpty() && middleName.length() > 0 && middleName.length() < 21) {
                        paramMap.put("patronymic", "\'" + middleName + "\'");
                    }
                    if (!city.isEmpty() && city.length() > 0 && city.length() < 21) {
                        paramMap.put("city", "\'" + city + "\'");
                    }
                    if (!cell.isEmpty() && cell.length() > 0 && cell.length() < 17) {
                        paramMap.put("cell", "\'" + cell + "\'");
                    }
                    if (!email.isEmpty() && email.length() > 0 && email.length() < 31) {
                        paramMap.put("email", "\'" + email + "\'");
                    }
                    List<Long> foundIds = daoManager.getTranslatorDao().findColleaguesInDb(paramMap);
                    int currentPage = 1;

                    for (int i = 0; i < resultsPerPage; i++) {
                        int colleagueId =  (resultsPerPage*pageNumber + i);
                        if (colleagueId <= foundIds.size() - 1)
                        {
                            long queriedId = foundIds.get(colleagueId);
                            Translator translator = daoManager.getTranslatorDao().get(queriedId);
                            foundColleagues.add(translator);
                        }
                    }
                    request.getSession().setAttribute("currentPage", currentPage);
                    long numberOfPages = daoManager.getTranslatorDao().pageCounter(foundIds);
                    request.getSession().setAttribute("pages", numberOfPages);
                    request.getSession().setAttribute("thisPageTranslators", foundColleagues);
                    request.getRequestDispatcher("WEB-INF/searchPage.jsp").forward(request, response);
                }

            } else {
                request.getRequestDispatcher("WEB-INF/removedPage.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("authorizationPage.jsp").forward(request, response);
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
