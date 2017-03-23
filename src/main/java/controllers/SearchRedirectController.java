package controllers;

import dao.DaoManager;
import model.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Redirects the user to a search page showing the information about all registered translators
 * (before some search criteria have been chosen)
 */
@WebServlet("/SearchRedirectController")
public class SearchRedirectController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(SearchRedirectController.class);
    private DaoManager daoManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoManager = (DaoManager)config.getServletContext().getAttribute("DaoManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        long id = (long) request.getSession().getAttribute("authorized");

        if ((id != -1) && (request.getSession().getAttribute("authorized") != null)) {
            LOG.info("Authorization check is completed");
            boolean isRemoved = (boolean) request.getSession().getAttribute("removed");
            if (!isRemoved) {
                LOG.info("Removal check is completed");
                int currentPage = 1;
                List<Long> allTranslatorIds = daoManager.getTranslatorDao().getAllButSelfId(id);
                List<Translator> currentPageTranslators = new ArrayList<>();

                long numberOfPages = daoManager.getTranslatorDao().pageCounter(allTranslatorIds);

                request.getSession().setAttribute("pages", numberOfPages);
                request.getSession().setAttribute("currentPage", currentPage);

                for (int i = 0; i < 4; i++) {
                    long queriedId = allTranslatorIds.get(i);
                    Translator translator = daoManager.getTranslatorDao().get(queriedId);
                    currentPageTranslators.add(translator);
                }

                request.getSession().setAttribute("thisPageTranslators", currentPageTranslators);
                request.getRequestDispatcher("WEB-INF/searchPage.jsp").forward(request, response);
            } else {
                LOG.warn("This user's page has been removed. Redirecting to a removed page...");
                request.getRequestDispatcher("WEB-INF/removedPage.jsp").forward(request, response);
            }
        } else {
            LOG.warn("Current user is not authorized. Access denied");
            request.getRequestDispatcher("authorizationPage.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
