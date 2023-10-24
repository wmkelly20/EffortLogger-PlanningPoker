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
 
public class DefectLogger extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
    	
        primaryStage.setTitle("Defect Logs");
        
        //Create the label
        Label defectConsoleLabel = new Label("Defect Console");
        defectConsoleLabel.setFont(new Font("Arial", 30));
        defectConsoleLabel.setStyle("-fx-border-color: black;");
        defectConsoleLabel.setPadding(new Insets(20));
        Label projectLabel = new Label("1. Select the Project:");
        
        
        // Create a combo box
        ObservableList<String> projectOptions = FXCollections.observableArrayList("Development Project","Business Project");
        ComboBox projectCombo = new ComboBox(projectOptions);
       

        

        VBox root = new VBox(10);
        root.setPadding(new Insets(10,10,10,10));
        root.setAlignment(Pos.TOP_CENTER);
        

        primaryStage.setScene(new Scene(root, 600, 700));
        primaryStage.show();
        
        //Add children to root
        root.getChildren().addAll(defectConsoleLabel,projectLabel,projectCombo);
        

        

    }
}