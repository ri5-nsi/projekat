package ba.etf.nsi.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ba.etf.nsi.dao.JdbcObjectProjectDAO;
import ba.etf.nsi.dao.TeamDAO;
import ba.etf.nsi.models.Project;
import ba.etf.nsi.models.Team;

/**
 * Servlet implementation class Project
 */
@WebServlet("/teams")
public class ProjectTeams extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectTeams() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
			if(request.getParameter("add")!=null) {
				
				
	       }
	       else{
				
	    	   
	    	    ArrayList<Team> teams = TeamDAO.getTeams("2e3665bf-7a9f-448f-ae27-a303dc64b5dc"); // Obtain all teams from that project.
		        request.setAttribute("teams", teams); // Store projects in request scope.
		        request.setAttribute("total", teams.size());
		        request.getRequestDispatcher("/Teams.jsp").forward(request, response); // Forward to JSP page to display them in HTML drop-down list.
				
				
				
			}
	    } catch (Exception e) {
	        throw new ServletException("Retrieving projects failed!", e);
	    }
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
