package leetcode.rank.year2022.jan2;

/**
 * @author yuzhang
 * @date 2022/1/2 10:27 上午
 * TODO
 */
public class Ex2 {
    public int numberOfBeams(String[] bank) {
        int lastLayer = 0;
        int count = 0;
        for (String beam : bank) {
            int curLayer = 0;
            for (char chr : beam.toCharArray()) {
                if (chr == '1') {
                    curLayer++;
                }
            }
            count += lastLayer * curLayer;
            if (curLayer != 0) {
                lastLayer = curLayer;
            }
        }
        return count;
    }
}
