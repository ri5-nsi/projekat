package timetracker

import helper.dbHelper
import java.sql.ResultSet
import java.util.UUID

class User {
    
    static constraints = {
    }
        
    public String UserID;
    public String FirstName;
    public  String LastName;
    public  String Username;
    public  String Password;
    public  String RepeatPassword;
    public  String Email;
    public  int Status;
    public  String UserStatus;
    public  Boolean AddUser;
    public  Boolean EditUser;
    public  Boolean AddProject;
    public  Boolean EditProject;
    public List<String> Roles;
    
    public String GetFullName()
    {
        return FirstName + " " + LastName;
    }
    
    public String toString() {
        return GetFullName();
    }
    
    public User CreateUser()
    {
        User u = new User();
        u.UserID = 1;
        u.FirstName = "FName";
        u.LastName = "LName";
        return u;
    }
    
    public Boolean ValidateUsername()
    {
        String query = "SELECT user_id FROM \"user\" WHERE username='"+Username+"'";
        ResultSet rs = dbHelper.ExecuteQuery(query);
        return !rs.next();
    }
    
    public void InsertUser()
    {
        String uuid = UUID.randomUUID() as String

        String query = "INSERT INTO \"user\"(user_id, first_name, last_name, email, username, password)" +
        " VALUES ('" +uuid+ "', '" +FirstName+ "', '" +LastName+ "', '" +Email+ "', '" +Username+ "', '" +Password+ "');";

        dbHelper.ExecuteQuery(query);
        
        AddRoles(uuid);
    }
    
    public void LoadUser(String uid)
    {
        try{
            ResultSet rs = dbHelper.ExecuteQuery("SELECT * FROM \"user\" WHERE user_id='"+uid+"'");

            if(rs==null)
                throw new NullPointerException();
            
            if(!rs.next())
                return;
                
            PopulateUser(rs);
            LoadRoles(uid);
        } catch(Exception ex){
            //TODO add logging logic
        }
    }
    
    public void LoadRoles(String uid)
    {
        try{
            Roles = new ArrayList<String>();
            
            ResultSet rs = 
            dbHelper.ExecuteQuery("SELECT r.role_name, r.application_name FROM user_in_role as uir LEFT JOIN role as r ON uir.role = r.role_id WHERE uir.user_id = '"+uid+"'");
            
            if(rs==null)
                throw new NullPointerException();
        
            while(rs.next())
            {
                String role = rs.getObject("role_name");
                
                if(role.equals("Add User"))
                    AddUser = true;
                
                if(role.equals("Edit User"))
                    EditUser = true;
                
                if(role.equals("Add Project"))
                    AddProject = true;
                
                if(role.equals("Edit Project"))
                    EditProject = true;
                
                Roles.add(role);
            }
                
            PopulateUser(rs);
        } catch(Exception ex){
            //TODO add logging logic
        }
    }
    
    private void AddRoles(String uuid)
    {
        if(AddUser)
        {
            String addUserUUID = UUID.randomUUID() as String;
    
            String addUserQuery = "INSERT INTO user_in_role(user_in_role_id, user_id, role)";
            addUserQuery += "VALUES ('"+addUserUUID+"', '"+uuid+"', 'eaea4af5-d2cd-4d0d-860b-cf16d226cab8')";
            dbHelper.ExecuteQuery(addUserQuery);
        }
        
        if(EditUser)
        {
            String editUserUUID = UUID.randomUUID() as String;
    
            String addUserQuery = "INSERT INTO user_in_role(user_in_role_id, user_id, role)";
            addUserQuery += "VALUES ('"+editUserUUID+"', '"+uuid+"', '2b3a70b3-ab87-47cd-9695-21401ca78549')";
            dbHelper.ExecuteQuery(addUserQuery);
        }
        
        if(AddProject)
        {
            String addProjectUUID = UUID.randomUUID() as String;
    
            String addUserQuery = "INSERT INTO user_in_role(user_in_role_id, user_id, role)";
            addUserQuery += "VALUES ('"+addProjectUUID+"', '"+uuid+"', '6a9a3133-1dd8-4935-b129-7e8fa2b33934')";
            dbHelper.ExecuteQuery(addUserQuery);
        }
        
        if(EditProject)
        {
            String addProjectUUID = UUID.randomUUID() as String;
    
            String addUserQuery = "INSERT INTO user_in_role(user_in_role_id, user_id, role)";
            addUserQuery += "VALUES ('"+addProjectUUID+"', '"+uuid+"', 'b870718d-5137-48bb-af30-f89e1ec561d0')";
            dbHelper.ExecuteQuery(addUserQuery);
        }
    }
    
    private void UpdateRoles()
    {
        String query = "DELETE FROM user_in_role WHERE user_id='" +UserID+ "';";
        dbHelper.ExecuteQuery(query);
        
        AddRoles(UserID);
    }
    
    public void PopulateUser(ResultSet rs)
    {
        UserID = rs.getObject("user_id");
        FirstName = rs.getObject("first_name");
        LastName = rs.getObject("last_name");
        Email = rs.getObject("email");
        Username = rs.getObject("username");
        Password = rs.getObject("password");
    }
    
    public void DeleteUser(String uid)
    {
        String query = "DELETE FROM \"user\" WHERE user_id='" +uid+ "';";
        dbHelper.ExecuteQuery(query);
        
        query = "DELETE FROM user_in_role WHERE user_id='" +uid+ "';";
        dbHelper.ExecuteQuery(query);
    }
    public void UpdateUserData()
    {
        String query = "UPDATE \"user\" SET first_name='" +FirstName+ "', last_name='" +LastName+ "', email='" +Email+ "' WHERE user_id='" +UserID+ "';";
        dbHelper.ExecuteQuery(query);
        
        UpdateRoles();
    }
}
