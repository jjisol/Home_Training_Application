package org.techtown.sportsdata;

/**
 * Created by user on 2018-12-03.
 */

public class ItemForAlarm {
    private String date;
    private String time;

    public ItemForAlarm(){}

    public ItemForAlarm(String date, String time){
        this.date = date;
        this.time = time;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }
}
