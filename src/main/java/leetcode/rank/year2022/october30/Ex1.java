package leetcode.rank.year2022.october30;

/**
 * @author yuzhang
 * @date 2022/10/30 09:58
 * TODO
 */
public class Ex1 {
    public int averageValue(int[] nums) {
        long count = 0, sum = 0;
        for (int num : nums) {
            if (num % 3 ==0 && num % 2 ==0) {
                count++;
                sum += num;
            }
        }
        return count == 0 ? 0 : (int) (sum / count);
    }
}
