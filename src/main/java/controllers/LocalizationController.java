package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;


@WebServlet("/LocalizationController")
public class LocalizationController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String language = request.getParameter("language");
        if (language.equals("en")) {
            Locale enLocale = Locale.ENGLISH;
            request.getSession().setAttribute("locale", enLocale);
        }
        else if (language.equals("ru")) {
            Locale ruLocale = new Locale("ru");
            request.getSession().setAttribute("locale", ruLocale);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
