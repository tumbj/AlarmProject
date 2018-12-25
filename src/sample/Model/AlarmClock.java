package sample.Model;

import java.util.ArrayList;
import java.util.List;

public class AlarmClock  {
    private Time time;
    public List<Clock> allAlarm;


    public AlarmClock() {
        this.time = new Time();
        this.allAlarm = new ArrayList<>();
        allAlarm.add(new Clock(2018,12,25,23,48,10));

        //not have minute is 60
    }

    public void setAlarm(){
        allAlarm.add(new Clock(Integer.parseInt(time.getDate()[0]),Integer.parseInt(time.getDate()[1]),
                Integer.parseInt(time.getDate()[2]),
                Integer.parseInt(time.getTime()[0]),
                Integer.parseInt(time.getTime()[1]),
                Integer.parseInt(time.getTime()[2])));
    }
    public List getAllAlarm(){
        return allAlarm;
    }




}
