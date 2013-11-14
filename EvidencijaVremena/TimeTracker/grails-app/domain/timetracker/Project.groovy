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

    public int Status;

    public String ProjectStatus;

    public int Users;

    public int SubProject;

    public int Versions;

    public int Component;

    public List<String> ProUsers;
    
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
}
