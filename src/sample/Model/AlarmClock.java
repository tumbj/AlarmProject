package sample.Model;

import java.util.ArrayList;
import java.util.List;

public class AlarmClock {
    private Time time;
    private List<Clock> allAlarm;

    public AlarmClock() {
        this.time = new Time();
        this.allAlarm = new ArrayList<>();
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
