package MainLogData;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SaveData {

	private int logNumber;
	private String date;
	private double startTime; //need to talk to caleb
	private double endTime; //need to talk to caleb
	private double deltaTime; //need to talk to caleb
	private String lifeCycleStep;
	private String effortCategory;
	private String plan;
	private String project;
	
	public SaveData (int logNumber, String date, double startTime, double endTime, double deltaTime, String lifeCycleStep, String effortCategory, String plan, String project) {
		this.logNumber = logNumber;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.deltaTime = deltaTime;
		this.lifeCycleStep = lifeCycleStep;
		this.effortCategory = effortCategory;
		this.plan = plan;
	}
	
	public void SaveLog() {
		
		//this would be used print the savedata object to a file
		
	}

	public int getLogNumber() {
		return logNumber;
	}

	public void setLogNumber(int logNumber) {
		this.logNumber = logNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public double getEndTime() {
		return endTime;
	}

	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

	public double getDeltaTime() {
		return deltaTime;
	}

	public void setDeltaTime(double deltaTime) {
		this.deltaTime = deltaTime;
	}

	public String getLifeCycleStep() {
		return lifeCycleStep;
	}

	public void setLifeCycleStep(String lifeCycleStep) {
		this.lifeCycleStep = lifeCycleStep;
	}

	public String getEffortCategory() {
		return effortCategory;
	}

	public void setEffortCategory(String effortCategory) {
		this.effortCategory = effortCategory;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}
	
	public String getProject() {
		return project;
	}
	
	
	
}
