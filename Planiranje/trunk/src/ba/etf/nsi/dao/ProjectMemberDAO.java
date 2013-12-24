package ba.etf.nsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import ba.etf.nsi.models.ProjectMember;
import ba.etf.nsi.models.Resource;
import ba.etf.nsi.models.Team;
import ba.etf.nsi.models.User;
import ba.etf.nsi.servlets.TeamMemberServlet;

public class ProjectMemberDAO {

	
	public static Connection c=JdbcObjectProjectDAO.c;
	
	
	
	public static void addProjectMember(String name,String project_id) throws Exception {
		
		   try {
			   String team_id = UUID.randomUUID().toString().replaceAll("-", "");
			   String team_project_id=UUID.randomUUID().toString().replaceAll("-", "");
			   // insert into resource table
			   PreparedStatement st1 = c.prepareStatement("INSERT INTO team (team_id,name) VALUES (?,?)"); 
			   // insert into resource_project table
			   PreparedStatement st2 = c.prepareStatement("INSERT INTO team_project (team_project_id,team, project) VALUES (?,?,?)");
			   
			   st1.setString(1, team_id);
			   st1.setString(2, name);
			   
			  
			   st2.setString(1, team_project_id);
			   st2.setString(2, project_id);
			   st2.setString(3, team_id);
			  
			   st1.executeUpdate();
			   st2.executeUpdate();
		   }
			catch(Exception e) {
				throw new Exception ("Unable to insert resource data to database. Error: " +e.getMessage());
			}
		}
	
	public static ArrayList<ProjectMember> getProjectMembers(String teamId) throws Exception {
		ArrayList<ProjectMember> projectMembers = new ArrayList<ProjectMember>();
		try
		{
			PreparedStatement st = c.prepareStatement("SELECT pm.project_member_id, pm.role, pm.planned_hours_per_task, pm.price_per_hour,pm.task, pm.user_id, pm.team, u.first_name, u.last_name, u.email FROM project_member pm join \"user\" u on pm.user_id = u.user_id WHERE pm.team=?::uuid");
			
			st.setString(1, teamId);
			ResultSet rs = st.executeQuery();
			 rs.toString();
			
					while (rs.next()) {
						User u = new User();
						ProjectMember pm = new ProjectMember();
						pm.setProject_member_id(rs.getString(1));
						pm.setPlanned_hours_per_task(rs.getDouble("planned_hours_per_task"));
						pm.setPrice_per_hour(rs.getDouble("price_per_hour"));
						pm.setTask_id(rs.getString("task"));
						u.setUser_id(rs.getString("user_id")); 
						pm.setTeam_id(rs.getString("team"));
						u.setFirst_name(rs.getString("first_name"));
						u.setLast_name(rs.getString("last_name"));
						u.setEmail(rs.getString("email"));
						pm.setRole(rs.getString("role")); 
						
						pm.setUser(u);
					
						projectMembers.add(pm);
					}
		}
		catch (Exception e) {
			throw new Exception ("Unable to retrieve list of resources from database. Error: " + e.getMessage());
		}
		
		return projectMembers;
	}
	
	
	
	
	
}
