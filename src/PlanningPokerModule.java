import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

public class PlanningPokerModule extends VBox {
    private TextArea historicalDataTextArea;
    private ComboBox<String> estimationComboBox;
    private TextArea notesTextArea;

    public PlanningPokerModule() {
        // Create the user-friendly interface components
        Label estimationLabel = new Label("Estimation:");
        estimationComboBox = new ComboBox<>();
        estimationComboBox.getItems().addAll("1", "2", "3", "5", "8", "13", "20", "40", "100");
        estimationComboBox.setPromptText("Select an estimation");

        Label notesLabel = new Label("Notes:");
        notesTextArea = new TextArea();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> handleSubmission());

        getChildren().addAll(estimationLabel, estimationComboBox, notesLabel, notesTextArea, submitButton);

        // Create historical data display (you can use the TextArea for this)
        historicalDataTextArea = new TextArea();
        historicalDataTextArea.setEditable(false);
        historicalDataTextArea.setWrapText(true);
        historicalDataTextArea.setPrefHeight(150);

        // Add the historicalDataTextArea to the layout
        getChildren().add(historicalDataTextArea);

        // Set up the layout
        setSpacing(10);
        setPadding(new Insets(10));
    }

    private void handleSubmission() {
        String selectedEstimation = estimationComboBox.getValue();
        String notes = notesTextArea.getText();
        // Handle the estimation and notes
        // You can store this information and add it to historical data

        // Append the new data to the historical data TextArea
        String historicalData = historicalDataTextArea.getText();
        historicalData += "Estimation: " + selectedEstimation + "\nNotes: " + notes + "\n\n";
        historicalDataTextArea.setText(historicalData);

        // Clear the input fields
        estimationComboBox.setValue(null);
        notesTextArea.clear();
    }
}
