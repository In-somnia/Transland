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


@WebServlet("/PageEditController")
public class PageEditController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(PageEditController.class);
    private DaoManager daoManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoManager = (DaoManager)config.getServletContext().getAttribute("DaoManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        LOG.info("Validating data...");
        String city = request.getParameter("city").trim().replace("<", "&lt;").replace(">", "&gt;");
        String cell = request.getParameter("cell").trim();
        String university = request.getParameter("university").trim().replace("<", "&lt;").replace(">", "&gt;");
        String department = request.getParameter("department").trim().replace("<", "&lt;").replace(">", "&gt;");
        EducationForm educationType = EducationForm.valueOf(request.getParameter("edForm"));
        int graduationYear = Integer.parseInt(request.getParameter("gradYear"));
        String experience = request.getParameter("experience").trim().replace("<", "&lt;").replace(">", "&gt;");
        String info = request.getParameter("info").trim().replace("<", "&lt;").replace(">", "&gt;");
        boolean editDataCheck = false;

        if (city.isEmpty() || city.length() < 1 || city.length() > 21) {
            editDataCheck = true;
        }
        if (cell.isEmpty() || cell.length() < 1 || cell.length() > 17) {
            editDataCheck = true;
        }
        if (university.isEmpty() || university.length() < 1 || university.length() > 51) {
            editDataCheck = true;
        }
        if (department.isEmpty() || department.length() < 1 || department.length() > 51) {
            editDataCheck = true;
        }
        if (graduationYear < 1949 || graduationYear > 2022) {
            editDataCheck = true;
        }
        if (experience.isEmpty() || department.length() < 1 || department.length() > 20) {
            editDataCheck = true;
        }
        if (info.length() > 150) {
            editDataCheck = true;
        }
        if (!editDataCheck) {
            long id = (long) request.getSession().getAttribute("authorized");
            if ((id != -1) && (request.getSession().getAttribute("authorized") != null)) {
                boolean isRemoved = (boolean) request.getSession().getAttribute("removed");
                if (!isRemoved) {
                    Translator translator = daoManager.getTranslatorDao().get(id);
                    translator.setCity(city);
                    translator.setCell(cell);
                    Education education = translator.getEducation();
                    education.setUniversity(university);
                    education.setDepartment(department);
                    education.setEducationType(educationType);
                    education.setGraduationYear(graduationYear);
                    translator.setEducation(education);
                    translator.setExperience(experience);
                    translator.setInfo(info);

                    daoManager.getTranslatorDao().editTranslatorData(translator);
                    request.getSession().setAttribute("userData", translator);

                    LOG.info("Validation completed. User's data have been edited");
                    request.getRequestDispatcher("WEB-INF/translatorProfilePage.jsp").forward(request, response);
                } else {
                    LOG.warn("This user's page has been removed. Redirecting to a removed page...");
                    request.getRequestDispatcher("WEB-INF/removedPage.jsp").forward(request, response);
                }
            } else {
                LOG.warn("Current user is not authorized. Access denied");
                request.getRequestDispatcher("authorizationPage.jsp").forward(request, response);
            }
        } else {
            LOG.warn("Invalid registration data. Redirecting to edit page...");
            request.getRequestDispatcher("/WEB-INF/editPage.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
