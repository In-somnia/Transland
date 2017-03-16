package controllers;


import dao.DaoManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authorizationController")
public class AuthorizationController extends HttpServlet {

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

        String login = req.getParameter("email");
        String password = req.getParameter("password");
        boolean invalidDataCheck = false;

        if (login.isEmpty() || login.length() < 1 || login.length() > 30)
        {
            invalidDataCheck = true;
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 30)
        {
            invalidDataCheck = true;
        }

        if (invalidDataCheck)
        {
            req.getRequestDispatcher("authorizationPage.jsp").forward(req, resp);
        }

        long userId = daoManager.getAuthorizationDao().checkCredentials(login, password);

        if (userId != -1) {

            req.getSession().setAttribute("authorized", userId);
            boolean isPageRemoved = daoManager.getTranslatorDao().checkIsRemoved(userId);
            req.getSession().setAttribute("removed", isPageRemoved);
            req.getRequestDispatcher("/TranslatorProfileController").forward(req, resp);

        } else {
            req.getRequestDispatcher("authorizationPage.jsp").forward(req, resp);
        }
    }
}
