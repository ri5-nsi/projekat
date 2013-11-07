package timetracker

class UserController {

    def index() { 
        timetracker.User usr = new timetracker.User();
        def map = [user: usr];
        render(view:"form", model: map);
    }
    
    def addUser(){
    
    }
    
    def editUser(){
        timetracker.User usr = new timetracker.User();
        def map = [user: usr];
        render(view:"editUser", model: map);
    }
}
