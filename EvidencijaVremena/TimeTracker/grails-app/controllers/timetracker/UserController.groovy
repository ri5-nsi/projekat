package timetracker

import helper.dbHelper
import java.sql.ResultSet
import grails.converters.JSON;

class UserController {

    def beforeInterceptor = [action:this.&auth]
    
    private auth() {
        if(!session.user) {
          redirect(controller:"login", action:"index")
          return false
        }
        
        timetracker.User usr = (timetracker.User)session.user;
        
        if(params.action =="addusr" || params.action =="addUser")
        {
            if(!usr.AddUser)
            {
                redirect(controller:"login", action:"index")
                return false 
            }
        }
        
        if(params.action == "editUser" || params.action =="editUserData" ||
           params.action == "deleteUser")
        {
            if(!usr.EditUser)
            {
                redirect(controller:"login", action:"index")
                return false 
            }
        }
      }
    
    def index() { 
        timetracker.User usr = new timetracker.User();
        def map = [user: usr];
        render(view:"index", model: map);
    }
    
    def addusr() { 
        timetracker.User usr = new timetracker.User();
        def map = [user: usr];
        render(view:"form", model: map);
    }
    
    def addUser(){
        timetracker.User usr = new timetracker.User(UserID:1);
        usr.properties = params;
        
        if(usr.ValidateUsername())
        {
            usr.InsertUser();
            redirect(action: "index")
        }
        else
        {
            String errMessage = "Username already exist, please select another one!";
            def map1 = [errorMessage: errMessage, user: usr];
            render(view:"form", model: map1);
        }
    }
    
    def editUser(){
        String uid = params.userID;
        timetracker.User usr = new timetracker.User();
        usr.LoadUser(uid);
        
        def map = [user: usr];
        render(view:"editUser", model: map);
    }
    
    def editUserData(){
        timetracker.User usr = new timetracker.User();
        usr.properties = params;
        usr.UpdateUserData();
        
        redirect(action: "index")
    }
    
    def getUsers() {
        def data = [:]
        def rows = []
        data['page'] = 1
        
        int i = 0;
        ResultSet rs = dbHelper.ExecuteQuery("SELECT * FROM \"user\"");
        while(rs.next())
        {
            def value = [:]
            value.id = rs.getObject("user_id") as String
            value.firstName = rs.getObject("first_name")
            value.lastName = rs.getObject("last_name")
            value.username = rs.getObject("username")
            value.email = rs.getObject("email")
            value.status = ''
            value.actions = "<a href=\"editUser?userID="+value.id+"\" >Edit</a> <a href=\"deleteUser?userID="+value.id+"\" >Delete</a>"
            
            rows << [cell: value];
            i++;
        }
        data['total'] = i;
        data['rows'] = rows
        
        render data as JSON
    }
    
    def deleteUser()
    {
        String uid = params.userID;
        timetracker.User usr = new timetracker.User();
        usr.DeleteUser(uid);
        
        redirect(action: "index")
    }
}
