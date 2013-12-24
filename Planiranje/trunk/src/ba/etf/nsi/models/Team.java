package ba.etf.nsi.models;

public class Team {
	
	public Team() {
		
		
	}
	
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String team_id;
	private String name;

}
