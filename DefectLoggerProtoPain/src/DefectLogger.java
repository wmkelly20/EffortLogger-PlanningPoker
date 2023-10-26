//package inputPrototype;




import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

 
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
            
            
            //Here we should also load in project defect data from database
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
                Defect newDefect = new Defect(project,name,false,symptoms,stepInject,stepRemove,defectCategory,null);
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
        

        VBox root = new VBox(5);
        root.setPadding(new Insets(10,10,10,10));
        root.setAlignment(Pos.TOP_CENTER);
        
        primaryStage.setScene(new Scene(root, 600, 750));
        primaryStage.show();
        
        //Add children to root
        root.getChildren().addAll(defectConsoleLabel,projectLabel,projectCombo,defectAmount,defectLabel,defectCombo,attributeLabel,statusLabel,statusButton,defectName,defectNameText,symptomsLabel,symptomsText,newDefectButton,updateDefectButton,stepInjectLabel,stepInjectCombo,stepRemoveLabel,stepRemoveCombo,defectCategoryLabel,defectCategoryCombo,fixDefectLabel,fixDefectCombo);
    }
    
    //Other class methods
    public String getProjectComboValue() {
        return project;
    }
}