package ba.etf.nsi.servlets;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ba.etf.nsi.dao.JdbcObjectProjectDAO;
import ba.etf.nsi.models.Project;
import ba.etf.nsi.models.Resource;

/**
 * Servlet implementation class ResourceServlet
 */
@WebServlet("/ResourceServlet")
public class ResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResourceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 try {
		
		/**/
		if(request.getParameter("add")!=null) {
		    String name=request.getParameter("name");
		    double quantity=Double.parseDouble(request.getParameter("quantity"));
		    String project_id=request.getParameter("projectId").trim();
		    if(project_id==null) throw new Exception("Niste oznacili projekat");
		     JdbcObjectProjectDAO.addResource(name, quantity, project_id);
		 }
		else if(request.getParameter("update")!=null) {
		  
		    String resourceId=request.getParameter("resourceId");
		    String name=request.getParameter("name");
		    double quantity=Double.parseDouble(request.getParameter("quantity"));
		    int i=JdbcObjectProjectDAO.updateResource(resourceId,name, quantity);
		    /*
            <scripting-invalid>true</scripting-invalid>
           */
		}
		else if(request.getParameter("delete")!=null) {
			String projectId=request.getParameter("projectId");
		    String resourceId=request.getParameter("resourceId");
		   	JdbcObjectProjectDAO.deleteResource(projectId,resourceId);
		}
		else {
			     ArrayList<Project> projects = JdbcObjectProjectDAO.getProjects(); // Obtain all projects.
			     request.setAttribute("projects", projects); // Store projects in request scope.
			     request.setAttribute("total", projects.size());
				 String projectId=request.getParameter("projects");
				 request.setAttribute("projectId", projectId);
			    //ArrayList<Resource> resources = JdbcObjectProjectDAO.getResources("30222299-9909-4458-1164-666922380421"); // Obtain all resources from specific project.
				 ArrayList<Resource> resources = JdbcObjectProjectDAO.getResources(projectId); // Obtain all resources from specific project.
				    
				 request.setAttribute("resources", resources); // Store resources in request scope.
			     //request.setAttribute("total", resources.size());
			     // Moramo se dogovoriti gdje ce se ovo prikazivati
			     
		}
		request.getRequestDispatcher("/Resources.jsp").forward(request, response); // Forward to JSP page to display them in HTML drop-down list.
		 
		 
	} catch (Exception e) {
	        throw new ServletException("Adding, Retrieving or Updating resource failed!", e);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
