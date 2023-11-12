import java.util.ArrayList;

import org.h2.engine.User;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	
	/***variables for login***/
	private GridPane grid;
	private Text title;
	private Label userLabel;
	private TextField userTextField;
	private Label passwordLabel;
	private PasswordField passwordField;
	private Button loginButton;
	private Button createNewAccount;
	private Scene scene_login;
	private boolean happen = false;
	private String username;
	private String password;
	/***end of variables for login***/

	@Override
	public void start(Stage primaryStage) throws Exception {
    
		// TODO Auto-generated method stub
		//Establishing a connection to the database
		DatabaseConnection.getConnection();
		//creating a table in the database
		DB_TableCreation.createTableEffortLog();
		DB_TableCreation.createTableUserAccounts();
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
			
	/***login portion***/	
		 primaryStage.setTitle("Login");

	     grid = new GridPane();
	     grid.setAlignment(Pos.CENTER);
	     grid.setHgap(10);
	     grid.setVgap(10);
	     grid.setPadding(new Insets(25, 25, 25, 25));

	     title = new Text("Please Login");
	     title.setFont(Font.font("System", 20));
	     grid.add(title, 0, 0, 2, 1);

	     userLabel = new Label("Username:");
	     grid.add(userLabel, 0, 1);

	     userTextField = new TextField();
	     grid.add(userTextField, 1, 1);

	     passwordLabel = new Label("Password:");
	     grid.add(passwordLabel, 0, 2);

	     passwordField = new PasswordField();
	     grid.add(passwordField, 1, 2);

	     loginButton = new Button("Login");
	     grid.add(loginButton, 1, 3);
	        
	     createNewAccount = new Button("Create Account");
	     grid.add(createNewAccount, 1, 4);

	        loginButton.setOnAction(event -> {
	            String username = userTextField.getText();
	            String password = passwordField.getText();

	            if (AccountCreator.AccountAuthenticate(username, password)) {
	                Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.setTitle("Success");
	                alert.setHeaderText(null);
	                alert.setContentText("You've successfully logged in!");
	                alert.showAndWait();
	            
	/***redirect to main console***/
		        	root = new StackPane();
			        // Create a Scene
			        scene = new Scene(root, 800, 600);
			        String cse = this.getClass().getResource("application.css").toExternalForm();
			        scene.getStylesheets().add(cse);
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
			        PlanningPokerModule pPoker = new PlanningPokerModule();
			        planningPoker.setContent(pPoker);
			        
			        logs = new Tab();
			        logs.setText("Logs");
			        Logs logsPane = new Logs(effortLogs);
			        logs.setContent(logsPane);
			        
			        tabPane = new TabPane();
			        tabPane.getTabs().addAll(mainTab, logs, planningPoker);
			        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			        root.getChildren().add(tabPane);
	 /***end of redirect to main console***/
	        		
	            } else {
	                Alert alert = new Alert(Alert.AlertType.ERROR);
	                alert.setTitle("Invalid Credentials");
	                alert.setHeaderText(null);
	                alert.setContentText("Your username and password do not match.");
	                alert.showAndWait();
	            }
	        });
	        
	        createNewAccount.setOnAction(event -> { // open new window to create an account
	        	AccountCreatorPane createAccount = new AccountCreatorPane();
	            createAccount.start(new Stage());
	        });
	        
	        Scene scene_login = new Scene(grid, 300, 275);
	        primaryStage.setScene(scene_login);
	        primaryStage.show();		
		
	}
}
