package ba.etf.nsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import ba.etf.nsi.models.Resource;
import ba.etf.nsi.models.Team;
import ba.etf.nsi.models.User;

public class TeamDAO {

	
	public static Connection c=JdbcObjectProjectDAO.c;
	
	
	
	public static void addTeam(String name,String project_id) throws Exception {
		
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
	
	public static ArrayList<Team> getTeams(String projectId) throws Exception {
		ArrayList<Team> teams = new ArrayList<Team>();
		try
		{
			PreparedStatement st = c.prepareStatement("SELECT team_id, name FROM  team t join team_project tp ON t.team_id=tp.team WHERE tp.project=?::uuid");
			
			st.setString(1, projectId);
			ResultSet rs = st.executeQuery();
			 rs.toString();
			
					while (rs.next()) {
						Team t = new Team();
						t.setTeam_id(rs.getString(1));
						t.setName(rs.getString(2));
					
						teams.add(t);
					}
		}
		catch (Exception e) {
			throw new Exception ("Unable to retrieve list of resources from database. Error: " + e.getMessage());
		}
		
		return teams;
	}
	
	
	
	
	
}
