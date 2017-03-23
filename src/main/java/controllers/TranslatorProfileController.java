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


@WebServlet("/TranslatorProfileController")
public class TranslatorProfileController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(TranslatorProfileController.class);
    private DaoManager daoManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoManager = (DaoManager)config.getServletContext().getAttribute("DaoManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        long id = (long)request.getSession().getAttribute("authorized");

        if ((id != -1) && (request.getSession().getAttribute("authorized") != null)) {
            LOG.info("Authorization check is completed");
            boolean isRemoved = (boolean)request.getSession().getAttribute("removed");
            if (!isRemoved) {
                Translator translator = daoManager.getTranslatorDao().get(id);
                request.getSession().setAttribute("userData", translator);

                LOG.info("Removal check is completed");
                request.getRequestDispatcher("WEB-INF/translatorProfilePage.jsp").forward(request, response);
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
