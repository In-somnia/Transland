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


@WebServlet("/SearchRedirectController")
public class SearchRedirectController extends HttpServlet {

    private DaoManager daoManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoManager = (DaoManager)config.getServletContext().getAttribute("DaoManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = (long) request.getSession().getAttribute("authorized");

        if ((id != -1) && (request.getSession().getAttribute("authorized") != null)) {

            boolean isRemoved = (boolean) request.getSession().getAttribute("removed");
            if (!isRemoved) {
                List<Long> allTranslatorIds = daoManager.getTranslatorDao().getAll();
                long numberOfPages = daoManager.getTranslatorDao().pageCounter(allTranslatorIds);
                request.getSession().setAttribute("pages", numberOfPages);

                List<Translator> currentPageTranslators = new ArrayList<>();

                for (int i = 0; i < 4; i++) {
                    Translator translator = daoManager.getTranslatorDao().get(allTranslatorIds.get(i));
                    currentPageTranslators.add(translator);
                }
                request.getSession().setAttribute("thisPageTranslators", currentPageTranslators);
                request.getRequestDispatcher("WEB-INF/searchPage.jsp").forward(request, response);
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
