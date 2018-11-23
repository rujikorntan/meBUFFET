package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeCustomer {
    private int mili, currentMili, hours, minutes, seconds;
    private  String minutesST, secondsST, hoursST;
    private Timer timer = new java.util.Timer();
    private Label sec, min, hour;

    public TimeCustomer(){
        Date date = new Date();
        int m = (date.getHours()*60*60) + (date.getMinutes()*60) + (date.getSeconds());
        this.mili = m;
    }

    public int getMili() {
        return mili;
    }

    public void setMili(int mili) {
        this.mili = mili;
    }

    public void settingTime() {
        this.currentMili = this.mili;
        this.hours = this.currentMili/3600;
        this.currentMili = this.currentMili%3600;
        this.minutes = this.currentMili/60;
        this.currentMili = this.currentMili%60;
        this.seconds = this.currentMili;
    }
    public void addMili() {
        this.mili += 1;
    }
    public void startTimer(Label ss, Label mm, Label hh){

        this.sec = ss;
        this.min = mm;
        this.hour = hh;
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        settingTime();
                        sec.setText(getSecond());
                        min.setText(getMinute());
                        hour.setText(getHours());
                        addMili();
                    }
                });
            }

        }, 0, 1000);
    }
    public void stopTimer(){
        timer.cancel();
        sec.setText("00");
        min.setText("00");
        hour.setText("00");

        mili = 0;
        timer.cancel();
        timer.purge();


    }
    public String getHours(){
        if( this.hours <= 9){
            this.hoursST = 0 + Integer.toString(this.hours);
        }else if( this.hours > 9){
            this.hoursST = Integer.toString(this.hours);
        }
        return this.hoursST;
    }
    public String getMinute(){
        if( this.minutes <= 9){
            this.minutesST = 0 + Integer.toString(this.minutes);
        }else if( this.minutes > 9){
            this.minutesST = Integer.toString(this.minutes);
        }
        return this.minutesST;
    }
    public String getSecond(){
        if( seconds <= 9 ){
            this.secondsST = 0 + Integer.toString(this.seconds);
        }else if (seconds >9){
            this.secondsST = Integer.toString(this.seconds);
        }
        return this.secondsST;
    }
}
