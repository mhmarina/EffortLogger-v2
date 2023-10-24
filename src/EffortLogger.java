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
	
	public ArrayList<EffortLog> effortLogs;
	private StackPane root;
	private Scene scene;
	private Tab mainTab;
	private Tab editor;
	private Tab defects;
	private Tab planningPoker;
	private Tab logs;
	private TabPane tabPane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// Create a StackPane as the root node
        root = new StackPane();
        // Create a Scene
        scene = new Scene(root, 800, 600);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        // Set the scene for the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("EffortLogger 2.0");
        primaryStage.show();
        
        effortLogs = new ArrayList<>();

        mainTab = new Tab(); // this tab will contain the clock function
        mainTab.setText("Main Console");
        MainConsolePane mainPane = new MainConsolePane(effortLogs);
        mainTab.setContent(mainPane);
        
        editor = new Tab();
        editor.setText("Editor");
        
        defects = new Tab();
        defects.setText("Defect Console");
        
        planningPoker = new Tab();
        planningPoker.setText("Planning Poker");
        
        logs = new Tab();
        logs.setText("Logs");
        Logs logsPane = new Logs(effortLogs);
        logs.setContent(logsPane);
        
        tabPane = new TabPane();
        tabPane.getTabs().addAll(mainTab, logs, planningPoker);
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        root.getChildren().add(tabPane);
        
	}
}
