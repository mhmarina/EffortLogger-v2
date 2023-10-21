import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import javafx.scene.control.Control;

import java.util.ArrayList;
import java.util.Date;

public class MainConsolePane extends VBox {

    private Label timeLabel;
    private Label startTimeLabel;
    private Label endTimeLabel;
    private Button startTimerButton;
    private Button stopTimerButton;
    private Timeline timeline;
    private int hours;
    private int minutes;
    private int seconds; // this is the total time
    private boolean started; // determines whether the clock has started
    private Date date;
	private String projectName = "null";
	private String lifeCycleStep = "null";
	private String effortCategory = "null";
	private String deliverable = "null";
	private EffortLog effortLog;
	private ComboBox<String> projectCB;
	private ComboBox<String> lifeCyclesCB;
	private ComboBox<String> effortCatCB;
	private ComboBox<String> deliverableCB;
	private ArrayList<String> projectList;
	private String [] lifeCycleList;
	private String [] effortCatList;
	private String [] deliverablesList;
	
    public MainConsolePane(ArrayList<EffortLog> effortLogs) {
        timeLabel = new Label("0 hours 0 minutes 0 seconds");
        timeLabel.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        Font header = Font.font("System", FontWeight.BOLD, 20);
        timeLabel.setFont(header);
        
        projectList = new ArrayList<>();
        projectList.add("Business Project");
        projectList.add("Development Project");
        lifeCycleList = new String[]{"Planning", "Information Gathering", "Information Understanding",
        							"Verifying", "Outlining", "Drafting", "Finalizing", "Team Meeting",
        							"Coach Meeting", "Stakeholder Meeting"};
        effortCatList = new String[] {"Plans", "Deliverables", "Interruptions", "Defects", "Other"};
        deliverablesList = new String[] {"Project Plan", "Risk Management Plan", "Conceptual Design Plan",
        								"Detailed Design Plan", "Implementation Plan"};
        
        projectCB = new ComboBox();
        projectCB.setValue("Project Name");
        projectCB.getItems().addAll(projectList);
        projectCB.setOnAction(new PCBHandler());
        lifeCyclesCB = new ComboBox();
        lifeCyclesCB.getItems().addAll(lifeCycleList);
        lifeCyclesCB.setValue("Life Cycle Stage");
        lifeCyclesCB.setOnAction(new LCCBHandler());
        effortCatCB = new ComboBox();
        effortCatCB.setValue("Effort Category");
        effortCatCB.getItems().addAll(effortCatList);
        effortCatCB.setOnAction(new ECCBHandler());
        deliverableCB = new ComboBox();
        deliverableCB.setValue("Deliverable Type");
        deliverableCB.getItems().addAll(deliverablesList);
        deliverableCB.setOnAction(new DCBHandler());
        
        GridPane comboBoxes = new GridPane();
        comboBoxes.add(projectCB, 0, 0);
        comboBoxes.add(lifeCyclesCB, 0, 1);
        comboBoxes.add(effortCatCB, 1, 0);
        comboBoxes.add(deliverableCB, 1, 1);
        comboBoxes.setAlignment(Pos.CENTER);
        
        double comboBoxWidth = 200.0; // You can adjust this width as needed
        projectCB.setPrefWidth(comboBoxWidth);
        lifeCyclesCB.setPrefWidth(comboBoxWidth);
        effortCatCB.setPrefWidth(comboBoxWidth);
        deliverableCB.setPrefWidth(comboBoxWidth);
   
        Insets margin = new Insets(5.0);
        for (Node node : comboBoxes.getChildren()) {
            GridPane.setMargin(node, margin);
        }
   
        startTimerButton = new Button("Start Timer");
        stopTimerButton = new Button("Stop Timer");
        startTimeLabel = new Label("Start Time: ");
        endTimeLabel = new Label("Stop Time: ");

        getChildren().addAll(comboBoxes,timeLabel,startTimerButton, stopTimerButton, startTimeLabel, endTimeLabel);
        setSpacing(10);
        setAlignment(Pos.CENTER);

        startTimerButton.setOnAction(new StartTimerHandler());
        stopTimerButton.setOnAction(new StopTimerHandler(effortLogs));

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    
    private class PCBHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			projectName = projectCB.getValue();
		}
    }
    private class LCCBHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			lifeCycleStep = lifeCyclesCB.getValue();
		}
    }    
    private class ECCBHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			effortCategory = effortCatCB.getValue();
		}
    }
    private class DCBHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			deliverable = deliverableCB.getValue();
		}
    }
    private class StartTimerHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (!started) {
            	hours = 0;
            	minutes = 0;
            	seconds = 0;
            	updateTimer();
                started = true;
                timeline.play();
                startTimerButton.setDisable(true);
                stopTimerButton.setDisable(false);
                date = new Date();
                startTimeLabel.setText("Start Time: " + date.toString());
                timeLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
            }
        }
    }
    private class StopTimerHandler implements EventHandler<ActionEvent> {
    	private ArrayList<EffortLog> logs;
    	public StopTimerHandler(ArrayList<EffortLog> l) {
    		this.logs = l;
    	}
        @Override
        public void handle(ActionEvent event) {
            if (started) {
                started = false;
                timeline.pause();
                startTimerButton.setDisable(false);
                stopTimerButton.setDisable(true);
                updateTimer();
                date = new Date();
                endTimeLabel.setText("Stop Time: " + date.toString());
                timeLabel.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                
                effortLog = new EffortLog(seconds, startTimeLabel.getText(), endTimeLabel.getText(),
                		    projectName, lifeCycleStep, effortCategory, deliverable);
                this.logs.add(effortLog);
                System.out.println(logs.size());
            }
        }
    }

    private void updateTimer() {
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
            if (minutes == 60) {
                minutes = 0;
                hours++;
            }
        }
        timeLabel.setText(formatTime(seconds));
    }
    
    public String formatTime(int sec) {
        int s = sec % 60;
        int m = (sec / 60) % 60;
        int h = sec / 3600;
    	
    	return String.format("%d hours %d minutes %d seconds", h, m, s);
    }
}
