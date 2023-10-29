//Author: Caleb Patterson
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;

//import java.awt.Color;
//import java.awt.Insets;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class EffortLoggerV2 extends Application {
	private LocalDateTime startTime;
	private LocalDateTime stopTime;
	private String currentTime;
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
    	
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
        	startTime = LocalDateTime.now();
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
            long minutesElapsed = ChronoUnit.MINUTES.between(startTime, stopTime); // Duration in minutes
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
                writer = new PrintWriter(file);

                // Writes log data to text file
                writer.println("Date: " + currentDate
                		+ "\nTime: " + currentTime
                		+ "\nTime Elapsed: " + minutesElapsed
                		+ "\nProject: " + projectSelected
                		+ "\nLife Cycle Step: " + lifeCycleSelected
                		+ "\nEffort Category: " + effortSelected
                		+ ", " + subEffortSelected);
            } catch (FileNotFoundException e) {
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
        Button EffortLogBtn = new Button("EffortLogBtn");
        Button DefectLogConsoleBtn = new Button("DefectLogConsoleBtn");
        Button DefinitionsBtn = new Button("DefinitionsBtn");
        Button EffortDefectLogsBtn = new Button("EffortDefectLogsBtn");
        //---------------------------------------------------
        HBox UIMenuHBox = new HBox();
        UIMenuHBox.getChildren().addAll(EffortLogBtn, DefectLogConsoleBtn, DefinitionsBtn, EffortDefectLogsBtn);
        UIMenuHBox.setSpacing(20);
        
        VBox vbox = new VBox();   
        vbox.getChildren().addAll(hb, redPane, startActDescription, startActivityBtn, listVBox, stopActDescription, stopActivityBtn, UIMenuDescription, UIMenuHBox, timeLabel); 
        vbox.setSpacing(16); 
        vbox.setPadding(new Insets(0, 0, 0, 12));
        
        primaryStage.setScene(new Scene(vbox, 600, 600));
        primaryStage.show();
    }
}

 
 
 