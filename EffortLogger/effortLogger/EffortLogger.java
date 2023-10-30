package effortLogger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EffortLogger extends Application {

    private ObservableList<Project> projects = FXCollections.observableArrayList();
    private ListView<Project> projectListView;
    private HBox cardContainer;
    private Card selectedCard;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
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
    }

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

}

