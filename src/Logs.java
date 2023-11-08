import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

    private Button reload;
    HBox searchBar;
    TextField search;
    private VBox logsContainer;
    private ScrollPane scrollPane;
    ImageView searchIcon;

    public Logs(ArrayList<EffortLog> effortLogs) {
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
        
        scrollPane = new ScrollPane(logsContainer);
        setPadding(new Insets(10));
        logsContainer.setPadding(new Insets(10));
    	scrollPane.setId("scrollpane");
        setSpacing(10);
        getChildren().addAll(searchBar, scrollPane);
    }

    private class LoadLogsHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
        	int numLogs = 0;
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
