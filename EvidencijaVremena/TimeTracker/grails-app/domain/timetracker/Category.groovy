package timetracker

import helper.dbHelper
import java.sql.ResultSet
import java.util.UUID

class Category {

    static constraints = {
    }
    public int CategoryID;
    public int CategoryType;
    public String Name;
    
    public int GetID()
    {
        String query = "SELECT code_value_id FROM \"code_value\" ORDER BY code_value_id DESC LIMIT 1;";
        ResultSet rs = dbHelper.ExecuteQuery(query);
        rs.next();
        int id=rs.getInt(1);
        return id;
    }
    
    public void InsertCategory()
    {
        int uuid =GetID()+1;
        
        String query = "INSERT INTO code_value(code_value_id, code_value_type, code_value_label)" +
        " VALUES ('"+uuid+"','1','" +Name+ "');";
        
        dbHelper.ExecuteQuery(query);
    }
    
    public void DeleteCategory(String uid)
    {
       
        String query = "DELETE FROM code_value WHERE code_value_id='" +uid+ "';";
        dbHelper.ExecuteQuery(query);
    } 
}
