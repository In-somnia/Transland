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


@WebServlet("/TranslatorProfileController")
public class TranslatorProfileController extends HttpServlet {

    private DaoManager daoManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoManager = (DaoManager)config.getServletContext().getAttribute("DaoManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        long id = (long)request.getSession().getAttribute("authorized");
        if ((id != -1) && (request.getSession().getAttribute("authorized") != null)) {
            Translator translator = daoManager.getTranslatorDao().get(id);
            request.getSession().setAttribute("userData", translator);
            request.getRequestDispatcher("/WEB-INF/translatorProfilePage.jsp").forward(request, response);
        }
        request.getRequestDispatcher("authorizationPage.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
