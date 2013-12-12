package timetracker


import grails.converters.JSON;
import java.sql.ResultSet
import helper.dbHelper


class CategoryController {

    def index() { 
        timetracker.Category proj = new timetracker.Category();
        def map = [category: proj];
        render(view:"index", model: map);}
    
    def addcat() { 
        timetracker.Category usr = new timetracker.Category();
        def map = [category: usr];
        render(view:"categoryForm", model: map);
    }
    
     def addCategory()
    {
        timetracker.Category proj = new timetracker.Category();
        proj.properties = params;
        
        proj.InsertCategory();
        redirect(action: "index")
    }
    
    def getCategories() {
        def data = [:]
        def rows = []
        data['page'] = 1
        
        int i = 0;
        ResultSet rs = dbHelper.ExecuteQuery("SELECT * FROM \"code_value\"");
        while(rs.next())
        {
            def value = [:]
            value.id = rs.getObject("code_value_id") as String
            value.type = rs.getObject("code_value_type")
            value.name = rs.getObject("code_value_label")
            
            
            rows << [cell: value];
            i++;
        }
        data['total'] = i;
        data['rows'] = rows
        
        render data as JSON
    }
    
    
    def deleteCategory()
    {
        String uid = params.CategoryID;
        timetracker.Category proj = new timetracker.Category();
        proj.DeleteCategory(uid);
        
        redirect(action: "index")
    }
}
