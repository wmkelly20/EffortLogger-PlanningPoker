import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class DefectLogger extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
    	
        primaryStage.setTitle("Risk-Reduction Prototype");
        Button btn = new Button();
        btn.setText("SHARE");
        btn.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                System.out.println("Shared!");
            }
        });

        double checkboxWidth = 150;
        CheckBox cb1 = new CheckBox("Include Name");
        cb1.setIndeterminate(false);
        CheckBox cb2 = new CheckBox("Include Team");
        cb2.setIndeterminate(false);
        CheckBox cb3 = new CheckBox("Include Title");
        cb3.setIndeterminate(false);
        CheckBox cb4 = new CheckBox("Include Metadata");
        cb4.setIndeterminate(false);
        cb1.setMinWidth(checkboxWidth);
        cb2.setMinWidth(checkboxWidth);
        cb3.setMinWidth(checkboxWidth);
        cb4.setMinWidth(checkboxWidth);
        

        VBox root = new VBox(10);
        root.setPadding(new Insets(10,10,10,10));
        root.setAlignment(Pos.TOP_CENTER);
        
        //root.getChildren().add(tb1);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
        
        //Define label and add everything to vbox
        Label emptySpace = new Label();
        emptySpace.setMinHeight(80);
        root.getChildren().addAll(cb1,cb2,cb3,cb4,emptySpace,btn);

        

    }
}