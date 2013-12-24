package ba.etf.nsi.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ba.etf.nsi.dao.JdbcObjectProjectDAO;

public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context=sce.getServletContext();
        String dburl=context.getInitParameter("dbUrl");
        String dbusername=context.getInitParameter("dbUserName");
        String dbpassword=context.getInitParameter("dbPassword");

        try {
			JdbcObjectProjectDAO.connect(dburl, dbusername, dbpassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Connection Establised.........");
    }

    public void contextDestroyed(ServletContextEvent sce) {
    	try {
			JdbcObjectProjectDAO.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}