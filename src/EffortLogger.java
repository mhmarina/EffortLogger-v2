import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EffortLogger extends Application{
	public static void main(String [] args) {
		System.out.println("Hello World!");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// Create a StackPane as the root node
        StackPane root = new StackPane();
        // Create a Scene
        Scene scene = new Scene(root, 800, 600);
        // Set the scene for the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("EffortLogger 2.0");
        primaryStage.show();
	}
}
