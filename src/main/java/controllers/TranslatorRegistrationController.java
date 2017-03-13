package controllers;


import dao.DaoManager;
import dao.h2.H2DaoManager;
import model.Education;
import model.EducationForm;
import model.Translator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/TranslatorRegistrationController")
public class TranslatorRegistrationController extends HttpServlet {

    private DaoManager daoManager;
    public static final String USER_ID = "id";

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoManager = (DaoManager)config.getServletContext().getAttribute("DaoManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName =  request.getParameter("firstName").trim();
        String lastName =  request.getParameter("lastName").trim();
        String middleName = request.getParameter("middleName").trim();
        String city = request.getParameter("city").trim();
        String cell = request.getParameter("cell").trim();
        String email = request.getParameter("email").trim();
        String university = request.getParameter("university").trim();
        String department = request.getParameter("department").trim();
        EducationForm educationType = EducationForm.valueOf(request.getParameter("edForm"));
        int graduationYear = Integer.parseInt(request.getParameter("gradYear"));
        String experience = request.getParameter("experience").trim();
        String info = request.getParameter("info").trim();
        String password = request.getParameter("password").trim();

        boolean validationCheck = false;


        if (firstName.isEmpty() || firstName.length() < 1 || firstName.length() > 50)
        {
            validationCheck = true;

        }

        if (firstName.isEmpty() || firstName.length() < 1 || firstName.length() > 50)
        {
            validationCheck = true;
        }

        if (firstName.isEmpty() || firstName.length() < 1 || firstName.length() > 50)
        {
            validationCheck = true;
        }

        if (!city.isEmpty() || city.length() < 1 || city.length() > 50)
        {
            validationCheck = true;
        }

        if (!cell.isEmpty() || cell.length() < 1 || cell.length() > 50)
        {
            validationCheck = true;
        }

        if (!email.isEmpty() || email.length() < 1 && email.length() > 100)
        {
            validationCheck = true;
        }

        if (!university.isEmpty() || university.length() < 1 || university.length() > 50)
        {
            validationCheck = true;
        }

        if (!department.isEmpty() || department.length() < 1 || department.length() > 50)
        {
            validationCheck = true;
        }

        if (graduationYear < 1950 || graduationYear > 2021)
        {
            validationCheck = true;
        }

        if (experience.isEmpty() || department.length() < 1 || department.length() > 20)
        {
            validationCheck = true;
        }

        if (info.length() > 250)
        {
            validationCheck = true;
        }
        if (password.isEmpty() || password.length() < 6 || password.length() > 50)
        {
            validationCheck = true;
        }


            if (!validationCheck)
            {
                request.getRequestDispatcher("translatorRegistrationPage.jsp").forward(request, response);
            }


        Translator translator = new Translator();
        translator.setFirstName(firstName);
        translator.setLastName(lastName);
        translator.setPatronymic(middleName);
        translator.setIsTranslator(true);
        translator.setCity(city);
        translator.setCell(cell);
        translator.setEmail(email);

        Education education = new Education();

        education.setUniversity(university);
        education.setDepartment(department);
        education.setEducationType(educationType);
        education.setGraduationYear(graduationYear);

        translator.setEducation(education);
        translator.setExperience(experience);
        translator.setInfo(info);
        translator.setPassword(password);

        long id = daoManager.getTranslatorDao().create(translator);
        request.setAttribute(USER_ID, id);
        request.getRequestDispatcher("authorizationPage.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
