//serves to build user interface of new create an account stage
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccountCreatorPane extends Application{
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,0,0);
			
			primaryStage.setTitle("Effort Logger 2.0 - Create Account");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			Text sceneTitle = new Text("Create Account");
	        sceneTitle.setFont(Font.font("System", 20));
			
			Label label = new Label("Enter Password: ");
			PasswordField textField = new PasswordField();
			textField.setMaxWidth(300);
			TilePane r = new TilePane();
			Label space1 = new Label(" ");
			Label space2 = new Label(" ");
			Label space3 = new Label(" ");
			Label space4 = new Label(" ");
			Label addAccStatus = new Label();
			
			Label enterF = new Label("Enter First Name: ");
			TextField firstnameField = new TextField();
			firstnameField.setMaxWidth(300);
			
			Label enterL = new Label("Enter Last Name: ");
			TextField lastnameField = new TextField();
			lastnameField.setMaxWidth(300);
			
			Label enterID = new Label("Enter ID: ");
			TextField idField = new TextField();
			idField.setMaxWidth(300);
			
			ToggleButton showPassword = new ToggleButton("Show Password");
			showPassword.setOnMousePressed(e -> {
			    textField.setPromptText(textField.getText());
			    textField.setText("");
			    textField.setDisable(true);
			    textField.setDisable(false);
			});
			showPassword.setOnMouseReleased(e -> {
			    textField.setText(textField.getPromptText());
			    textField.setPromptText("");
			});
			
			
			AccountCreator account = new AccountCreator();
			account.initialize();
	        
	        EventHandler<ActionEvent> exit = new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent e)
	            {
	            	primaryStage.close();
	            }
	        };

	        
	        Button check = new Button("Submit");
	        
	        Button proceedToLogin = new Button("Login");

	        proceedToLogin.setOnAction(exit);
			
			TilePane buttons = new TilePane(Orientation.HORIZONTAL);
			buttons.getChildren().add(check);
			buttons.getChildren().add(proceedToLogin);
			buttons.setAlignment(Pos.CENTER);
			
			TilePane passwordButtons = new TilePane(Orientation.HORIZONTAL);
			passwordButtons.getChildren().add(label);
			passwordButtons.getChildren().add(textField);
			passwordButtons.getChildren().add(showPassword);
			passwordButtons.setAlignment(Pos.CENTER);
			
			TilePane firstnameButtons = new TilePane(Orientation.HORIZONTAL);
			firstnameButtons.getChildren().add(enterF);
			firstnameButtons.getChildren().add(firstnameField);
			firstnameButtons.getChildren().add(space1);
			firstnameButtons.setAlignment(Pos.CENTER);
			
			TilePane lastnameButtons = new TilePane(Orientation.HORIZONTAL);
			lastnameButtons.getChildren().add(enterL);
			lastnameButtons.getChildren().add(lastnameField);
			lastnameButtons.getChildren().add(space2);
			lastnameButtons.setAlignment(Pos.CENTER);
			
			TilePane idButtons = new TilePane(Orientation.HORIZONTAL);
			idButtons.getChildren().add(enterID);
			idButtons.getChildren().add(idField);
			idButtons.getChildren().add(space3);
			idButtons.setAlignment(Pos.CENTER);
			
			r.getChildren().add(sceneTitle);
			r.getChildren().add(addAccStatus);
			r.getChildren().add(firstnameButtons);
			r.getChildren().add(lastnameButtons);
			r.getChildren().add(idButtons);
			r.getChildren().add(passwordButtons);
			r.getChildren().add(label);
			r.getChildren().add(buttons);
			
			r.setHgap(10);
			r.setVgap(10);
			
			Scene sc = new Scene(r, 700, 400);
			 
	        // set the scene
			primaryStage.setScene(sc);
			//show create an account stage
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}