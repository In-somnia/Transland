package listeners;

import dao.h2.H2DaoManager;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class Injector implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("DaoManager", new H2DaoManager());
    }
}
