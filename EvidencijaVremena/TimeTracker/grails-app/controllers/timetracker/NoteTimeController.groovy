package timetracker

import java.util.Formatter.DateTime;
import helper.dbHelper
import java.sql.ResultSet
import grails.converters.JSON;
import timetracker.User;

class NoteTimeController {

	def index() {
		//timetracker.NoteTime nTime = new timetracker.NoteTime();
		//nTime.test = new  Date();		
		//def map = [noteTime: nTime];
		
		//render(view:"index", model: map);}
		/*Project proj = new Project();
		
		def projekti = proj.GetListOfAllProjects();
		//def projekti = ProjectController.getProjects();
		
		return [projekti: projekti]
		*/
		timetracker.User usr = new timetracker.User();
        def map = [user: usr];
        render(view:"index", model: map);
		}
	
	def addnote(){
		//timetracker.NoteTime nTime = new timetracker.NoteTime();
		//nTime.test = new  Date();
		
		render(view:"index")
	}
	
	def getUsers() {
		def data = [:]
		def rows = []
		data['page'] = 1
		
		//timetracker.User usr = (timetracker.User)session.user;
		
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
	
	def getProjects() {
		def data = [:]
		def rows = []
		data['page'] = 1
		
		int i = 0;
		ResultSet rs = dbHelper.ExecuteQuery("SELECT u.username, t.name taskname, te.date, hours_recorder, c.text  FROM public.timesheet_evidence te JOIN public.task t ON t.task_id = te.task JOIN public.timesheet ts ON ts.timesheet_id = te.timesheet JOIN public.user u ON u.user_id = ts.user_id JOIN public.comment c ON c.timesheet = ts.timesheet_id");
		while(rs.next())
		{
			def value = [:]
			value.id = rs.getObject("username") as String
			value.date = rs.getObject("date") as String
			value.hours = rs.getObject("hours_recorder") as String
			value.name = rs.getObject("taskname") as String
			value.comment = rs.getObject("text") as String
			
			rows << [cell: value];
			i++;
		}
		data['total'] = i;
		data['rows'] = rows
		
		render data as JSON
	}
	
	def getProjectsDiv() {
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
			
			rows << [cell: value];
			i++;
		}
		data['total'] = i;
		data['rows'] = rows
		
		render data as JSON
	}
	
	def getTasksDiv(String projectid) {
		def data = [:]
		def rows = []
		data['page'] = 1
		
		int i = 0;
		ResultSet rs = dbHelper.ExecuteQuery("SELECT * FROM task WHERE project = '" + projectid + "'");
		while(rs.next())
		{
			def value = [:]
			value.id = rs.getObject("task_id") as String
			value.name = rs.getObject("name")
			value.project = rs.getObject("project")
			
			rows << [cell: value];
			i++;
		}
		data['total'] = i;
		data['rows'] = rows
		
		render data as JSON
	}
	
	def insertNoteTimeData(String hours_recorded, String uuidtask, String timesheet, String comment)
	{
		timetracker.NoteTime ntime = new timetracker.NoteTime();
		
		double hours_recorded1 = hours_recorded as double
		ntime.InsertNoteTime(hours_recorded1, uuidtask, timesheet)
		
		ntime.InsertComment(comment)
		
		redirect(action: "index")
		
	}
	

}
