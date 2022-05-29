package leetcode.rank.year2022.may22;

/**
 * @author yuzhang
 * @date 2022/5/22 10:24
 * TODO
 */
public class Ex1 {
    public int percentageLetter(String s, char letter) {
        int count = 0;
        for(char chr : s.toCharArray()) {
            if (chr == letter) {
                count++;
            }
        }
        return (count * 100) / s.length();
    }
}
