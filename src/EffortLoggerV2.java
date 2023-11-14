//Author: Caleb Patterson
package src;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
//import java.awt.Color;
//import java.awt.Insets;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Scanner;

//import javax.smartcardio.Card;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class EffortLoggerV2 extends Application {
	
	//BRANDON Declarations-----------------------------------
	 private ObservableList<Project> projects = FXCollections.observableArrayList();
	 private ListView<Project> projectListView;
	 private HBox cardContainer;
	 private Card selectedCard;
	 
	 private void generateEstimate() {
	        Project selectedProject = projectListView.getSelectionModel().getSelectedItem();
	        if (selectedProject == null) {
	            new Alert(Alert.AlertType.WARNING, "No project selected").show();
	            return;
	        }
	        int estimate = calculateEstimate(selectedProject);
	        selectCardWithValue(estimate);
	    }


	    private int calculateEstimate(Project selectedProject) {
	        return selectedProject.getStoryPoints();
	    }
	    
	    private void selectCardWithValue(int value) {
	        for (Node node : cardContainer.getChildren()) {
	            if (node instanceof Card) {
	                Card card = (Card) node;
	                card.setSelected(card.getValue() == value);
	            }
	        }
	    }

	    private class Project {
	        private String name;
	        private int storyPoints;

	        public Project(String name, int storyPoints) {
	            this.name = name;
	            this.storyPoints = storyPoints;
	        }

	        public int getStoryPoints() {
	            return storyPoints;
	        }

	        @Override
	        public String toString() {
	            return name + " - " + storyPoints + " points";
	        }
	    }

	    
	    private void createCards() {
	        for (int i = 0; i <= 100; i += 10) { 
	            Card card = new Card(i);
	            cardContainer.getChildren().add(card);
	            card.setOnMouseClicked(e -> cardClicked(card));
	        }
	    }
	    
	    private void cardClicked(Card card) {
	    	if(selectedCard != null) selectedCard.unhighlight();
	    	card.highlight();
	    	selectedCard = card;
	        System.out.println("Card value: " + card.getValue());
	    }
	    
	    private void loadDataFromCSV() {
	        String csvFile = "data.csv";  
	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                System.out.println("Read line: " + line);
	                String[] parts = line.split(",");
	                if (parts.length < 2) {
	                    System.err.println("Skipping line due to insufficient parts: " + line);
	                    continue;
	                }
	                String name = parts[0].trim().replaceAll("\"", "");
	                int storyPoints;
	                try {
	                    storyPoints = Integer.parseInt(parts[1].trim().replaceAll("\"", ""));
	                } catch (NumberFormatException e) {
	                    System.err.println("Invalid story points value: " + parts[1]);
	                    continue;
	                }
	                projects.add(new Project(name, storyPoints));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        projectListView.refresh();
	        System.out.println("Projects list size after loading: " + projects.size());
	        if (!projects.isEmpty()) {
	            System.out.println("First project: " + projects.get(0));
	        }
	    }

	 //------------------------------------------------------
	
	
	
	//KEENAN Declarations------------------------------------
	 private LinkedList<SaveData> LogList = new LinkedList<SaveData>();
	 private int logNumber = 0;
	 private String date = "Date";
	 private double startTime = 0; //need to talk to caleb
	 private double endTime = 0; //need to talk to caleb
	 private double deltaTime = 0; //need to talk to caleb
	 //------------------------------------------------------
	
	
	//DYLAN declarations-------------------------------------
	public class Defect{
		private String project;
		private String name;
		private boolean status;
		private String symptoms;
		private String stepInject;
		private String stepRemove;
		private String defectCategory;
		private String fixDefect;
		
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
     //----------------------------------------------------
     
     //CALEB Variables--------------
     private LocalDateTime startTimeC;
     private LocalDateTime stopTime;
     private String currentTime;
     private String projectC;
     //-----------------------------
	
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
    	
    	//mainline button declarations
    	Button effortLoggerConsoleBtn = new Button("Effort Logger Console");
    	Button defectLogConsoleBtn = new Button("Defect Log Console");
    	Button effortLogEditorBtn = new Button("Effort Log Editor");
    	Button planningPokerBtn = new Button("Planning Poker");
    	Button planningPokerOtherBtn = new Button("Planning Poker Other");
    	
    	//CALEB Code================================================================
    	effortLoggerConsoleBtn.setOnAction(mainlineEvent -> {
    	Stage secondaryStage = new Stage();

    	LocalDate currentDate = LocalDate.now();  
        System.out.println("Welcome to EffortLoggerV2");
        System.out.println("It started!");
        primaryStage.setTitle("EffortLogger V2");
        
     
        Label header = new Label("Effort Logger Console");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        
        // Clock Code-----------------------------------------
        Label timeLabel = new Label();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        
        //Creates a Timeline that calls our function every second
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime ct = LocalTime.now();
            timeLabel.setText(ct.format(formatter));
            currentTime = ct.format(formatter);
        }),
        new KeyFrame(Duration.seconds(1))
        );

        clock.setCycleCount(Timeline.INDEFINITE);
        //clock.play();
        //---------------------------------------------------------
        
        //Clock state notification tile-------------------------
        StackPane redPane = new StackPane();
        redPane.setPrefHeight(40);  
        redPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

        Label redPaneLabel = new Label("Clock is stopped");
        redPaneLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        redPaneLabel.setTextFill(Color.WHITE);  
        redPaneLabel.setAlignment(Pos.CENTER);  
        redPane.getChildren().add(redPaneLabel);
        //------------------------------------------------------
        
        HBox hb = new HBox();
        hb.getChildren().addAll(header);
        hb.setSpacing(10);
        hb.setAlignment(Pos.TOP_CENTER);
        
        //Step 1-----------------------------
        //Start Button to begin clock and store the current time data
        Label startActDescription = new Label("1. When you start a new activity, press the 'Start an Activity' button.");
        Button startActivityBtn = new Button();
        startActivityBtn.setText("Start an Activity");
        startActivityBtn.setOnAction(event -> {
        	startTimeC = LocalDateTime.now();
            System.out.println("Clock has begun!");
            clock.play();
            redPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            redPaneLabel.setText("Clock is running");
        });
        //-----------------------------------
        
        //Step 2---------------------------------------------
        //Combo boxes for project attribute selection
        Label attributesDescription = new Label("2. Select the project, life cycle step, effort category, and deliverable from the following lists: ");
        Label projectLabel = new Label("Project:");
        ComboBox<String> projectCombo = new ComboBox<>();
        projectCombo.getItems().addAll("Business Project", "Development Project");
        Label lifeCycleStepsLabel = new Label("Life Cycle Steps:");
        ComboBox<String> lifeCycleCombo = new ComboBox<>();
        //Logic for changing what options the Life Cycle combo box displays depending on what project is selected 
        projectCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
        	if(newValue == "Business Project")
        	{
        		lifeCycleCombo.getItems().clear();
            	lifeCycleCombo.getItems().addAll("Planning", "Information Gathering", "Information Understanding", "Verifying",
            		"Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting");
        	}else {
        		lifeCycleCombo.getItems().clear();
            	lifeCycleCombo.getItems().addAll("Problem Understanding", "Conceptual Design Plan", "Requirements", "Conceptual Design", 
            			"Conceptual Design Review", "Detailed Design Plan", "Detailed Design/Prototype", "Detailed Design Review",
            			"Implementation Plan", "Test Case Generation", "Solution Specification", "Solution Review", "Solution Implementation", 
            			"Unit/System Test", "Reflection", "Repository Update");
        	}	
        });
       
        Label effortCategory = new Label("Effort Category");
        ComboBox<String> effortCombo = new ComboBox<>();
        effortCombo.getItems().addAll("Plans", "Deliverables", "Interruptions", "Defects", "Others");
        ComboBox<String> subEffortCombo = new ComboBox<>();
      //Logic for changing what options the sub effort combo box displays depending on what main Effort Category is selected 
        effortCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
        	if(newValue == "Plans")
        	{
        		subEffortCombo.getItems().clear();
        		subEffortCombo.getItems().addAll("Project Plan", "Risk Management Plan", "Conceptual Design Plan", "Detailed Design Plan", 
        				"Implementation Plan");
        	}
        	else if(newValue == "Deliverables")
        	{
        		subEffortCombo.getItems().clear();
        		subEffortCombo.getItems().addAll("Conceptual Design", "Detailed Design", "Test Cases", "Solution", "Reflection", "Outline",
        				"Draft", "Report", "User Defined", "Other");
        	}
        	else if(newValue == "Interruptions")
        	{
        		subEffortCombo.getItems().clear();
        		subEffortCombo.getItems().addAll("Break", "Phone", "Teammate", "Visitor", "Other");
        	}
        	else if(newValue == "Defects")
        	{
        		subEffortCombo.getItems().clear();
        		subEffortCombo.getItems().addAll("- no defect selected -");
        	}else {
        		subEffortCombo.getItems().clear();
        	}
        });
        
        VBox listVBox = new VBox();
        listVBox.getChildren().addAll(attributesDescription,projectLabel, projectCombo, lifeCycleStepsLabel, lifeCycleCombo, effortCategory, effortCombo, subEffortCombo);
        //---------------------------------------------------
        
        //Step 3---------------------------------------------
        //Stop Button to stop clock and log all activity data selected by user
        Label stopActDescription = new Label("3. Press the 'Stop this Activity' to generate an effort log entry using the attributes above.");
        Button stopActivityBtn = new Button();
        stopActivityBtn.setText("Stop this Activity");
        //When stop button pressed, store the number of minutes that have elapsed from the start time and stop time
        stopActivityBtn.setOnAction(event -> {
        	stopTime = LocalDateTime.now();
            long minutesElapsed = ChronoUnit.MINUTES.between(startTimeC, stopTime); // Duration in minutes
            //double durationInHours = minutes / 60.0; // Convert minutes to hours

            
            clock.stop();
            
            //All of the drop down data stored here
            String projectSelected = projectCombo.getValue();
            String lifeCycleSelected = lifeCycleCombo.getValue();
            String effortSelected = effortCombo.getValue();
            String subEffortSelected = subEffortCombo.getValue();
            
            //FILE I/O
            PrintWriter writer = null;
            try {
                // Create a new file (or overwrite an existing file)
                File file = new File("output.txt");
                writer = new PrintWriter(new FileWriter(file, true));

                // Writes log data to text file
                writer.println("Date: " + currentDate
                		+ "|" + currentTime
                		+ "|" + minutesElapsed + " minutes"
                		+ "|" + projectSelected
                		+ "|" + lifeCycleSelected
                		+ "|" + effortSelected
                		+ ", " + subEffortSelected + "|\n");
            } catch (IOException e) {
                System.err.println("Error: Could not create or write to file.");
                e.printStackTrace();
            } finally {
                //Closes the writer to avoid memory leaks
                if (writer != null) {
                    writer.close();
                }
            }
         
            redPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            redPaneLabel.setText("Clock is stopped");
            //System.out.println(clock);
        });
        //---------------------------------------------------
        
        //Step 4---------------------------------------------
        //Buttons that lead to other EffortLogger V2 features
        Label UIMenuDescription = new Label("4. Unless you are done for the day, it is best to perform steps 1 and 2 above before resuming work.");
        Button EffortLogBtn = new Button("Effort Log");
        Button DefectLogConsoleBtn = new Button("Defect Log Console");
        Button DefinitionsBtn = new Button("Definitions");
        Button EffortDefectLogsBtn = new Button("Effort Defect Logs");
        //---------------------------------------------------
        HBox UIMenuHBox = new HBox();
        UIMenuHBox.getChildren().addAll(EffortLogBtn, DefectLogConsoleBtn, DefinitionsBtn, EffortDefectLogsBtn);
        UIMenuHBox.setSpacing(20);
        
        VBox vbox = new VBox();   
        vbox.getChildren().addAll(hb, redPane, startActDescription, startActivityBtn, listVBox, stopActDescription, stopActivityBtn, UIMenuDescription, UIMenuHBox, timeLabel); 
        vbox.setSpacing(16); 
        vbox.setPadding(new Insets(0, 0, 0, 12));
        
        secondaryStage.setScene(new Scene(vbox, 600, 600));
        secondaryStage.show();
        //primaryStage.setScene(new Scene(vbox, 600, 600));
        //primaryStage.show();
    	});
    	//========================================================================================
    	
    	//DYLAN Code==============================================================================
    	defectLogConsoleBtn.setOnAction(mainlineEvent -> {
    		
        	
        	//Core element definitions
    		/*
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
            */
        	
        	
            
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
                EventHandler<ActionEvent> deleteEvent = new EventHandler<ActionEvent>() { 
                    public void handle(ActionEvent e) 
                    {
                    	//Check if current defect selected is valid
                    	if (defectCombo.getValue() != null)
                    	{
                			int index = defectCombo.getSelectionModel().getSelectedIndex();
                    		if (projectCombo.getValue().equals("Business Project"))
                    		{
                    			projectDefectsBusinessNames.remove(name);
                    			projectDefectsBusiness.remove(projectDefectsBusiness.get(index));
                    			defectNumBusiness--;
                    			defectAmount.setText("There are " + Integer.toString(defectNumBusiness) + " defects in this project.");
                    		}
                    		else
                    		{
                    			projectDefectsDevelopmentNames.remove(name);
                    			projectDefectsDevelopment.remove(projectDefectsBusiness.get(index));
                    			defectNumDevelopment--;
                    			defectAmount.setText("There are " + Integer.toString(defectNumDevelopment) + " defects in this project.");
                    		}
                    		
                    		//Reset current values
                    		name = "";
                    		defectCombo.setValue("");
                    		defectNameText.setText("");
                    		symptoms = "";
                    		symptomsText.setText("");
                    		stepInject = "";
                    		stepInjectCombo.setValue("");
                    		stepRemove = "";
                    		stepRemoveCombo.setValue("");
                    		defectCategory = "";
                    		defectCategoryCombo.setValue("");
                    		fixDefect = "";
                    		fixDefectCombo.setValue("");
                    	}
                    		
                    	
                    }
                };
                deleteButton.setOnAction(deleteEvent);
                
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
                root.getChildren().addAll(defectConsoleLabel,projectLabel,projectCombo,defectAmount,defectLabel,defectCombo,attributeLabel,statusLabel,statusButton,defectName,defectNameText,symptomsLabel,symptomsText,stepInjectLabel,stepInjectCombo,stepRemoveLabel,stepRemoveCombo,defectCategoryLabel,defectCategoryCombo,fixDefectLabel,fixDefectCombo,newDefectButton,updateDefectButton,deleteButton,saveButton,loadButton);
        
    	});
    	//=====================================================================================================
    	
    	//KEENAN CODE==========================================================================================
    	effortLogEditorBtn.setOnAction(mainlineEvent -> {
    		 // Create a button
	        Button start = new Button("Start an Activity");
	        Button stop = new Button("Stop this Activity");
	        Button edit = new Button("Effort Log Editor");
	        // Increase the button's font size
	        start.setMinWidth(Region.USE_PREF_SIZE);
	        start.setStyle("-fx-font-size: 20px;"); 
	        stop.setMinWidth(Region.USE_PREF_SIZE);
	        stop.setStyle("-fx-font-size: 20px;"); 
	        edit.setMinWidth(Region.USE_PREF_SIZE);
	        edit.setStyle("-fx-font-size: 20px");
	        
	        // Create a text field
	        TextField projectText = new TextField("Business Project");
	        TextField lifeCycleText = new TextField();
	        TextField effortCatText = new TextField();
	        TextField planText = new TextField();
	        
	        // Create a label
	        Label projectLabel = new Label("Project:");
	        Label lifeCycleLabel = new Label("Life Cycle Step:");
	        Label effortCatLabel = new Label("Effort Category:");
	        Label planLabel = new Label("Plan:");
	        

	        // Define a layout (StackPane) and add the button and text field to it
	        GridPane grid = new GridPane();
	        grid.getChildren().addAll(start, stop, projectText, lifeCycleText, effortCatText, planText, projectLabel, lifeCycleLabel, effortCatLabel, planLabel, edit);
	        
	        //positioning
	        GridPane.setRowIndex(start, 1); // Set the row index
	        GridPane.setColumnIndex(start, 2); // Set the column index
	        GridPane.setRowIndex(projectLabel, 3);
	        GridPane.setColumnIndex(projectLabel, 1);
	        GridPane.setRowIndex(projectText, 4);
	        GridPane.setColumnIndex(projectText, 1);
	        GridPane.setRowIndex(lifeCycleText, 4);
	        GridPane.setColumnIndex(lifeCycleText, 4);
	        GridPane.setRowIndex(effortCatText, 8);
	        GridPane.setColumnIndex(effortCatText, 1);
	        GridPane.setRowIndex(planText, 8);
	        GridPane.setColumnIndex(planText, 4);
	        GridPane.setRowIndex(lifeCycleLabel, 3);
	        GridPane.setColumnIndex(lifeCycleLabel, 4);
	        GridPane.setRowIndex(effortCatLabel, 7);
	        GridPane.setColumnIndex(effortCatLabel, 1);
	        GridPane.setRowIndex(planLabel, 7);
	        GridPane.setColumnIndex(planLabel, 4);
	        GridPane.setRowIndex(stop, 11);
	        GridPane.setColumnIndex(stop, 2);
	        GridPane.setRowIndex(edit, 12);
	        GridPane.setColumnIndex(edit, 2);
	        
	        //grid 2 stuff
	        Button backToLogs = new Button("Back To Log Console");
	        Button clearLogs = new Button("Clear This Effort Log");
	        Button deleteLog = new Button("Delete This Entry");
	        Button updateLog = new Button("Update this entry");
	        Button splitLog = new Button("Split This Entry into Two Entries");
	        Button loadData = new Button("Load Log");
	        backToLogs.setStyle("-fx-font-size: 10px");
	        clearLogs.setStyle("-fx-font-size: 10px");
	        deleteLog.setStyle("-fx-font-size: 10px");
	        updateLog.setStyle("-fx-font-size: 10px");
	        splitLog.setStyle("-fx-font-size: 10px");
	        
	        
	        TextField projectEditText = new TextField("Business Project");
	        TextField numberToChangeText = new TextField();
	        TextField dateText = new TextField();
	        TextField startTimeText = new TextField();
	        TextField stopTimeText = new TextField();
	        TextField lifeCycEditText = new TextField();
	        TextField effortCatEditText = new TextField();
	        
	        Label projectEditLabel = new Label("1. Select the Project in order to edit its Effort Log.");
	        projectEditLabel.setMinWidth(Region.USE_PREF_SIZE);
	        Label numberToChangeLabel = new Label("2.b. Select the Effort Log Entry's number");
	        numberToChangeLabel.setMinWidth(Region.USE_PREF_SIZE);
	        Label modifyAttLabel = new Label("3.a. Modify the Current Effort Log Entry's attributes and press \"Update This Entry\" when finished.");
	        modifyAttLabel.setMinWidth(Region.USE_PREF_SIZE);
	        Label dateEditLabel = new Label("Date:");
	        Label startTimeEditLabel = new Label("Start Time:");
	        Label stopTimeEditLabel = new Label("Stop Time:");
	        Label lifeCycleStepEditLabel = new Label("Life Cycle Step:");
	        Label effortCatEditLabel = new Label("Effort Category:");
	        
	        
	        
	        GridPane grid2 = new GridPane();
	        grid2.getChildren().addAll(clearLogs, projectEditText, projectEditLabel, numberToChangeLabel, numberToChangeText, modifyAttLabel,
	        		dateEditLabel, dateText, startTimeEditLabel, startTimeText, stopTimeEditLabel,
	        		stopTimeText, lifeCycleStepEditLabel, lifeCycEditText, effortCatEditLabel,
	        		effortCatEditText, updateLog, deleteLog, splitLog, backToLogs, loadData);
	        
	        GridPane.setColumnIndex(projectEditText, 4);
	        GridPane.setRowIndex(projectEditText, 1);
	        GridPane.setColumnIndex(clearLogs, 10);
	        GridPane.setRowIndex(clearLogs, 1);
	        GridPane.setColumnIndex(projectEditLabel, 4);
	        GridPane.setRowIndex(projectEditLabel, 0);
	        GridPane.setColumnIndex(numberToChangeLabel, 4);
	        GridPane.setRowIndex(numberToChangeLabel, 5);
	        GridPane.setColumnIndex(numberToChangeText, 4);
	        GridPane.setRowIndex(numberToChangeText, 6);
	        GridPane.setColumnIndex(modifyAttLabel, 4);
	        GridPane.setRowIndex(modifyAttLabel, 7);
	        GridPane.setColumnIndex(dateEditLabel, 4);
	        GridPane.setRowIndex(dateEditLabel, 8);
	        GridPane.setColumnIndex(dateText, 4);
	        GridPane.setRowIndex(dateText, 9);
	        GridPane.setColumnIndex(startTimeEditLabel, 4);
	        GridPane.setRowIndex(startTimeEditLabel, 10);
	        GridPane.setColumnIndex(startTimeText, 4);
	        GridPane.setRowIndex(startTimeText, 11);
	        GridPane.setColumnIndex(stopTimeEditLabel, 4);
	        GridPane.setRowIndex(stopTimeEditLabel, 12);
	        GridPane.setColumnIndex(stopTimeText, 4);
	        GridPane.setRowIndex(stopTimeText, 13);
	        GridPane.setColumnIndex(lifeCycleStepEditLabel, 4);
	        GridPane.setRowIndex(lifeCycleStepEditLabel, 14);
	        GridPane.setColumnIndex(lifeCycEditText, 4);
	        GridPane.setRowIndex(lifeCycEditText, 15);
	        GridPane.setColumnIndex(effortCatEditLabel, 4);
	        GridPane.setRowIndex(effortCatEditLabel, 16);
	        GridPane.setColumnIndex(effortCatEditText, 4);
	        GridPane.setRowIndex(effortCatEditText, 17);
	        GridPane.setColumnIndex(updateLog, 4);
	        GridPane.setRowIndex(updateLog, 18);
	        GridPane.setColumnIndex(deleteLog, 4);
	        GridPane.setRowIndex(deleteLog, 19);
	        GridPane.setColumnIndex(splitLog, 4);
	        GridPane.setRowIndex(splitLog, 20);
	        GridPane.setColumnIndex(backToLogs, 4);
	        GridPane.setRowIndex(backToLogs, 21);
	        GridPane.setColumnIndex(loadData, 10);
	        GridPane.setRowIndex(loadData, 6);
	        
	        
	        

	        // Create the scene
	        Scene logScene = new Scene(grid, 400, 500);
	        Scene editScene = new Scene(grid2, 700, 500);

	        // Set the stage's title
	        primaryStage.setTitle("Effort Console");

	        // Set the scene for the stage
	        primaryStage.setScene(logScene);

	        // Set an action for the button
	        start.setOnAction(event -> {
	        	//System.out.println(LogList);
	        	
	        	projectText.setText("Business Project");
	        	
	        });
	        
	        stop.setOnAction(event -> {
	        	
	        	logNumber += 1;
	            //String enteredText = projectText.getText();
	            //System.out.println("User entered: " + enteredText);
	        	SaveData temp = new SaveData(logNumber, date, startTime, endTime, deltaTime, lifeCycleText.getText(), effortCatText.getText(), planText.getText(), projectText.getText());
	        	LogList.add(temp);
	        	lifeCycleText.setText("");
	        	effortCatText.setText("");
	        	planText.setText("");
	        	
	        	//write list to file
	        	
	        	FileWriter fileWriter;
	        	try {
					fileWriter = new FileWriter("Logs.txt");
					PrintWriter printWriter = new PrintWriter(fileWriter);
	          	    //printWriter.printf("|" + logNumber + "|" + date + "|" + startTime + "|" + endTime + "|" + deltaTime + "|" + lifeCycleText.getText() + "|" + effortCatText.getText() + "|" + planText.getText() + "|");	
					for(SaveData element : LogList) {
						printWriter.print("|" + element.getLogNumber() + "|" + element.getDate() + "|" + element.getStartTime() + "|" + element.getEndTime() + "|" + element.getDeltaTime() + "|" + element.getLifeCycleStep() + "|" + element.getEffortCategory() + "|" + element.getPlan() + "|\n");
					}
	          	    printWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	    
	        	
	        });
	        
	        edit.setOnAction(event -> {
	        	//load edit console
	        	primaryStage.setScene(editScene);
	        	primaryStage.setTitle("Edit Console");
	        });

	        
	        backToLogs.setOnAction(event -> {
	        	primaryStage.setScene(logScene);
	        	primaryStage.setTitle("Effort Console");
	        });
	        
	        loadData.setOnAction(event -> {
	        	for(SaveData element : LogList) {
	        		if (element.getLogNumber() == Integer.parseInt(numberToChangeText.getText())) {
	        			dateText.setText(element.getDate());
	        			startTimeText.setText(Double.toString(element.getStartTime()));
	        			stopTimeText.setText(Double.toString(element.getEndTime()));
	        			lifeCycEditText.setText(element.getLifeCycleStep());
	        			effortCatEditText.setText(element.getEffortCategory());
	        		}
	        	}
	        });
	        
	        clearLogs.setOnAction(event -> {
	        	LogList.clear();
	        	FileWriter fileWriter;
	        	try {
					fileWriter = new FileWriter("Logs.txt");
					PrintWriter printWriter = new PrintWriter(fileWriter);
	          	    
	          	    printWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        });
	        
	        deleteLog.setOnAction(event -> {
	        	for(SaveData element : LogList) {
	        		if(element.getLogNumber() == Integer.parseInt(numberToChangeText.getText())) {
	        			LogList.remove(element.getLogNumber()-1);
	        		}
	        	}
	        	
	        	int i = 1;
	        	
	        	for(SaveData element : LogList) {
	        		if(element.getLogNumber() != i) {
	        			element.setLogNumber(i);
	        			
	        		}
	        		i += 1;
	        	}
	        	
	        	dateText.setText("");
    			startTimeText.setText("");
    			stopTimeText.setText("");
    			lifeCycEditText.setText("");
    			effortCatEditText.setText("");
	        	
	        	FileWriter fileWriter;
	        	try {
					fileWriter = new FileWriter("Logs.txt");
					PrintWriter printWriter = new PrintWriter(fileWriter);
	          	    //printWriter.printf("|" + logNumber + "|" + date + "|" + startTime + "|" + endTime + "|" + deltaTime + "|" + lifeCycleText.getText() + "|" + effortCatText.getText() + "|" + planText.getText() + "|");	
					for(SaveData element : LogList) {
						printWriter.print("|" + element.getLogNumber() + "|" + element.getDate() + "|" + element.getStartTime() + "|" + element.getEndTime() + "|" + element.getDeltaTime() + "|" + element.getLifeCycleStep() + "|" + element.getEffortCategory() + "|" + element.getPlan() + "|\n");
					}
	          	    printWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	
	        });
	        
	        updateLog.setOnAction(event ->{
	        	for(SaveData element : LogList) {
	        		if(element.getLogNumber() == Integer.parseInt(numberToChangeText.getText())) {
	        			element.setDate(dateText.getText());
	        			element.setStartTime(Double.parseDouble(startTimeText.getText()));
	        			element.setEndTime(Double.parseDouble(stopTimeText.getText()));
	        			element.setDeltaTime(element.getEndTime()- element.getStartTime());
	        			element.setLifeCycleStep(lifeCycEditText.getText());
	        			element.setEffortCategory(effortCatEditText.getText());
	        		}
	        		
	        		FileWriter fileWriter;
		        	try {
						fileWriter = new FileWriter("Logs.txt");
						PrintWriter printWriter = new PrintWriter(fileWriter);
		          	    //printWriter.printf("|" + logNumber + "|" + date + "|" + startTime + "|" + endTime + "|" + deltaTime + "|" + lifeCycleText.getText() + "|" + effortCatText.getText() + "|" + planText.getText() + "|");	
						for(SaveData element2 : LogList) {
							printWriter.print("|" + element2.getLogNumber() + "|" + element2.getDate() + "|" + element2.getStartTime() + "|" + element2.getEndTime() + "|" + element2.getDeltaTime() + "|" + element2.getLifeCycleStep() + "|" + element2.getEffortCategory() + "|" + element2.getPlan() + "|\n");
						}
		          	    printWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
		        	dateText.setText("");
	    			startTimeText.setText("");
	    			stopTimeText.setText("");
	    			lifeCycEditText.setText("");
	    			effortCatEditText.setText("");
	    			numberToChangeText.setText("");
	    			
		        	
	        	}
	        });
	        
	        splitLog.setOnAction(event ->{
	        	for(SaveData element : LogList) {
	        		if(element.getLogNumber() == Integer.parseInt(numberToChangeText.getText())) {
	        			SaveData newElement = new SaveData(element.getLogNumber(), element.getDate(), element.getStartTime(), element.getEndTime(), element.getDeltaTime(), element.getLifeCycleStep(), element.getEffortCategory(), element.getPlan(), element.getProject());
	        			LogList.add(element.getLogNumber(), newElement);
	        			break;
	        		}
	        	}
	        	int i = 1;
	        	
	        	for(SaveData element : LogList) {
	        		if(element.getLogNumber() != i) {
	        			element.setLogNumber(i);
	        			
	        		}
	        		i += 1;
	        	}
	        	
	        	numberToChangeText.setText("");
	        	dateText.setText("");
    			startTimeText.setText("");
    			stopTimeText.setText("");
    			lifeCycEditText.setText("");
    			effortCatEditText.setText("");
	        	
	        	FileWriter fileWriter;
	        	try {
					fileWriter = new FileWriter("Logs.txt");
					PrintWriter printWriter = new PrintWriter(fileWriter);
	          	    //printWriter.printf("|" + logNumber + "|" + date + "|" + startTime + "|" + endTime + "|" + deltaTime + "|" + lifeCycleText.getText() + "|" + effortCatText.getText() + "|" + planText.getText() + "|");	
					for(SaveData element : LogList) {
						printWriter.print("|" + element.getLogNumber() + "|" + element.getDate() + "|" + element.getStartTime() + "|" + element.getEndTime() + "|" + element.getDeltaTime() + "|" + element.getLifeCycleStep() + "|" + element.getEffortCategory() + "|" + element.getPlan() + "|\n");
					}
	          	    printWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        });
	        // Show the stage
	        primaryStage.show();
    	});
    	//====================================================================================
    	
    	//WILSON CODE=========================================================================
    	planningPokerBtn.setOnAction(mainlineEvent -> {
    		//Label and box for naming a defect
    		   Label attributeLabel = new Label("Define or update Current Defect attributes");
    	        Label defectName = new Label("Defect Name:");
    	        TextField defectNameText = new TextField();

    	        Label symptomsLabel = new Label("Defect Symptoms or Cause/Resolution");
    	        TextField symptomsText = new TextField();
    			
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
    	                	defectNameText.setText(loadedName);
    	                	symptoms = loadedSymptoms;
    	                	symptomsText.setText(loadedSymptoms);
    	                	
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
    	            	catch (FileNotFoundException err) {
    	        	      err.printStackTrace();
    	        	    }
    	            }
    	        };
    	        loadButton.setOnAction(loadEvent);
    	        
    	       
    	        
    	        VBox root = new VBox(5);  
    	        root.setPadding(new Insets(10,10,10,10));
    	        root.setAlignment(Pos.TOP_CENTER);
    	        primaryStage.setScene(new Scene(root, 600, 750));
    	        primaryStage.setTitle("PlanningPokerSetup");  
    	        
    	        root.getChildren().addAll(projectCombo,defectCombo,statusButton,stepInjectCombo,stepRemoveCombo,defectCategoryCombo,fixDefectCombo,newDefectButton,updateDefectButton,deleteButton,saveButton,loadButton);
    	        primaryStage.show();  
    	});
    	//================================================================================
    	
    	//BRANDON Code====================================================================
    	planningPokerOtherBtn.setOnAction(mainlineEvent -> { 
    		 projectListView = new ListView<>(projects);
    	        projectListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    	        Button loadButton = new Button("Load Data");
    	        loadButton.setOnAction(e -> loadDataFromCSV());

    	        Button generateEstimateButton = new Button("Generate Estimate");
    	        generateEstimateButton.setOnAction(e -> generateEstimate());

    	        VBox leftPanel = new VBox(loadButton, projectListView, generateEstimateButton);

    	        cardContainer = new HBox();
    	        createCards();

    	        HBox root = new HBox(leftPanel, cardContainer);

    	        Scene scene = new Scene(root, 800, 600);
    	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	        primaryStage.setScene(scene);
    	        primaryStage.show();
    	});
    	//================================================================================
    	StackPane root = new StackPane();
    	HBox mainlineHBox = new HBox();
    	mainlineHBox.setAlignment(Pos.CENTER);
    	Label mainlineHeader = new Label("mainline");
        mainlineHeader.setFont(Font.font("Arial", FontWeight.BOLD, 30));
    	mainlineHBox.getChildren().addAll(effortLoggerConsoleBtn, defectLogConsoleBtn, effortLogEditorBtn, planningPokerBtn, planningPokerOtherBtn);
    	root.getChildren().addAll(mainlineHeader, mainlineHBox);
    	root.setAlignment(Pos.TOP_CENTER);
    	primaryStage.setScene(new Scene(root, 600, 600));
    	primaryStage.show();
    	
    	
    }
}

 
 
 