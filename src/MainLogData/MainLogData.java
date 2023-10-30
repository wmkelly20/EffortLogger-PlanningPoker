package MainLogData;
import java.util.LinkedList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Label;



public class MainLogData extends Application{
	
	 private LinkedList<SaveData> LogList = new LinkedList<SaveData>();
	 private int logNumber = 0;
	 private String date = "Date";
	 private double startTime = 0; //need to talk to caleb
	 private double endTime = 0; //need to talk to caleb
	 private double deltaTime = 0; //need to talk to caleb
	 
	 public static void main(String[] args) {
	        launch(args);
	    }
	
	 public void start(Stage primaryStage) {
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
	    }

}
