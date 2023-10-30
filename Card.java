//package effortLogger;
package effortLoggerPackage;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class Card extends StackPane {
	private final int value;
    private boolean isSelected;
	
	public Card(int value) {
		this.value = value;
		Label label = new Label(String.valueOf(value));
		label.getStyleClass().add("card-label");
		this.getStyleClass().add("card");
		this.setAlignment(Pos.CENTER);
		this.getChildren().add(label);
	}
	
	public int getValue() {
		return value;
	}
	
	public void highlight() {
		setStyle("-fx-background-color: blue;");
		this.isSelected = true;
	}
	
	public void unhighlight() {
		setStyle("-fx-background-color: white;");
		this.isSelected = false;
	}
	
	public void setSelected(boolean selected) {
        this.isSelected = selected;
        if (selected) {
            setStyle("-fx-background-color: blue;");
        } else {
            setStyle("-fx-background-color: grey;");
        }
    }

}