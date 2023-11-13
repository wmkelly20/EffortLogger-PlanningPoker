package effortLoggerPackage;
//package MainLogData;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SaveData {

	private int logNumber;
	private String date;
	private String startTime; //need to talk to caleb
	private String endTime; //need to talk to caleb
	private String deltaTime; //need to talk to caleb
	private String lifeCycleStep;
	private String effortCategory;
	private String plan;
	private String project;
	
	public SaveData (int logNumber, String date, String endTime, String deltaTime, String lifeCycleStep, String effortCategory, String project) {
		this.logNumber = logNumber;
		this.date = date;
		//this.startTime = startTime;
		this.endTime = endTime;
		this.deltaTime = deltaTime;
		this.lifeCycleStep = lifeCycleStep;
		this.effortCategory = effortCategory;
		this.project = project;
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

	public void setDate(String date2) {
		this.date = date2;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime2) {
		this.endTime = endTime2;
	}

	public String getDeltaTime() {
		return deltaTime;
	}

	public void setDeltaTime(String deltaTime2) {
		this.deltaTime = deltaTime2;
	}

	public String getLifeCycleStep() {
		return lifeCycleStep;
	}

	public void setLifeCycleStep(String lifeCycleStep2) {
		this.lifeCycleStep = lifeCycleStep2;
	}

	public String getEffortCategory() {
		return effortCategory;
	}

	public void setEffortCategory(String effortCategory2) {
		this.effortCategory = effortCategory2;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan2) {
		this.plan = plan2;
	}
	
	public String getProject() {
		return project;
	}
	
	public void setProject(String project2) {
		this.project = project2;
	}
	
	
	
}