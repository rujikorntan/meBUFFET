package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Timer;
import java.util.TimerTask;

public class AlertController {
    private int mili = 3;
    private int  seconds;
    private  String secondsST;
    private Timer timer = new java.util.Timer();


    @FXML
    private Label timeOut;

    public void subMilli() {
        this.mili -= 1;
    }
    public void settingTime() {
        this.seconds = this.mili;
    }

    public  void initialize(){
        startTime(this.timeOut);
    }
    public void startTime(Label ss){
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        checkMili();
                        settingTime();
                        ss.setText(getSecond());
                        subMilli();
                    }
                });
            }

        }, 0, 1000);
    }
    public void checkMili(){
        if(this.mili == -1){
            Stage stage = (Stage) timeOut.getScene().getWindow();
            stage.close();

        }

    }
    public String getSecond(){
        return this.secondsST = Integer.toString(this.seconds);
    }


}
