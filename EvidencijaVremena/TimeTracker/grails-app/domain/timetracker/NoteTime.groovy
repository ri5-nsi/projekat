package timetracker

import helper.dbHelper
import java.sql.ResultSet
import java.util.UUID

class NoteTime {

    static constraints = {
    }
	
	public UUID Project;
	public UUID Task;
	public UUID User;
	public UUID Timesheet;
	public double HoursRecorded;
	public Date EntryDate
	public String Test;
	
	public hasMany = [projekti: Project]
	
	public void InsertNoteTime(double hours_recorded, String uuidTask, String timesheet)
	{
		String uuidTSE = UUID.randomUUID() as String
		String uuidTS = UUID.randomUUID() as String


		//String query1 = "INSERT INTO timesheet(timesheet_id, user_id)" +
		//" VALUES ('" +uuidTS+ "', '0ccc677a-04fd-4f3a-b446-15d80740872c');";

		
		String query = "INSERT INTO timesheet_evidence(timesheet_evidence_id, date, day_of_week, hours_recorder, task, timesheet)" +
		" VALUES ('" +uuidTSE+ "','NOW()', '0', '" + hours_recorded + "', '" +uuidTask+ "', (SELECT timesheet_id FROM timesheet limit 1));";

		//dbHelper.ExecuteQuery(query1);
		
		dbHelper.ExecuteQuery(query);
		
	/*	String query2 = "INSERT INTO project(project_id, code, name, budget, description, start_on, end_on)" +
		" VALUES ('" +uuid+ "','0', '" +Name+ "', '0', '" +Description+ "', NOW(), NOW());";

		dbHelper.ExecuteQuery(query2);
		*/
		
	}
	
	public void InsertComment(String comment)
	{
		String uuidTSE = UUID.randomUUID() as String
		String query = "INSERT INTO public.comment(comment_id, date, text, timesheet)" +
		" VALUES ('" +uuidTSE+ "','NOW()', '"+ comment + "', (SELECT timesheet_id FROM timesheet limit 1));";
		
		dbHelper.ExecuteQuery(query);
	}
	public NoteTime()
	{
		//Date d = new Date();
		//d.day();
		
		//test = d.day().toString() + "." + d.month().toString() + "." + d.year().toString();
		//test = "Testni podatak"
		
	}
}
