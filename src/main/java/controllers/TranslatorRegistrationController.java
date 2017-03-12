package controllers;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/TranslatorRegistrationController")
public class TranslatorRegistrationController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName =  request.getSession().getAttribute("first-name").toString().trim();
        String lastName =  request.getSession().getAttribute("last-name").toString().trim();
        String middleName = request.getSession().getAttribute("middle-name").toString().trim();
        String city = request.getSession().getAttribute("city").toString().trim();
        String cell = request.getSession().getAttribute("cell").toString().trim();
        String email = request.getSession().getAttribute("email").toString().trim();




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
