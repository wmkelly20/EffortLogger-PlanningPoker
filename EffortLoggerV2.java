import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;

//import java.awt.Color;
//import java.awt.Insets;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
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
            LocalTime currentTime = LocalTime.now();
            timeLabel.setText(currentTime.format(formatter));
        }),
        new KeyFrame(Duration.seconds(1))
        );

        clock.setCycleCount(Timeline.INDEFINITE);
        //clock.play();
        //---------------------------------------------------------
        
        //Red Clock Stopped Tile--------------------------------
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
        Label startActDescription = new Label("1. When you start a new activity, press the 'Start an Activity' button.");
        Button startActivityBtn = new Button();
        startActivityBtn.setText("Start an Activity");
        startActivityBtn.setOnAction(event -> {
            System.out.println("Clock has begun!");
            clock.play();
            redPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            redPaneLabel.setText("Clock is running");
        });
        //-----------------------------------
        
        //Step 2---------------------------------------------
        Label attributesDescription = new Label("2. Select the project, life cycle step, effort category, and deliverable from the following lists: ");
        Label projectLabel = new Label("Project:");
        ComboBox<String> projectCombo = new ComboBox<>();
        projectCombo.getItems().addAll("Business Project", "Development Project");
        Label lifeCycleStepsLabel = new Label("Life Cycle Steps:");
        ComboBox<String> lifeCycleCombo = new ComboBox<>();
        lifeCycleCombo.getItems().addAll("Planning", "Information Gathering", "Information Understanding", "Verifying",
        		"Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting");
        Label effortCategory = new Label("Effort Category");
        ComboBox<String> effortCombo = new ComboBox<>();
        effortCombo.getItems().addAll("Plans", "Deliverables", "Interruptions", "Defects", "Others");
        ComboBox<String> plansCombo = new ComboBox<>();
        plansCombo.getItems().addAll("Project Plan", "Risk Management Plan", "Conceptual Design Plan", "Detailed Design Plan", "Implementation Plan");
        
        VBox listVBox = new VBox();
        listVBox.getChildren().addAll(attributesDescription,projectLabel, projectCombo, lifeCycleStepsLabel, lifeCycleCombo, effortCategory, effortCombo, plansCombo);
        //---------------------------------------------------
        
        //Step 3---------------------------------------------
        Label stopActDescription = new Label("3. Press the 'Stop this Activity' to generate an effort log entry using the attributes above.");
        Button stopActivityBtn = new Button();
        stopActivityBtn.setText("Stop this Activity");
        stopActivityBtn.setOnAction(event -> {
            System.out.println("Clock has stopped!");
            clock.stop();
            redPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            redPaneLabel.setText("Clock is stopped");
        });
        //---------------------------------------------------
        
        Label UIMenuDescription = new Label("4. Unless you are done for the day, it is best to perform steps 1 and 2 above before resuming work.");
        Button EffortLogBtn = new Button("EffortLogBtn");
        Button DefectLogConsoleBtn = new Button("DefectLogConsoleBtn");
        Button DefinitionsBtn = new Button("DefinitionsBtn");
        Button EffortDefectLogsBtn = new Button("EffortDefectLogsBtn");
        
        HBox UIMenuHBox = new HBox();
        UIMenuHBox.getChildren().addAll(EffortLogBtn, DefectLogConsoleBtn, DefinitionsBtn, EffortDefectLogsBtn);
        UIMenuHBox.setSpacing(20);
        
        VBox vbox = new VBox();   
        vbox.getChildren().addAll(hb, redPane, startActDescription, startActivityBtn, listVBox, stopActDescription, stopActivityBtn, UIMenuDescription, UIMenuHBox, timeLabel); 
        vbox.setSpacing(20);    
        
        primaryStage.setScene(new Scene(vbox, 400, 400));
        primaryStage.show();
    }
}

 
 
 