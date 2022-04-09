package leetcode.rank.year2022.apr3;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.MINUTES;


/**
 * @author yuzhang
 * @date 2022/4/3 10:20 AM
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        System.out.println(new Ex1().convertTime("02:30", "04:35"));
    }
    public int convertTime(String current, String correct) {
        LocalTime currentTime = LocalTime.parse(current, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime correctTime = LocalTime.parse(correct, DateTimeFormatter.ofPattern("HH:mm"));
        long minutes = MINUTES.between(currentTime, correctTime);
        int count = 0;
        count += minutes / 60;
        minutes %= 60;
        count += minutes / 15;
        minutes %= 15;
        count += minutes / 5;
        minutes %= 5;
        count += minutes;
        return count;
    }
}
