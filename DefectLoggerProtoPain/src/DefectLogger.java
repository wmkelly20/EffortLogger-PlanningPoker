
//Risk-Reduction Prototype: Defect Logs
//Author: Dylan Kingsbury

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

 
public class DefectLogger extends Application {
	
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
    
	
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override public void start(Stage primaryStage) {
    
    	//Initialize combo boxes
        projectCombo.setValue(projectOptions.get(0));
        //defectCombo.setValue(projectDefectsBusiness.get(0));
    	
    	
        primaryStage.setTitle("Defect Logs");
        
        //Create the label
        Label defectConsoleLabel = new Label("Defect Console");
        defectConsoleLabel.setFont(new Font("Arial", 20));
        defectConsoleLabel.setStyle("-fx-border-color: black;");
        defectConsoleLabel.setPadding(new Insets(10));
        Label projectLabel = new Label("1. Select the Project:");
        
        Label defectAmount;
        if (project == "Business Project")
        	defectAmount = new Label("There are " + Integer.toString(defectNumBusiness) + " defects in this project.");
        else
        	defectAmount = new Label("There are " + Integer.toString(defectNumDevelopment) + " defects in this project.");
        
        //Listen for changes within the combo box and update the project value
        //We use combo boxes to limit the amount of inputs the user can input as to avoid incorrect inputs
        projectCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            project = newValue;
            if (project == "Business Project")
            {
            	defectCombo.setItems(projectDefectsBusinessNames);
            	fixDefectCombo.setItems(projectDefectsBusinessNames);
            	defectAmount.setText("There are " + Integer.toString(defectNumBusiness) + " defects in this project.");
            }
            else
            {
            	defectCombo.setItems(projectDefectsDevelopmentNames);
            	fixDefectCombo.setItems(projectDefectsDevelopmentNames);
            	defectAmount.setText("There are " + Integer.toString(defectNumDevelopment) + " defects in this project.");
            }
            
        });
        

        
        
        Label defectLabel = new Label("2. Select one of the following defects to make it the current defect or press 'Create New Defect'.");
        
        //Action event for update defect button
        EventHandler<ActionEvent> updateDefectEvent = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {              
            	int index = defectCombo.getSelectionModel().getSelectedIndex();
                //Change values in current observable list
                if (project == "Business Project")
                {
                	projectDefectsBusinessNames.set(index, name);
                	projectDefectsBusiness.get(index).setName(name);
                	projectDefectsBusiness.get(index).setSymptoms(symptoms);
                	projectDefectsBusiness.get(index).setStepInject(stepInject);
                	projectDefectsBusiness.get(index).setStepRemove(stepRemove);
                	projectDefectsBusiness.get(index).setDefectCategory(defectCategory);
                	projectDefectsBusiness.get(index).setFixDefect(fixDefect);

                }
                else
                {
                	projectDefectsDevelopmentNames.set(index, name);
                	projectDefectsDevelopment.get(index).setName(name);
                	projectDefectsDevelopment.get(index).setSymptoms(symptoms);
                	projectDefectsDevelopment.get(index).setStepInject(stepInject);
                	projectDefectsDevelopment.get(index).setStepRemove(stepRemove);
                	projectDefectsDevelopment.get(index).setDefectCategory(defectCategory);
                	projectDefectsDevelopment.get(index).setFixDefect(fixDefect);
                	
                }
                defectCombo.setValue(name);
            } 
        }; 
        updateDefectButton.setOnAction(updateDefectEvent);
        
        
        //Label and box for naming a defect
        
        Label attributeLabel = new Label("3. Define or update Current Defect attributes");
        
        Label defectName = new Label("Defect Name:");
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
        
        //Add label and button for toggling status
        Label statusLabel = new Label();
        if (!status)
        	statusLabel.setText("Current Status: CLOSED");
        else
        	statusLabel.setText("Current Status: OPEN");
        //Action event for toggle status button
        EventHandler<ActionEvent> toggleStatusEvent = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {              
            	if (status)
            	{
            		statusLabel.setText("Current Status: CLOSED");
            		status = false;
            	}
            	else
            	{
            		statusLabel.setText("Current Status: OPEN");
            		status = true;
            	}
            	
            	
            	int index = defectCombo.getSelectionModel().getSelectedIndex();
                //Change values in current observable list
                if (project == "Business Project")
                	projectDefectsBusiness.get(index).setStatus(status);
                else
                	projectDefectsDevelopment.get(index).setStatus(status);
                
            } 
        }; 
        statusButton.setOnAction(toggleStatusEvent);
        
        
        Label symptomsLabel = new Label("Defect Symptoms or Cause/Resolution");
        TextField symptomsText = new TextField();
        //This is the textfield for the symptoms. It works functionally the same as the name textfield
        symptomsText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                symptoms=newValue;
                
            }
        });
        
        //Listen for changes when selecting defect
        defectCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
        	//Set name
        	int index = defectCombo.getSelectionModel().getSelectedIndex();
        	name = defectCombo.getValue();
        	if (project == "Business Project")
        	{
        		status = projectDefectsBusiness.get(index).status;
        		symptoms = projectDefectsBusiness.get(index).symptoms;
        		stepInject = projectDefectsBusiness.get(index).stepInject;
        		stepRemove = projectDefectsBusiness.get(index).stepRemove;
        		defectCategory = projectDefectsBusiness.get(index).defectCategory;
        		fixDefect = projectDefectsBusiness.get(index).fixDefect;
        	}
        	else
        	{
        		status = projectDefectsDevelopment.get(index).status;
        		symptoms = projectDefectsDevelopment.get(index).symptoms;
        		stepInject = projectDefectsDevelopment.get(index).stepInject;
        		stepRemove = projectDefectsDevelopment.get(index).stepRemove;
        		defectCategory = projectDefectsDevelopment.get(index).defectCategory;
        		fixDefect = projectDefectsDevelopment.get(index).fixDefect;
        	}
        	//Update Status text
        	if (!status)
        		statusLabel.setText("Current Status: CLOSED");
        	else
        		statusLabel.setText("Current Status: OPEN");
        	
        	defectNameText.setText(name);
        	symptomsText.setText(symptoms);
        	stepInjectCombo.setValue(stepInject);
        	stepRemoveCombo.setValue(stepRemove);
        	defectCategoryCombo.setValue(defectCategory);
        	fixDefectCombo.setValue(fixDefect);
      
        });
        
        //Action event for create new defect button
        EventHandler<ActionEvent> newDefectEvent = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {              
                Defect newDefect = new Defect(project,name,status,symptoms,stepInject,stepRemove,defectCategory,fixDefect);
                //Add defect to corresponding defect list
                if (project == "Business Project")
                {
                	defectNumBusiness++;
                	projectDefectsBusiness.addAll(newDefect);
                	projectDefectsBusinessNames.addAll(name);
                	defectAmount.setText("There are " + Integer.toString(defectNumBusiness) + " defects in this project.");

                }
                else
                {
                	defectNumDevelopment++;
                	projectDefectsDevelopment.addAll(newDefect);
                	projectDefectsDevelopmentNames.addAll(name);
                	defectAmount.setText("There are " + Integer.toString(defectNumDevelopment) + " defects in this project.");
                }
                defectCombo.setValue(name);
            } 
        }; 
        newDefectButton.setOnAction(newDefectEvent);
        
        
        //Editing the Step Inject attribute
        Label stepInjectLabel = new Label("Step when injected");
        //Listen for changes when selecting defect
        stepInjectCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
        	//Set step inject
        	stepInject = newValue;
      
        });
        //Editing the remove Inject attribute
        Label stepRemoveLabel = new Label("Step when removed");
        //Listen for changes when selecting defect
        stepRemoveCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
        	//Set step inject
        	stepRemove = newValue;
      
        });
        //Editing the remove Inject attribute
        Label defectCategoryLabel = new Label("Defect Category");
        //Listen for changes when selecting defect
        defectCategoryCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
        	//Set step inject
        	defectCategory = newValue;
      
        });
        
        //Fix Defect Combo Box Code
        Label fixDefectLabel = new Label("Fix:");
        //Listen for changes when selecting defect
        fixDefectCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
        	//Set step inject
        	fixDefect = newValue;
      
        });
        
        //Action event for save data button
        EventHandler<ActionEvent> saveEvent = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
            	try 
            	{
		        	//First create the text file
		    	    File defectFile = new File("defectLog.txt");
		    	    defectFile.createNewFile();
		    	    
		    	    //Next write data onto text file
	    	        FileWriter myWriter = new FileWriter("defectLog.txt");
	    	        
	    	        //Check if Business project is null or not
	    	        if (!projectDefectsBusiness.isEmpty())
	    	        {
		    	        
		    	        //Print start of business projects
		    	        myWriter.write("$$BUSINESS PROJECT$$\n");
		    	        
		    	        //Iterate through each defect within business project list
		    	        projectDefectsBusiness.forEach((defect) -> { 
		    	        	try {
								myWriter.write("\n" +  defect.getName() + "\n" + 
												defect.getStatus() + "\n" +
												defect.getSymptoms() + "\n" +
												defect.getStepInject() + "\n" +
												defect.getStepRemove() + "\n" +
												defect.getDefectCategory() + "\n" +
												defect.getFixDefect() + "\n");
								
							} catch (IOException err) {
								err.printStackTrace();
							}
		    	        });
	    	        }
	    	        
	    	        //Check if Development project is null or not
	    	        if (!projectDefectsDevelopment.isEmpty())
	    	        {
	    	        
		    	        //Print start of develop project list
		    	        myWriter.write("$$DEVELOPMENT PROJECT$$\n");
		    	        
		    	        //Iterate through each defect within business project list
		    	        projectDefectsDevelopment.forEach((defect) -> { 
		    	        	try {
		    	        		myWriter.write("\n" + defect.getName() + "\n" + 
				    	        				defect.getStatus() + "\n" +
				    	        				defect.getSymptoms() + "\n" +
												defect.getStepInject() + "\n" +
												defect.getStepRemove() + "\n" +
												defect.getDefectCategory() + "\n" +
												defect.getFixDefect() + "\n");
							} catch (IOException err) {
								err.printStackTrace();
							}
		    	        });
	    	        }
	    	        
	    	        //Print end read line
	    	        myWriter.write("$$END READ$$\n");
	    	        
	    	        
	    	        
	    	        
	    	        myWriter.close();
        	    } 
            	catch (IOException err) 
            	{
            		err.printStackTrace();
            	}
            	
            }
        }; 
        saveButton.setOnAction(saveEvent);
        
        //Action event for load data button
        EventHandler<ActionEvent> loadEvent = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
            	
            	try {
        	      File defectFile = new File("defectLog.txt");
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
	                	defectAmount.setText("There are " + Integer.toString(defectNumBusiness) + " defects in this project.");
        	    	
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
	                	defectAmount.setText("There are " + Integer.toString(defectNumDevelopment) + " defects in this project.");
        	    	
		        	       
	        	    	//Update project combo
	        	    	defectCombo.setItems(projectDefectsDevelopmentNames);
	                	fixDefectCombo.setItems(projectDefectsDevelopmentNames);
	        	    	projectCombo.setValue("Development Project");
        	    	}
                	
                	//Set these values into the current boxes and fields
                	defectCombo.setValue(loadedName);
                	name = loadedName;
                	defectNameText.setText(loadedName);
                	symptoms = loadedSymptoms;
                	symptomsText.setText(loadedSymptoms);
                	
                	status = trueStatus;
                	if (trueStatus)
                		statusLabel.setText("Current Status: OPEN");
                	else
                		statusLabel.setText("Current Status: CLOSED");
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
            	catch (FileNotFoundException err) {
        	      err.printStackTrace();
        	    }
            }
        };
        loadButton.setOnAction(loadEvent);
        
        
        
        
        //Create the vbox, it's settings and add all previously created elements to scene.
        VBox root = new VBox(5);
        root.setPadding(new Insets(10,10,10,10));
        root.setAlignment(Pos.TOP_CENTER);
        
        primaryStage.setScene(new Scene(root, 600, 750));
        primaryStage.show();
        
        //Add children to root
        root.getChildren().addAll(defectConsoleLabel,projectLabel,projectCombo,defectAmount,defectLabel,defectCombo,attributeLabel,statusLabel,statusButton,defectName,defectNameText,symptomsLabel,symptomsText,stepInjectLabel,stepInjectCombo,stepRemoveLabel,stepRemoveCombo,defectCategoryLabel,defectCategoryCombo,fixDefectLabel,fixDefectCombo,newDefectButton,updateDefectButton,saveButton,loadButton);
    }

}