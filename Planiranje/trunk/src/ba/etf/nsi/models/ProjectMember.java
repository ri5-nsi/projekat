package ba.etf.nsi.models;

public class ProjectMember {
	
	public ProjectMember() {
		// TODO Auto-generated constructor stub
	}
	
    public User user;
    
    
	

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getProject_member_id() {
		return project_member_id;
	}
	public void setProject_member_id(String project_member_id) {
		this.project_member_id = project_member_id;
	}



	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public void setPlanned_hours_per_task(Double planned_hours_per_task) {
		this.planned_hours_per_task = planned_hours_per_task;
	}
	
	public Double getPlanned_hours_per_task() {
		return this.planned_hours_per_task;
	}
	
	public void setPrice_per_hour(Double price_per_hour) {
		this.price_per_hour = price_per_hour;
	}
	
	public Double getPrice_per_hour() {
		return this.price_per_hour;
	}

	private String project_member_id;
	private Double planned_hours_per_task;
	private String role;
	private Double price_per_hour;
	private String task_id;
	private String team_id;
	
	

}
