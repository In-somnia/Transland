package listeners;


import dao.DaoManager;
import dao.h2.H2DaoManager;
import dao.h2.H2TranslatorDao;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;


@WebListener
public class Injector implements ServletContextListener {

    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DaoManager h2DaoManager = new H2DaoManager(dataSource);

        sce.getServletContext().setAttribute("DaoManager", h2DaoManager);
    }
}
