package leetcode.rank.year2022.august28;

/**
 * @author yuzhang
 * @date 2022/8/28 10:20
 * TODO
 */
public class Ex2 {
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder(s);
        int startIdx = 0;
        int idx = sb.indexOf("*", startIdx);
        while (idx >= 0) {
            sb.deleteCharAt(idx);
            sb.deleteCharAt(idx - 1);
            idx -= 2;
            idx = sb.indexOf("*", idx);
        }
        return sb.toString();
    }
}
