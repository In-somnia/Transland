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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {

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


                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String middleName = request.getParameter("middleName");
                String city = request.getParameter("city");
                String cell = request.getParameter("cell");
                String email = request.getParameter("email");

            } else {
                request.getRequestDispatcher("WEB-INF/removedPage.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("authorizationPage.jsp").forward(request, response);
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
