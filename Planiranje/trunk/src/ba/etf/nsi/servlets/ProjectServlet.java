package ba.etf.nsi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ba.etf.nsi.dao.JdbcObjectProjectDAO;
import ba.etf.nsi.models.Project;

/**
 * Servlet implementation class Project
 */
@WebServlet("/projects")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        ArrayList<Project> projects = JdbcObjectProjectDAO.getProjects(); // Obtain all projects.
	        request.setAttribute("projects", projects); // Store projects in request scope.
	        request.setAttribute("total", projects.size());
	        request.getRequestDispatcher("/Projects.jsp").forward(request, response); // Forward to JSP page to display them in HTML drop-down list.
	    } catch (Exception e) {
	        throw new ServletException("Retrieving projects failed!", e);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		//out.println("<script type=\"text/javascript\">");
		//out.println("alert('nesto');");
		//out.println("</script>");
		String id=request.getParameter("id").trim();
		String name=request.getParameter("name");
		String description=request.getParameter("description");
		String startOn=request.getParameter("startOn");
		String endOn=request.getParameter("endOn");
		String sCode=request.getParameter("code");
		int code=Integer.parseInt(sCode.trim());
		double budget=Double.parseDouble(request.getParameter("budget"));
		try {
			JdbcObjectProjectDAO.updateProjects(id, name, code, budget, description, startOn, endOn);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Update has been successfully stored to database');");
			out.println("</script>");
			response.sendRedirect("projects");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Error: Unable to store updates to database.');");
			out.println("</script>");
		}
	}

}
