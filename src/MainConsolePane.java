import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private boolean started;
    private Date date;

    public MainConsolePane() {
        timeLabel = new Label("0 hours 0 minutes 0 seconds");
        timeLabel.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        Font header = Font.font("System", FontWeight.BOLD, 20);
        timeLabel.setFont(header);

        startTimerButton = new Button("Start Timer");
        stopTimerButton = new Button("Stop Timer");
        startTimeLabel = new Label("Start Time: ");
        endTimeLabel = new Label("Stop Time: ");

        getChildren().addAll(timeLabel, startTimerButton, stopTimerButton, startTimeLabel, endTimeLabel);
        setSpacing(10);
        setAlignment(Pos.CENTER);

        startTimerButton.setOnAction(new StartTimerHandler());
        stopTimerButton.setOnAction(new StopTimerHandler());

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
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
