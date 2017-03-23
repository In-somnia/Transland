package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Puts the locale chosen by a user into his/her session
 * to provide the necessary interface until the user logs out
 */
@WebServlet("/LocalizationController")
public class LocalizationController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(LocalizationController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String language = request.getParameter("language");

        if (language.equals("en")) {
            Locale enLocale = Locale.ENGLISH;
            LOG.info("Interface language has been changed to English");
            request.getSession().setAttribute("locale", enLocale);
        }
        else if (language.equals("ru")) {
            Locale ruLocale = new Locale("ru");
            LOG.info("Interface language has been changed to Russian");
            request.getSession().setAttribute("locale", ruLocale);
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
