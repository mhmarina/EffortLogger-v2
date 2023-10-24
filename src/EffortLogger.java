import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EffortLogger extends Application{
	public static void main(String [] args) {
		launch(args);
	}
	
	private ArrayList<EffortLog> effortLogs;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// Create a StackPane as the root node
        StackPane root = new StackPane();
        // Create a Scene
        Scene scene = new Scene(root, 800, 600);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        // Set the scene for the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("EffortLogger 2.0");
        primaryStage.show();
        
        effortLogs = new ArrayList<>();

        
        Tab mainTab = new Tab(); // this tab will contain the clock function
        mainTab.setText("Main Console");
        MainConsolePane mainPane = new MainConsolePane(effortLogs);
        mainTab.setContent(mainPane);
        Tab editor = new Tab();
        editor.setText("Editor");
        Tab defects = new Tab();
        defects.setText("Defect Console");
        Tab planningPoker = new Tab();
        planningPoker.setText("Planning Poker");
        Tab logs = new Tab();
        logs.setText("Logs");
        Logs logsPane = new Logs(effortLogs);
        logs.setContent(logsPane);
        
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(mainTab, logs, planningPoker);
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        root.getChildren().add(tabPane);
        
	}
}
