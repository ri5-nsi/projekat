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
import ba.etf.nsi.models.Task;

/**
 * Servlet implementation class Project
 */
@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        ArrayList<Task> tasks = JdbcObjectProjectDAO.getTasks(); // Obtain all projects.
	        request.setAttribute("tasks", tasks); // Store projects in request scope.
	        request.setAttribute("total", tasks.size());
	        request.getRequestDispatcher("/Tasks.jsp").forward(request, response); // Forward to JSP page to display them in HTML drop-down list.
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
		String startOn=request.getParameter("startOn");
		String endOn=request.getParameter("endOn");
		String sCode=request.getParameter("code");
		int code=Integer.parseInt(sCode.trim());
		String sStatus=request.getParameter("status");
		int status=Integer.parseInt(sStatus.trim());
		double hoursPlaned=Double.parseDouble(request.getParameter("hoursPlaned"));
		try {
			JdbcObjectProjectDAO.updateTasks(id, name, startOn, endOn, hoursPlaned, status, code);
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
