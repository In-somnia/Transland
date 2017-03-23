package controllers;


import dao.DaoManager;
import model.Education;
import model.EducationForm;
import model.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/TranslatorRegistrationController")
public class TranslatorRegistrationController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(TranslatorRegistrationController.class);
    private DaoManager daoManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoManager = (DaoManager)config.getServletContext().getAttribute("DaoManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        LOG.info("Validating data...");
        String firstName = request.getParameter("firstName").trim().replace("<", "&lt;").replace(">", "&gt;");
        String lastName = request.getParameter("lastName").trim().replace("<", "&lt;").replace(">", "&gt;");
        String middleName = request.getParameter("middleName").trim().replace("<", "&lt;").replace(">", "&gt;");
        String city = request.getParameter("city").trim().replace("<", "&lt;").replace(">", "&gt;");
        String cell = request.getParameter("cell").trim().replace("<", "&lt;");
        String email = request.getParameter("email").trim().replace("<", "&lt;").replace(">", "&gt;");
        String university = request.getParameter("university").trim().replace("<", "&lt;").replace(">", "&gt;");
        String department = request.getParameter("department").trim().replace("<", "&lt;").replace(">", "&gt;");
        EducationForm educationType = EducationForm.valueOf(request.getParameter("edForm"));
        int graduationYear = Integer.parseInt(request.getParameter("gradYear"));
        String experience = request.getParameter("experience").trim().replace("<", "&lt;").replace(">", "&gt;");
        String info = request.getParameter("info").trim().replace("<", "&lt;").replace(">", "&gt;");
        String password = request.getParameter("password").trim();

        boolean validationCheck = false;
        if (firstName.isEmpty() || firstName.length() < 1 || firstName.length() > 21) {
            validationCheck = true;
        }
        if (lastName.isEmpty() || lastName.length() < 1 || lastName.length() > 21) {
            validationCheck = true;
        }
        if (middleName.isEmpty() || middleName.length() < 1 || middleName.length() > 21) {
            validationCheck = true;
        }
        if (city.isEmpty() || city.length() < 1 || city.length() > 21) {
            validationCheck = true;
        }
        if (cell.isEmpty() || cell.length() < 1 || cell.length() > 17) {
            validationCheck = true;
        }
        if (email.isEmpty() || email.length() < 1 || email.length() > 31) {
            validationCheck = true;
        }
        if (university.isEmpty() || university.length() < 1 || university.length() > 51) {
            validationCheck = true;
        }
        if (department.isEmpty() || department.length() < 1 || department.length() > 51) {
            validationCheck = true;
        }
        if (graduationYear < 1949 || graduationYear > 2022) {
            validationCheck = true;
        }
        if (experience.isEmpty() || experience.length() < 1 || experience.length() > 21) {
            validationCheck = true;
        }
        if (info.length() > 150) {
            validationCheck = true;
        }
        if (password.isEmpty() || password.length() < 6 || password.length() > 31) {
            validationCheck = true;
        }
        if (!validationCheck) {
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
            translator.setIsRemoved(false);

            long id = daoManager.getTranslatorDao().create(translator);
            request.setAttribute("id", id);

            LOG.info("Validation completed. Registration is successful");
            request.getRequestDispatcher("authorizationPage.jsp").forward(request, response);
        } else {
            LOG.warn("Invalid registration data. Redirecting to translator registration page...");
            request.getRequestDispatcher("translatorRegistrationForm.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
