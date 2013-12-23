package timetracker

import helper.dbHelper
import java.sql.ResultSet
import java.util.UUID

class Project {

    static constraints = {
    }
    
    public int ProjectID;
    public String Name;
    public String Description;
    public int Status; //ne upisuje se u tabeli project?
    public String ProjectStatus;
    public int Users;
    public int SubProject;
    public int Versions;
    public int Component;
    public List<String> ProUsers;
	
    
	public hasMany = [noteTime: NoteTime]
    public void InsertProject()
    {
        String uuid = UUID.randomUUID() as String

        String query = "INSERT INTO project(project_id, code, name, budget, description, start_on, end_on)" +
        " VALUES ('" +uuid+ "','0', '" +Name+ "', '0', '" +Description+ "', NOW(), NOW());";

        dbHelper.ExecuteQuery(query);
    }
    
    public void DeleteProject(String uid)
    {
        String query = "DELETE FROM project WHERE project_id='" +uid+ "';";
        dbHelper.ExecuteQuery(query);
    }
	
	public List<Project> GetListOfAllProjects(){
		
		//String query = "SELECT project_id, code, name, budget, description, start_on, end_on FROM \"project\" ;";
		//ResultSet rs = dbHelper.ExecuteQuery(query);
		ArrayList<Project> projects = new ArrayList<Project>();
		
		Project projecta = new Project();
		projecta.ProjectID = 1;
		projecta.Name = "Testni projekat";
		projecta.Description  = "Opis";
		projecta.Status = 1; //nemam podatka - kako je definisano?
		projecta.ProjectStatus = 1; //nemam podatka ...
		projecta.Users= 1;  //odakle ovo? broj usera na projektu iz tabele taskovi (count) ?
		projecta.SubProject = 1; // sta znaci?
		projecta.Versions = 1; //?
		projecta.Component = 1; //1
		projecta.ProUsers = new ArrayList<String>();
		
		projects.add(projecta);
		
		/*while(rs.next())
		{
			Project project = new Project();
			
			project.ProjectID = rs.getObject("project_id"); 
			project.Name = rs.getObject("name");
			project.Description  = rs.getObject("description");
			project.Status = 1; //nemam podatka - kako je definisano?
			project.ProjectStatus = 1; //nemam podatka ...
			project.Users= 1;  //odakle ovo? broj usera na projektu iz tabele taskovi (count) ?
			project.SubProject = 1; // sta znaci?
			project.Versions = 1; //?
			project.Component = 1; //1
			project.ProUsers = new ArrayList<String>();
			
			projects.add(project);
		}*/
		return projects;

	}
}
