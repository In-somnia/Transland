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
import java.util.List;

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
                List<Long> allTranslatorIds = daoManager.getTranslatorDao().getAllButSelfId(id);
                List<Translator> currentPageTranslators = new ArrayList<>();

                if (pageNumber != 0) {
                    currentPageTranslators.clear();
                    request.getSession().setAttribute("currentPage", pageNumber);
                    for (int i = 0; i < resultsPerPage; i++) {

                        int colleagueId =  ((resultsPerPage*(pageNumber - 1)) + i);

                        if (colleagueId <= allTranslatorIds.size() - 1)
                        {
                            long queriedId = allTranslatorIds.get(colleagueId);
                            Translator translator = daoManager.getTranslatorDao().get(queriedId);
                            currentPageTranslators.add(translator);
                        }
                    }
                    request.getSession().setAttribute("thisPageTranslators", currentPageTranslators);
                    request.getRequestDispatcher("WEB-INF/searchPage.jsp").forward(request, response);
                } else {
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String middleName = request.getParameter("middleName");
                    String city = request.getParameter("city");
                    String cell = request.getParameter("cell");
                    String email = request.getParameter("email");


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
