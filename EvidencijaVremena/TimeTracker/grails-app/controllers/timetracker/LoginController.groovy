package timetracker

import helper.dbHelper
import java.sql.ResultSet

class LoginController {
      
    def index() { 
        render(view:"index");
    }
    
    def login() {
        String query = "SELECT * FROM \"user\" WHERE username='"+params.Username+"' AND password='"+params.Password+"'";
        ResultSet rs = dbHelper.ExecuteQuery(query);
        if(rs.next())
        {
            timetracker.User usr = new timetracker.User();
            usr.PopulateUser(rs);
            usr.LoadRoles(usr.UserID);
            session.user = usr;
            redirect(controller: "home", action: "index");
            return;
        }
        redirect(action: "index");
    }
    
    def logout(){
       session.user = null
       redirect(action: "index"); 
    }
}
