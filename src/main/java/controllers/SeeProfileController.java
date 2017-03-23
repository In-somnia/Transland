package controllers;


import model.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/SeeProfileController")
public class SeeProfileController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(SeeProfileController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        long id = (long) request.getSession().getAttribute("authorized");

        if ((id != -1) && (request.getSession().getAttribute("authorized") != null)) {
            LOG.info("Authorization check is completed");
            boolean isRemoved = (boolean) request.getSession().getAttribute("removed");
            if (!isRemoved) {
                LOG.info("Removal check is completed");
                String receivedId = request.getParameter("id");
                @SuppressWarnings("unchecked") List<Translator> pageResults =
                        (ArrayList)request.getSession().getAttribute("thisPageTranslators");

                for (Translator translator : pageResults) {

                    if (String.valueOf(translator.getId()).equals(receivedId)) {
                        request.getSession().setAttribute("colleagueProfile", translator);
                        request.getRequestDispatcher("/WEB-INF/colleagueProfile.jsp").forward(request, response);
                    }
                }
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
