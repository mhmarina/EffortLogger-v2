import javafx.scene.control.TextArea;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Logs extends VBox {
	
	public final int MIN_HEIGHT = 480;
    private Button reload;
    HBox searchBar;
    TextField search;
    private VBox logsContainer;
    private ScrollPane scrollPane;
    ImageView searchIcon;
    TextArea backlog;
    HBox body;

    public Logs(ArrayList<EffortLog> effortLogs) {
    	VBox right = new VBox();
    	Label backLogTitle = new Label("Projects Backlog: ");
        backLogTitle.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
    	backlog = new TextArea();
    	backlog.setText(ProjectsBacklogTableOps.readProjectsBacklog());
    	right.getChildren().addAll(backLogTitle, backlog);
    	backlog.setMinHeight(MIN_HEIGHT);
    	backlog.setPadding(new Insets(10));
    	
        reload = new Button("Load Logs");
        searchIcon = new ImageView(new Image("magnifying_glass.png")); 
        searchIcon.setFitHeight(20);
        searchIcon.setFitWidth(20);
        searchBar = new HBox(8);
        search = new TextField();
        search.setPromptText("Search list of Effort Logs...");
        search.setPrefWidth(500);
        reload.setOnAction(new LoadLogsHandler());
        // display inside vbox
        logsContainer = new VBox();
        searchBar.getChildren().addAll(searchIcon, search, reload);
        
        Label logsTitle = new Label("EffortLogs: ");
        logsTitle.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        VBox left = new VBox();
        
        scrollPane = new ScrollPane(logsContainer);
        left.getChildren().addAll(logsTitle, scrollPane);
        scrollPane.setMinHeight(MIN_HEIGHT);
        
        setPadding(new Insets(10));
        logsContainer.setPadding(new Insets(10));
                
    	scrollPane.setMinWidth(380);
        setSpacing(10);
        
        body = new HBox();
        body.getChildren().addAll(left, right);
        body.setSpacing(20);
        getChildren().addAll(searchBar, body);
    }

    private class LoadLogsHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            logsContainer.getChildren().clear();
            System.out.println(search.getText());
            //read from the database
            if(search.getText().isEmpty()) {
            Text logText = new Text(EffortLogTableOps.readEffortLog());
            logsContainer.getChildren().add(logText);
            }//display all logs if search bar is empty
            else {
            	String target = search.getText();
                Text logText = new Text(EffortLogTableOps.searchEffortLog(target));
                if(logText.getText().equals("")) {
                	logText.setText("No matching patterns found.");
                }
                logsContainer.getChildren().add(logText);
            }
        }
    }
}
