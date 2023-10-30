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
		//List of Setters for defect class
			
	}
	    

    private Button loadButton = new Button("Load Data");
    
    private String name = "";
    private String symptoms = "";
	
	@Override public void start(Stage primaryStage) 
	{  
	
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
        //Input of symptoms, same as the description of a planning poker session
        symptomsText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                symptoms=newValue;
                
            }
        });
       
        Label titleLabel = new Label("Planning Poker:");
        titleLabel.setFont(new Font("Arial", 24));
        
        Label descLabel = new Label("Please enter a user story below, or load one from the defect logs.");
        
        
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
