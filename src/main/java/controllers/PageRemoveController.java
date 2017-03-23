package controllers;


import dao.DaoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/PageRemoveController")
public class PageRemoveController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(PageRemoveController.class);
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
            boolean isRemoved = daoManager.getTranslatorDao().removePage(id);
            request.getSession().setAttribute("removed", isRemoved);

            LOG.info("This page has been removed successfully");
            request.getRequestDispatcher("WEB-INF/removedPage.jsp").forward(request, response);
        } else {
            LOG.warn("Current user is not authorized. Access denied");
            request.getRequestDispatcher("authorizationPage.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
