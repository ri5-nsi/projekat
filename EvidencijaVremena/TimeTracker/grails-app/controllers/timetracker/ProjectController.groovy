package timetracker

import grails.converters.JSON;
import java.sql.ResultSet
import helper.dbHelper

class ProjectController {

    def beforeInterceptor = [action:this.&auth]
    
    private auth() {
        if(!session.user) {
          redirect(controller:"login", action:"index")
          return false
        }
        
        if(params.action =="addProject" || params.action =="addproj")
        {
            if(!session.user.AddProject)
            {
                redirect(controller:"login", action:"index")
                return false 
            }
        }
        
        if(params.action == "editProject" || params.action == "deleteProject")
        {
            if(!session.user.EditProject)
            {
                redirect(controller:"login", action:"index")
                return false 
            }
        }
      }
    
    def index() { 
        timetracker.Project proj = new timetracker.Project();
        def map = [project: proj];
        render(view:"projects", model: map);
    }
    
    def addproj()
    {
        timetracker.Project proj = new timetracker.Project();
        List<timetracker.User> usrs = new ArrayList<timetracker.User>();
        
        ResultSet rs = dbHelper.ExecuteQuery("SELECT * FROM \"user\";");

        if(rs!=null)
            while(rs.next())
            {
                timetracker.User usr = new timetracker.User();
                usr.PopulateUser(rs);
                usrs.add(usr);
            }
        
        def map = [project: proj, user:usrs];
        render(view:"projectForm", model: map); 
    }
    
    def addProject()
    {
        timetracker.Project proj = new timetracker.Project();
        proj.properties = params;
        
        proj.InsertProject();
        redirect(action: "index")
    }
    
    def editProject(){
        timetracker.Project proj = new timetracker.Project();
        List<timetracker.User> usrs = new ArrayList<timetracker.User>();
        
        ResultSet rs = dbHelper.ExecuteQuery("SELECT * FROM \"user\";");

        if(rs!=null)
            while(rs.next())
            {
                timetracker.User usr = new timetracker.User();
                usr.PopulateUser(rs);
                usrs.add(usr);
            }
        
        def map = [project: proj, user:usrs];
        render(view:"editProject", model: map); 
    }
    
    def getProjects() {
        def data = [:]
        def rows = []
        data['page'] = 1
        
        int i = 0;
        ResultSet rs = dbHelper.ExecuteQuery("SELECT * FROM \"project\"");
        while(rs.next())
        {
            def value = [:]
            value.id = rs.getObject("project_id") as String
            value.name = rs.getObject("name")
            value.description = rs.getObject("description")
            value.status = ''
            value.actions = "<!--<a href=\"editProject?projectID="+value.id+"\" >Edit</a> --><a href=\"deleteProject?projectID="+value.id+"\" >Delete</a>"
            
            rows << [cell: value];
            i++;
        }
        data['total'] = i;
        data['rows'] = rows
        
        render data as JSON
    }

    def deleteProject()
    {
        String uid = params.projectID;
        timetracker.Project proj = new timetracker.Project();
        proj.DeleteProject(uid);
        
        redirect(action: "index")
    }
}
