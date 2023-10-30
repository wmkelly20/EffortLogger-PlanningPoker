//Risk-Reduction Prototype: Planning Poker Loading
//Author: Wilson Kelly

package planningPokerSetup;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class planningPokerSetup extends Application {  
/*
To do:
	need to load logs from defectLogger
	could import all, for right now only need these variables:
		name
		symptoms

Flow:
	load or input user stories
		Story (text input)
		Description (text input)
	Start (clock button)
		poll begins with clock
		can now start voting
	Stop (clock button)
		stop voting
			estimation time(?)
		eliminate card
		next step is to keep playing, until done
*/
	//Class for defects
	public class Defect{
		private String project;
		private String name;
		private boolean status;
		private String symptoms;
		private String stepInject;
		private String stepRemove;
		private String defectCategory;
		private String fixDefect ;
		
		public Defect(String project, String name, boolean status, String symptoms, String stepInject, String stepRemove, String defectCategory, String fixDefect)
		{
			this.project = project;
			this.name = name;
			this.status = status;
			this.symptoms = symptoms;
			this.stepInject = stepInject;
			this.stepRemove = stepRemove;
			this.defectCategory = defectCategory;
			this.fixDefect = fixDefect;
		}
		
		//List of Setters for defect class
		public void setName(String name) {
			this.name=name;}
		
		public void setStatus(boolean status) {
			this.status = status;}
		
		public void setSymptoms(String symptoms) {
			this.symptoms = symptoms;}
		
		public void setStepInject(String stepInject) {
			this.stepInject = stepInject;}
		
		public void setStepRemove(String stepRemove) {
			this.stepRemove = stepRemove;}
		
		public void setDefectCategory(String defectCategory) {
			this.defectCategory = defectCategory;}
		
		public void setFixDefect(String fixDefect) {
			this.fixDefect = fixDefect;}
		
		//List of getters for defect class
		public String getName() {
			return name;}
		public boolean getStatus() {
			return status;}
		public String getSymptoms() {
			return symptoms;}
		public String getStepInject() {
			return stepInject;}
		public String getStepRemove() {
			return stepRemove;}
		public String getDefectCategory() {
			return defectCategory;}
		public String getFixDefect() {
			return fixDefect;}
			
	}
	
	//Core element definitions
    private ObservableList<String> projectOptions = FXCollections.observableArrayList("Business Project","Development Project");
    private ComboBox<String> projectCombo = new ComboBox<String>(projectOptions);
    private ObservableList<Defect> projectDefectsBusiness = FXCollections.observableArrayList(); //This should load up defects that are stored in database
    private ObservableList<String> projectDefectsBusinessNames = FXCollections.observableArrayList();
    private ObservableList<Defect> projectDefectsDevelopment = FXCollections.observableArrayList();
    private ObservableList<String> projectDefectsDevelopmentNames = FXCollections.observableArrayList();
    private ComboBox<String> defectCombo = new ComboBox<String>(projectDefectsBusinessNames);
    private ObservableList<String> stepInjectOptions = FXCollections.observableArrayList("Planning","Information Gathering","Information Understanding","Verifying","Outlining","Drafting","Finalizing","Team Meeting","Coach Meeting","Stakeholder Meeting");
    private ComboBox<String> stepInjectCombo = new ComboBox<String>(stepInjectOptions);
    private ComboBox<String> stepRemoveCombo = new ComboBox<String>(stepInjectOptions);
    private ObservableList<String> defectCategoryOptions = FXCollections.observableArrayList("Not Specified", "10 Documentation", "20 Syntax", "30 Build, Package","40 Assignment","50 Interface","60 Checking","70 Data","80 Function", "90 System", "100 Environment");
    private ComboBox<String> defectCategoryCombo = new ComboBox<String>(defectCategoryOptions);
    private ComboBox<String> fixDefectCombo = new ComboBox<String>(projectDefectsBusinessNames);

    
    private Button newDefectButton = new Button("Create New Defect");
    private Button updateDefectButton = new Button("Update Current Defect");
    private Button statusButton = new Button("Toggle Status");
    private Button saveButton = new Button("Save Data As Defect Log");
    private Button loadButton = new Button("Load Data");
    private Button deleteButton = new Button("Delete Current Defect");
    
    //Define core data that the defect log stores
    private String project = "Business Project";
    private String name = "";
    private boolean status = false;
    private String symptoms = "";
    private String stepInject = "";
    private String stepRemove = "";
    private String defectCategory = "";
    private String fixDefect = "";
    		
	
    //Other data in use by defect logger
    private int defectNumBusiness = 0; //This should be loaded to a value when a project is loaded up
    private int defectNumDevelopment = 0;
	
	
	@Override public void start(Stage primaryStage) {  
	
		
		//Action event for load data button
        EventHandler<ActionEvent> loadEvent = new EventHandler<ActionEvent>() 
        { 
        public void handle(ActionEvent e) 
            {	
            try {            	
            	File defectFile = new File("defectLog.txt");
        	    //check for contents
        	    if(defectFile.length()==0) {
        	    	  System.out.println("File is empty");
        	    }
        	    else 
        	    {
	        	      Scanner defectReader = new Scanner(defectFile);
	        	      String projectType = defectReader.nextLine();
	        	      projectType = defectReader.nextLine();
	        	      
	        	      //Set which project we are reading to
	        	      String currentProject = "Business";
	        	      
	        	      while (!projectType.equals("$$END READ$$")) 
	        	      {
	        	    	//Read in and set all of defect parameters
	        	    	String loadedName = defectReader.nextLine();
	        	    	String loadedStatus = defectReader.nextLine();
	        	    	boolean trueStatus;
	        	    	if (loadedStatus.equals("false"))
	        	    		trueStatus = false;
	        	    	else
	        	    		trueStatus = true;
	        	    	String loadedSymptoms = defectReader.nextLine();
	        	    	String loadedStepInject = defectReader.nextLine();
	        	    	String loadedStepRemove = defectReader.nextLine();
	        	    	String loadedDefectCategory = defectReader.nextLine();
	        	    	String loadedDefectFix = defectReader.nextLine();
	        	    	  
	        	    	//Determine which project we're reading to
	        	    	if (currentProject.equals("Business"))
	        	    	{
	            	    	//Create new defect object
	            	    	Defect newDefect = new Defect("Business Project",loadedName,trueStatus,loadedSymptoms,loadedStepInject,loadedStepRemove,loadedDefectCategory,loadedDefectFix);
	            	    	//Add these values to the system
	            	    	defectNumBusiness++;
	                    	projectDefectsBusiness.addAll(newDefect);
	                    	projectDefectsBusinessNames.addAll(loadedName);
	                    	//defectAmount.setText("There are " + Integer.toString(defectNumBusiness) + " defects in this project.");
	        	    	
	                    	//Update project combo
	            	    	projectCombo.setValue("Business Project");
	            	    	defectCombo.setItems(projectDefectsBusinessNames);
	                    	fixDefectCombo.setItems(projectDefectsBusinessNames);
	        	    	
	        	    	}
	        	    	else
	        	    	{
	        	    		//Create new defect object
	            	    	Defect newDefect = new Defect("Development Project",loadedName,trueStatus,loadedSymptoms,loadedStepInject,loadedStepRemove,loadedDefectCategory,loadedDefectFix);
	            	    	//Add these values to the system
	            	    	defectNumDevelopment++;
	                    	projectDefectsDevelopment.addAll(newDefect);
	                    	projectDefectsDevelopmentNames.addAll(loadedName);
	                    	//defectAmount.setText("There are " + Integer.toString(defectNumDevelopment) + " defects in this project.");
	        	    	
	    	        	       
	            	    	//Update project combo
	            	    	defectCombo.setItems(projectDefectsDevelopmentNames);
	                    	fixDefectCombo.setItems(projectDefectsDevelopmentNames);
	            	    	projectCombo.setValue("Development Project");
	        	    	}
	                	
	                	//Set these values into the current boxes and fields
	                	defectCombo.setValue(loadedName);
	                	name = loadedName;
	                	//to do: fix this later defectNameText.setText(loadedName);
	                	symptoms = loadedSymptoms;
	                	//symptomsText.setText(loadedSymptoms);
	                	
	                	status = trueStatus;
	                	//if (trueStatus)
	                		//statusLabel.setText("Current Status: OPEN");
	                	//else
	                		//statusLabel.setText("Current Status: CLOSED");
	                	stepInjectCombo.setValue(loadedStepInject);
	                	stepInject = loadedStepInject;
	                	stepRemoveCombo.setValue(loadedStepRemove);
	                	stepRemove = loadedStepRemove;
	                	defectCategoryCombo.setValue(loadedDefectCategory);
	                	defectCategory = loadedDefectCategory;
	                	fixDefectCombo.setValue(loadedDefectFix);
	                	fixDefect = loadedDefectFix;
	        	    	
	                	projectType = defectReader.nextLine();
	                	if (projectType.equals("$$DEVELOPMENT PROJECT$$"))
	                	{
	                		currentProject = "Development";
	                		projectType = defectReader.nextLine();
	                	}
	                	
	        	      }
	        	      defectReader.close();
	        	    } 
        	      	} 
	            	catch (FileNotFoundException err) {
	        	      err.printStackTrace();
	        	    }
            	}
            
        };
        loadButton.setOnAction(loadEvent);
                
        Label defectName = new Label("Story:");
        TextField defectNameText = new TextField();
        //Listener for the defectNameBox. When the string within the field has changed
        //It will update the name variable within the name variable which is later stored in observable list
        //Input must be string
        defectNameText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                name=newValue;
                
            }
        });
        
        Label symptomsLabel = new Label("Description:");
        TextField symptomsText = new TextField();
        //This is the textfield for the symptoms. It works functionally the same as the name textfield
        symptomsText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                symptoms=newValue;
                
            }
        });
       
        Label titleLabel = new Label("Planning Poker:");
        titleLabel.setFont(new Font("Arial", 24));
        
        Label descLabel = new Label("Please enter your user story below.");
        
        
        VBox userInput = new VBox(5);          
        userInput.setPadding(new Insets(10,10,10,10));
        userInput.setSpacing(10);
        userInput.setAlignment(Pos.TOP_CENTER);
        primaryStage.setScene(new Scene(userInput, 600, 750));
        primaryStage.setTitle("Planning Poker Setup");  
        
        
        userInput.getChildren().addAll(titleLabel,descLabel,defectName,defectNameText,symptomsLabel,symptomsText,loadButton);
        primaryStage.show();  

	}
	
	public static void main(String[] args) {
        launch(args);  
	}

}
