import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.List;
import java.util.ArrayList; // For demo purposes

public class PlanningPokerModule extends VBox {
    private ComboBox<String> scaleSelector;
    private ComboBox<String> votingOptions;
    private TextArea commentsArea;
    private Button submitButton;
    private ListView<String> historyListView; // For displaying historical data
    private Button loadHistoryButton;        // Button to load history

    private ObservableList<String> fibonacciOptions = FXCollections.observableArrayList("0", "1", "2", "3", "5", "8", "13", "21");
    private ObservableList<String> tshirtOptions = FXCollections.observableArrayList("XS", "S", "M", "L", "XL");
    private ObservableList<String> sequentialOptions = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    private ObservableList<String> cardOptions = FXCollections.observableArrayList("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King");

    public PlanningPokerModule() {
        initializeComponents();
    }

    private void initializeComponents() {
        scaleSelector = new ComboBox<>();
        scaleSelector.getItems().addAll("Fibonacci", "T-Shirt", "Sequential", "Cards");
        scaleSelector.setValue("Fibonacci"); // Default scale

        votingOptions = new ComboBox<>();
        votingOptions.setItems(fibonacciOptions); // Default to Fibonacci options

        commentsArea = new TextArea();
        commentsArea.setPromptText("Enter your comments here");

        submitButton = new Button("Submit");
        submitButton.setOnAction(this::handleSubmit);

        scaleSelector.setOnAction(event -> updateVotingOptions());

        // Initialize history ListView
        historyListView = new ListView<>();
        historyListView.setPrefHeight(200); // Set preferred height

        // Initialize Load History Button
        loadHistoryButton = new Button("Load History");
        loadHistoryButton.setOnAction(this::handleLoadHistory);

        this.getChildren().addAll(scaleSelector, votingOptions, commentsArea, submitButton, loadHistoryButton, historyListView);
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

        // Logic to store these details in the database
        // For example: DatabaseConnection.getInstance().saveVote(selectedOption, comments);

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
        List<String> historyData = fetchHistoryData(); // Implement this method
        ObservableList<String> items = FXCollections.observableArrayList(historyData);
        historyListView.setItems(items);
    }

    private List<String> fetchHistoryData() {
        // Placeholder data for demonstration
        // Replace this with actual database fetching logic
        List<String> demoHistory = new ArrayList<>();
        
        return demoHistory;
    }
}
