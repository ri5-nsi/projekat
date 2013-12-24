package ba.etf.nsi.models;

import java.util.Date;

public class Task {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getHoursPlaned() {
		return hoursPlaned;
	}
	public void setHoursPlaned(double hoursPlaned) {
		this.hoursPlaned = hoursPlaned;
	}
	private String id;
	private int code;
	private String name;
	private int status;
	private double hoursPlaned;
	public String getStartOn() {
		return startOn;
	}
	public void setStartOn(String startOn) {
		this.startOn = startOn;
	}
	public String getEndOn() {
		return endOn;
	}
	public void setEndOn(String endOn) {
		this.endOn = endOn;
	}
	private String startOn;
	private String endOn;
}
