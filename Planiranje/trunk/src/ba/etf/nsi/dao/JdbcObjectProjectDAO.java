package ba.etf.nsi.dao;
import ba.etf.nsi.models.Resource;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;
import ba.etf.nsi.models.Task;

import ba.etf.nsi.models.Project;

public class JdbcObjectProjectDAO {
	private static final String Driver= "org.postgresql.Driver";
	public static Connection c;
	
	public static void connect(String dburl, String dbuser, String dbpass) throws Exception {
		try {
			Class.forName(Driver);
			c = DriverManager.getConnection(dburl, dbuser, dbpass);
			} 
		catch (Exception e) {
			throw new Exception("Unable to connect to database. Error: "+e.getMessage());
			}
		}
	
	public static void disconnect() throws Exception {
		try {
			c.close();
			} 
		catch (Exception e) {
			throw new Exception("Unable to disconnect from database.");
			}
		}
	// Add a resource with guid :)
	public static void addResource(String name,double quantity,String projectId) throws Exception {
	   try {
		   UUID resource_id = UUID.randomUUID();
		   UUID resurce_project_id=UUID.randomUUID();
		   UUID project_id=java.util.UUID.fromString(projectId);
		   
		   // insert into resource table
		 //  CallableStatement st1 = c.prepareCall("{ ? = call CreateResourceOnProject(?::uuid,?::text,?::numeric) }");
		   
		  PreparedStatement st1 = c.prepareStatement("INSERT INTO resource (resource_id,name,quantity) VALUES (?,?,?)"); 
		  PreparedStatement st2 = c.prepareStatement("INSERT INTO resource_project (resurce_project_id,project, resource) VALUES (?,?,?)");
		
		   st1.setObject(1,resource_id);
		   st1.setString(2,name);
		   st1.setDouble(3,quantity);
		   
		   st2.setObject(1, resurce_project_id);
		   st2.setObject(2, project_id);
		   st2.setObject(3, resource_id);
		   
		   st1.execute();
		   st2.execute();
		   
		   st1.close();
		   st2.close();   
		   
	   }
		catch(Exception e) {
			throw new Exception ("Unable to insert resource data to database. Error: " +e.getMessage());
		}
	}
	// retrieving list of resources for specific project
	public static ArrayList<Resource> getResources(String projectId) throws Exception {
		ArrayList<Resource> resources = new ArrayList<Resource>();
		try
		{
			PreparedStatement st = c.prepareStatement("SELECT resource_id, name, quantity FROM resource r join resource_project rp ON r.resource_id=rp.resource WHERE rp.project=?::uuid");
			st.setString(1, projectId);
			ResultSet rs = st.executeQuery();
					while (rs.next()) {
						Resource r = new Resource();
						r.setId(rs.getString(1));
						r.setName(rs.getString(2));
						r.setQuantity(rs.getDouble(3));
						resources.add(r);
					}
		}
		catch (Exception e) {
			throw new Exception ("Unable to retrieve list of resources from database. Error: " + e.getMessage());
		}
		return resources;
	}
	// updating data for resource that belongs to specific project
	public static int updateResource(String resourceId,String name,double quantity) throws Exception {
	
		try
		{
			// UUID project_id=java.util.UUID.fromString(projectId);
			 UUID resource_id=java.util.UUID.fromString(resourceId);
			 
			 PreparedStatement st1 = c.prepareStatement("UPDATE resource SET name=?, quantity=? WHERE resource_id=?"); 
			 
			 st1.setString(1, name);
			 st1.setDouble(2, quantity);
			 st1.setObject(3,resource_id);
			 
			 st1.execute();
			 st1.close();
		}
		catch (Exception e) {
			throw new Exception ("Unable to update resource in database. Error: " + e.getMessage());
		}
		
		
		return 0;
	
	}
	
	// delete resource from specific project
	public static void deleteResource(String projectId,String resourceId) throws Exception {
		try
		{
						 
			 PreparedStatement st1 = c.prepareStatement("DELETE FROM resource_project WHERE resource=?::uuid AND project=?::uuid");
			 PreparedStatement st2 = c.prepareStatement("DELETE FROM resource WHERE resource_id=?::uuid"); 
			 
			 st1.setString(1, resourceId);
			 st1.setString(2, projectId);

             st2.setString(1,resourceId);
			 
			 st1.execute();
			 st2.execute();
			 
			 st1.close();
			 st2.close();
		}
		catch (Exception e) {
			 throw new Exception ("Unable to delete resource in database. Error: " + e.getMessage());
		}
	}
	
	
	
	public static void addProject (String name, double budget) throws Exception {
		try{
			String project_id = UUID.randomUUID().toString().replaceAll("-", "");
			PreparedStatement st = c.prepareStatement("INSERT INTO project (project_id,name, budget) VALUES (?,?,?)");
			st.setString(1, project_id);
			st.setString(2, name);
			st.setDouble(3, budget);
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception ("Unable to insert project data to database. Error: " +e.getMessage());
		}
	}
	
	public static ArrayList<Project> getProjects () throws Exception {
		ArrayList<Project> projects = new ArrayList<Project>();
		try
		{
			PreparedStatement st = c.prepareStatement("SELECT project_id, code, name, budget, description, start_on, end_on FROM project");
					ResultSet rs = st.executeQuery();
					while (rs.next()) {
						Project p = new Project();
						p.setId(rs.getString(1));
						p.setCode(rs.getInt(2));
						p.setName(rs.getString(3));
						p.setBudget(rs.getDouble(4));
						p.setDescription(rs.getString(5));
						p.setStartOn(rs.getString(6));
						p.setEndOn(rs.getString(7));
						projects.add(p);
					}
		}
		catch (Exception e) {
			throw new Exception ("Unable to retrieve list of projects from database. Error: " + e.getMessage());
		}
		return projects;
	}
	
	public static void updateProjects(String id, String name, int code, double budget, String description, String startOn, String endOn) throws Exception
	{
		try 
		{
			PreparedStatement st = c.prepareStatement("UPDATE project SET name=?, code=?, budget=?, description=?, start_on=?, end_on=? WHERE project_id=?::uuid");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			java.util.Date startDate=format.parse(startOn);
			Date sqlStartDate=new Date (startDate.getDate());
			
			java.util.Date endDate=format.parse(endOn);
			Date sqlEndDate=new Date (startDate.getDate());
			
			
			st.setString(1, name);
			st.setInt(2, code);
			st.setDouble(3, budget);
			st.setString(4, description);
			st.setDate(5, sqlStartDate);
			st.setDate(6, sqlEndDate);
			st.setString(7, id);
			st.execute();
		}
		catch (Exception e) {
			throw new Exception ("Unable to store updates to database. Error: " + e.getMessage());
		}
	}
	
	public static void addTask (String name, double hoursPlaned) throws Exception {
		try{
			String project_id = UUID.randomUUID().toString().replaceAll("-", "");
			PreparedStatement st = c.prepareStatement("INSERT INTO task (task_id, name, hours_planed) VALUES (?,?,?)");
			st.setString(1, project_id);
			st.setString(2, name);
			st.setDouble(3, hoursPlaned);
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception ("Unable to insert project data to database. Error: " +e.getMessage());
		}
	}
	
	public static ArrayList<Task> getTasks () throws Exception {
		ArrayList<Task> tasks = new ArrayList<Task>();
		try
		{
			PreparedStatement st = c.prepareStatement("SELECT task_id, name, start_on, end_on, hours_planed, status, code FROM task");
					ResultSet rs = st.executeQuery();
					while (rs.next()) {
						Task t = new Task();
						t.setId(rs.getString(1));
						t.setName(rs.getString(2));
						t.setStartOn(rs.getString(3));
						t.setEndOn(rs.getString(4));
						t.setHoursPlaned(rs.getDouble(5));
						t.setStatus(rs.getInt(6));
						t.setCode(rs.getInt(7));
						tasks.add(t);
					}
		}
		catch (Exception e) {
			throw new Exception ("Unable to retrieve list of tasks from database. Error: " + e.getMessage());
		}
		return tasks;
	}
	
	public static void updateTasks(String id, String name, String startOn, String endOn, double hoursPlaned, int status, int code) throws Exception
	{
		try 
		{
			PreparedStatement st = c.prepareStatement("UPDATE task SET name=?, start_on=?, end_on=?, hours_planed=?, status=?, code=?=? WHERE task_id=?::uuid");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			java.util.Date startDate=format.parse(startOn);
			Date sqlStartDate=new Date (startDate.getDate());
			
			java.util.Date endDate=format.parse(endOn);
			Date sqlEndDate=new Date (startDate.getDate());
			
			
			st.setString(1, name);
			st.setDate(2, sqlStartDate);
			st.setDate(3, sqlEndDate);
			st.setDouble(4, hoursPlaned);
			st.setDouble(5, status);
			st.setDouble(6, code);
			st.setString(7, id);
			st.execute();
		}
		catch (Exception e) {
			throw new Exception ("Unable to store updates to database. Error: " + e.getMessage());
		}
	}
}
