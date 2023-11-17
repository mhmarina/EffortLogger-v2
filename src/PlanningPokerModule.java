import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import java.util.List;
import java.util.ArrayList; // For demo purposes

public class PlanningPokerModule extends VBox {
    private ComboBox<String> scaleSelector;
    private ComboBox<String> votingOptions;
    private TextArea commentsArea;
    private TextField projectNameField;
    private VBox textInput;
    private Button submitButton;
    private Button finalizeEntry;
    private Button generateAvg;
    private HBox buttonsRow;
    private HBox randomEstimate;
    private TextField generatedEstimate;
    private ListView<String> historyListView; // For displaying historical data
    private Button loadHistoryButton;        // Button to load history
    private ObservableList<String> fibonacciOptions = FXCollections.observableArrayList("0", "1", "2", "3", "5", "8", "13", "21");
    private ObservableList<String> tshirtOptions = FXCollections.observableArrayList("XS", "S", "M", "L", "XL"); // can't parse these into ints for average calculation so I have to remove them 
    private ObservableList<String> sequentialOptions = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    private ObservableList<String> cardOptions = FXCollections.observableArrayList("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King");
    public PlanningPokerModule() {
        initializeComponents();
    }

    private void initializeComponents() {
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        scaleSelector = new ComboBox<>();
        scaleSelector.getItems().addAll("Fibonacci", "T-Shirt", "Sequential", "Cards");
        scaleSelector.setValue("Fibonacci"); // Default scale

        votingOptions = new ComboBox<>();
        votingOptions.setItems(fibonacciOptions); // Default to Fibonacci options

        textInput = new VBox();
        projectNameField = new TextField();
        projectNameField.setPromptText("Type project name here...");
        commentsArea = new TextArea();
        commentsArea.setPromptText("Enter your comments here...");
        textInput.getChildren().addAll(projectNameField, commentsArea);

        submitButton = new Button("Submit");
        submitButton.setOnAction(this::handleSubmit);

        scaleSelector.setOnAction(event -> updateVotingOptions());

        // Initialize history ListView
        historyListView = new ListView<>();
        historyListView.setPrefHeight(200); // Set preferred height

        
        buttonsRow = new HBox();
        // Initialize Load History Button
        loadHistoryButton = new Button("Load History");
        loadHistoryButton.setOnAction(this::handleLoadHistory);
        
        randomEstimate = new HBox();
        generateAvg = new Button("Generate Estimate");
        generateAvg.setOnAction(new handleGenerate());
        generatedEstimate = new TextField();
        generatedEstimate.setPromptText("0");
        generatedEstimate.setMinWidth(2); // Set a maximum width
        randomEstimate.getChildren().addAll(generateAvg, generatedEstimate);
        randomEstimate.setSpacing(0);
        
        finalizeEntry = new Button("Finalize Project");
        finalizeEntry.setOnAction(new handleFinalizeEntry());
        
        buttonsRow.getChildren().addAll(loadHistoryButton, randomEstimate, finalizeEntry);
        buttonsRow.setSpacing(25);

        this.getChildren().addAll(scaleSelector, votingOptions, textInput, submitButton, buttonsRow, historyListView);
    }

    private class handleFinalizeEntry implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			String projectName = projectNameField.getText();
			int storyPoints = Integer.parseInt(generatedEstimate.getText());
			if(storyPoints == 0) {
				//print error message: must generate or input average
			}
			// insert into backlog database
			// clear current planning poker (session) database
		}	
    }
    
    private class handleGenerate implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			ArrayList<Integer> points = PlanningPokerTableOps.readPlanningPokerPoints();
			int sum = 0;
			int average = 0;
			System.out.println("initial sum " + sum);
			for(int i = 0; i < points.size(); i++) {
				System.out.println(points.get(i));
				System.out.print(", current sum: " + sum + "\n");
				sum = sum + points.get(i);
			}
			average = sum / points.size();
			generatedEstimate.setText(Integer.toString(average));
		}	
    }
    
    private void updateVotingOptions() {
        String selectedScale = scaleSelector.getValue();
        switch (selectedScale) {
            case "Fibonacci":
                votingOptions.setItems(fibonacciOptions);
                break;
            case "T-Shirt":
                votingOptions.setItems(tshirtOptions);
                break;
            case "Sequential":
                votingOptions.setItems(sequentialOptions);
                break;
            case "Cards":
                votingOptions.setItems(cardOptions);
                break;
            default:
                break;
        }
    }

    private void handleSubmit(ActionEvent event) {
        String selectedOption = votingOptions.getValue();
        String comments = commentsArea.getText();
        String projectName = projectNameField.getText();
        System.out.print(projectName);
        // Logic to store these details in the database
        PlanningPokerTableOps.insertPlanningPokerData(selectedOption, comments); // insertPlanningPokerData is a method in PlanningPokerTableOps.java

        // Clearing the fields after submission
        votingOptions.getSelectionModel().clearSelection();
        commentsArea.clear();

        // Show confirmation dialog
        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setTitle("Submission Confirmed");
        confirmationAlert.setHeaderText("Vote Submitted");
        String content = String.format("You voted: %s\nComments: %s", selectedOption, comments);
        confirmationAlert.setContentText(content);
        confirmationAlert.showAndWait();
    }

    private void handleLoadHistory(ActionEvent event) {
        // Fetch historical data and update the historyListView
        String historyData = PlanningPokerTableOps.readPlanningPokerData(); // readPlanningPokerData is a method in PlanningPokerTableOps.java
        ObservableList<String> items = FXCollections.observableArrayList(historyData);
        historyListView.setItems(items);
    }

}
