import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Logs extends VBox {

    private Button reload;
    private VBox logsContainer;

    public Logs(ArrayList<EffortLog> effortLogs) {
        reload = new Button("Load Logs");
        reload.setOnAction(new LoadLogsHandler(effortLogs));
        logsContainer = new VBox();

        ScrollPane scrollPane = new ScrollPane(logsContainer);
        setPadding(new Insets(10));
        logsContainer.setPadding(new Insets(10));
        setSpacing(10);

        getChildren().addAll(reload, scrollPane);
    }

    private class LoadLogsHandler implements EventHandler<ActionEvent> {
        ArrayList<EffortLog> log;

        public LoadLogsHandler(ArrayList<EffortLog> l) {
            this.log = l;
        }

        public void handle(ActionEvent event) {
            logsContainer.getChildren().clear();
            for (EffortLog log : log) {
                String logString = log.toString();
                Text logText = new Text(logString);
                logText.setStyle("-fx-border-color: gray; -fx-border-width: 0 0 1 0;"); // Add bottom border
                logsContainer.getChildren().add(logText);
            }
        }
    }
}
