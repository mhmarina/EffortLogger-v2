import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Logs extends VBox {
	
	private Button reload;
	private VBox logsContainer;
	
    public Logs(ArrayList<EffortLog> effortLogs) {
    	reload = new Button("Load Logs");
    	reload.setOnAction(new LoadLogsHandler(effortLogs));    	
    	logsContainer = new VBox();
    	
    	getChildren().addAll(reload, logsContainer);

    }
    
    private class LoadLogsHandler implements EventHandler<ActionEvent>{
    	ArrayList<EffortLog> log;
    	public LoadLogsHandler(ArrayList<EffortLog> l) {
    		this.log = l;
    	}
    	public void handle(ActionEvent event) {
    		logsContainer.getChildren().clear();
            for (EffortLog log : log) {
                String logString = log.toString();
                Text logText = new Text(logString);
                logsContainer.getChildren().add(logText);
            }
    	}
    }
}
