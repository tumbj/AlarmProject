package sample.Model;
import java.time.ZonedDateTime;
import java.time.Clock;

public class Time {
//    public static void main(String[] args) {
//        Clock clock = Clock.systemDefaultZone();
//
//        ZonedDateTime zoneDateTime = ZonedDateTime.now(clock);
//        System.out.println(zoneDateTime);
//
//
//        String[] forSplitClock = (zoneDateTime+"").split("T");
//        System.out.println(forSplitClock[0]);
//        String[] forSplitYearMonthDay = forSplitClock[0].split("-");
//
//        System.out.println(forSplitYearMonthDay[0]); // Year only
//        System.out.println(forSplitYearMonthDay[1]); // Month only
//        System.out.println(forSplitYearMonthDay[2]); // Day only
//
//
//        String[] splitTime = forSplitClock[1].split("\\.");
//        System.out.println(splitTime[0]);// Time only
//        String[] splitHourMinMilli = splitTime[0].split(":");
//        System.out.println(splitHourMinMilli[0]);
//
//
//    }
    private java.time.Clock clock;
    private ZonedDateTime zoneDateTime ;

    public Time() {
        this.clock = java.time.Clock.systemDefaultZone();
        this.zoneDateTime = ZonedDateTime.now(clock);
    }

    public String[] getDate(){
        this.clock = java.time.Clock.systemDefaultZone();
        this.zoneDateTime = ZonedDateTime.now(clock);
        String[] forSplitClock = (zoneDateTime+"").split("T");
        return forSplitClock[0].split("-");
    }
    public String[] getTime(){
        this.clock = java.time.Clock.systemDefaultZone();
        this.zoneDateTime = ZonedDateTime.now(clock);
        String[] forSplitClock = (zoneDateTime+"").split("T");
        String[] splitTime = forSplitClock[1].split("\\.");
        return splitTime[0].split(":");
    }
}