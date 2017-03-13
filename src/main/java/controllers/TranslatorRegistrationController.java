package controllers;


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

    private H2DaoManager h2DaoManager;
    public static final String USER_ID = "id";

    @Override
    public void init(ServletConfig config) throws ServletException {
        h2DaoManager = (H2DaoManager)config.getServletContext().getAttribute("H2DaoManager");
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

        boolean[] validationList = new boolean[13];
        int counter = 0;

        if (!(firstName.isEmpty()) && firstName.length() >= 1 && firstName.length() <= 50)
        {
            validationList[counter] = true;
            counter++;
        }

        if (!(lastName.isEmpty()) && lastName.length() >= 1 && lastName.length() <= 50)
        {
            validationList[counter] = true;
            counter++;
        }

        if (!(middleName.isEmpty()) && middleName.length() >= 1 && middleName.length() <= 50)
        {
            validationList[counter] = true;
            counter++;
        }

        if (!(city.isEmpty()) && city.length() >= 1 && city.length() <= 50)
        {
            validationList[counter] = true;
            counter++;
        }

        if (!(cell.isEmpty()) && cell.length() >= 1 && cell.length() <= 50)
        {
            validationList[counter] = true;
            counter++;
        }

        if (!(email.isEmpty()) && email.length() >= 1 && email.length() <= 100)
        {
            validationList[counter] = true;
            counter++;
        }

        if (!(university.isEmpty()) && university.length() >= 1 && university.length() <= 50)
        {
            validationList[counter] = true;
            counter++;
        }

        if (!(department.isEmpty()) && department.length() >= 1 && department.length() <= 50)
        {
            validationList[counter] = true;
            counter++;
        }

        if (educationType == EducationForm.valueOf("FULL_TIME") || educationType == EducationForm.valueOf("PART_TIME"))
        {
            validationList[counter] = true;
            counter++;
        }

        if (graduationYear >= 1950 && graduationYear <= 2021)
        {
            validationList[counter] = true;
            counter++;
        }

        if (!(experience.isEmpty()) && department.length() >= 1 && department.length() <= 20)
        {
            validationList[counter] = true;
            counter++;
        }

        if (info.length() <= 250)
        {
            validationList[counter] = true;
            counter++;
        }
        if (!(password.isEmpty() && password.length() >= 6 && password.length() <= 50))
        {
            validationList[counter] = true;
        }

        for (boolean checkResult : validationList)
        {
            if (!checkResult)
            {
                request.getRequestDispatcher("translatorRegistrationPage.jsp").forward(request, response);
            }
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

        long id = h2DaoManager.getTranslatorDao().create(translator);
        request.setAttribute(USER_ID, id);
        request.getRequestDispatcher("authorizationPage.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
