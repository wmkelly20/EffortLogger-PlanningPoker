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
	save into a new file
	load from file

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

	//class for user stories
	public class userStory {
		private String name;
		private String description;
	
		public userStory(String name, String description) {
			this.name = name;
			this.description = name;
		}
		
		//List of Setters for user class
		public void setName(String name) {
			this.name=name;
		}
		
		public void setDescription(String description) {
			this.description=description;
		}
		
		//List of getters for user class
		public String getName() {
			return name; 
		}
		
		public String getDescription() {
			return description; 
		}
		
	}
	    
	//Core element definitions
    private ObservableList<userStory> userStoriesDisplay = FXCollections.observableArrayList();    
    private ComboBox<String> userStoryCombo = new ComboBox<String>(userStoriesDisplay);

    
    private Button loadButton = new Button("Load Data");
    private Button saveButton = new Button("Save Data");

    
    private String name = "";
    private String description = "";
	
	@Override public void start(Stage primaryStage) 
	{  
	
		userStoryCombo.setValue(description);
		
		
		
        Label storyName = new Label("Story:");
        TextField storyNameText = new TextField();
        //Listener for the storyNameBox. When the string within the field has changed
        //It will update the name variable within the name variable which is later stored in observable list
        //Input must be string
        storyNameText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                name=newValue;
            }
        });
        
        Label descriptionLabel = new Label("Description:");
        TextField descriptionText = new TextField();
        //Input of description, same as the description of a planning poker session
        descriptionText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                description=newValue;
            }
        });
        
        
        //save data button
        EventHandler<ActionEvent> saveEvent = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
            	try
            	{
            		//First create the text file
		    	    File userStoryFile = new File("userStory.txt");
		    	    
		    	    //Check if file is empty
	    	        if (!userStoryFile.exists())
	    	        {
	    	        	userStoryFile.createNewFile();
	    	        }
	    	        
		    	    //Next write data onto text file
	    	        FileWriter myWriter = new FileWriter("userStory.txt", true);
	    	        
	    	        //Print the user story
	    	        myWriter.write(name+"\n");
	    	        myWriter.write(description+"\n");
	    	        System.out.println(name);
	    	       
	    	        myWriter.close();
        	    } 
            	catch (IOException err) 
            	{
            		err.printStackTrace();
            	}
            }
        }; 
        saveButton.setOnAction(saveEvent);       
	    	
        //load data button
        EventHandler<ActionEvent> loadEvent = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
        		try {
	            		File userStoryFile = new File("userStory.txt");
	            		Scanner userStoryReader = new Scanner(userStoryFile);
	 
	            		while(userStoryReader.nextLine()!=null) {
		            		//Read in and set all of user parameters
		        	    	String loadedName = userStoryReader.nextLine();
		        	    	String loadedDescription = userStoryReader.nextLine();
		        	    	
		        	    	userStory story = new userStory(loadedName, loadedDescription);
		        	    	userStoriesDisplay.addAll(story);

		        	    	
        	    	
	            		}
        	    	//defectCombo.setItems(projectDefectsBusinessNames);
                	//fixDefectCombo.setItems(projectDefectsBusinessNames);
        	    	
        	    	
        	    	//defectCombo.setValue(loadedName);
                	//name = loadedName;
                	//defectNameText.setText(loadedName);
                	//description = loadedDescription;
                	//descriptionsText.setText(loadedDescription);
	        	    
      	      userStoryReader.close();
      	    } 
          	catch (FileNotFoundException err) {
      	      err.printStackTrace();
      	    }
          }
      };
      loadButton.setOnAction(loadEvent);
        
        
        Label titleLabel = new Label("Planning Poker:");
        titleLabel.setFont(new Font("Arial", 24));
        
        Label descLabel = new Label("Please enter a user story below, or load one from the story logs.");
        
        
        VBox userInput = new VBox(5);          
        userInput.setPadding(new Insets(10,10,10,10));
        userInput.setSpacing(10);
        userInput.setAlignment(Pos.TOP_CENTER);
        primaryStage.setScene(new Scene(userInput, 600, 750));
        primaryStage.setTitle("Planning Poker Setup");  
        
        
        userInput.getChildren().addAll(
        		titleLabel,descLabel,storyName,storyNameText,descriptionLabel,descriptionText,loadButton, saveButton
        		);
        primaryStage.show();  

	}
	
	public static void main(String[] args) {
        launch(args);  
	}

}
