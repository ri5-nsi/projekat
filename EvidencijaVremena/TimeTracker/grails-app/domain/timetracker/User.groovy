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
        } catch(Exception ex){
            //TODO add logging logic
        }
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
    }
    public void UpdateUserData()
    {
        String query = "UPDATE \"user\" SET first_name='" +FirstName+ "', last_name='" +LastName+ "', email='" +Email+ "' WHERE user_id='" +UserID+ "';";
        dbHelper.ExecuteQuery(query);
        
    }
}
