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

@WebServlet("/authorizationController")
public class AuthorizationController extends HttpServlet {
    static final Logger LOG = LoggerFactory.getLogger(AuthorizationController.class);
    private DaoManager daoManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoManager = (DaoManager)config.getServletContext().getAttribute("DaoManager");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String login = req.getParameter("email").replace("<", "&lt;").replace(">", "&gt;");
        String password = req.getParameter("password").replace("<", "&lt;").replace(">", "&gt;");
        boolean invalidDataCheck = false;

        if (login.isEmpty() || login.length() < 1 || login.length() > 31)
        {
            invalidDataCheck = true;
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 31)
        {
            invalidDataCheck = true;
        }

        if (invalidDataCheck)
        {
            LOG.warn("Invalid authorization data");
            req.getRequestDispatcher("authorizationPage.jsp").forward(req, resp);
        }

        long userId = daoManager.getAuthorizationDao().checkCredentials(login, password);

        if (userId != -1) {

            req.getSession().setAttribute("authorized", userId);
            boolean isPageRemoved = daoManager.getTranslatorDao().checkIsRemoved(userId);
            req.getSession().setAttribute("removed", isPageRemoved);
            LOG.info("Authorization is completed");
            req.getRequestDispatcher("/TranslatorProfileController").forward(req, resp);

        } else {
            LOG.warn("Invalid login or password. Authorization denied");
            req.getRequestDispatcher("authorizationPage.jsp").forward(req, resp);
        }
    }
}
