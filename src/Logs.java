import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Logs extends VBox {

    private Button reload;
    private VBox logsContainer;
    private ScrollPane scrollPane;

    public Logs(ArrayList<EffortLog> effortLogs) {
        reload = new Button("Load Logs");
        reload.setOnAction(new LoadLogsHandler(effortLogs));
        // display inside vbox
        logsContainer = new VBox();

        scrollPane = new ScrollPane(logsContainer);
        setPadding(new Insets(10));
        logsContainer.setPadding(new Insets(10));
    	scrollPane.setId("scrollpane");
        setSpacing(10);
        getChildren().addAll(reload, scrollPane);
    }

    private class LoadLogsHandler implements EventHandler<ActionEvent> {
        ArrayList<EffortLog> log;
    
        public LoadLogsHandler(ArrayList<EffortLog> l) {
            this.log = l;
        }

        public void handle(ActionEvent event) {
        	int numLogs = 0;
            logsContainer.getChildren().clear();
            Text logText = new Text(EffortLogTableOps.readEffortLog());
            logsContainer.getChildren().add(logText);
 
        }
    }
}
