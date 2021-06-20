package leetcode.rank.jun20;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yu zhang
 */
public class Ex2 {
    public static void main(String[] args) {
        String num = "2046";
        Ex2 ex2 = new Ex2();
        System.out.println(ex2.numberOfRounds("04:54", "18:51"));
    }

    public int numberOfRounds(String startTime, String finishTime) {
        LocalTime start = LocalTime.parse(startTime, DateTimeFormatter.ISO_LOCAL_TIME);
        LocalTime end = LocalTime.parse(finishTime, DateTimeFormatter.ISO_LOCAL_TIME);
        if (start.isBefore(end)) {
            if (Duration.between(start, end).toMinutes() < 15) {
                return 0;
            }
            int minutes = (int) (Math.ceil(start.getMinute() / 15.0)) * 15;
            start = start.minusMinutes(start.getMinute()).plusMinutes(minutes);
            return (int) (Duration.between(start, end).toMinutes() / 15);
        } else {
            int minutes = (int) (Math.ceil(start.getMinute() / 15.0)) * 15;
            start = start.minusMinutes(start.getMinute()).plusMinutes(minutes);
            int round = 0;
            if (!start.equals(LocalTime.of(0, 0))) {
                round = (int) ((Duration.between(start, LocalTime.of(23, 59)).toMinutes() + 1) / 15);
            }
            round = (int) (round + Duration.between(LocalTime.of(0, 0), end).toMinutes() / 15);
            return round;
        }
    }
}
