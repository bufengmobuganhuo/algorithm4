package leetcode.rank.junly18;

/**
 * @author yuzhang
 * @date 2021/7/18 上午10:38
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        int[] rungs = {4,8,12,16};
        Ex2 ex2 = new Ex2();
        System.out.println(ex2.addRungs(rungs,3));
    }
    public int addRungs(int[] rungs, int dist) {
        int count = 0;
        int current = 0;
        int idx = 0;
        while (idx < rungs.length) {
            if (current + dist >= rungs[idx]) {
                current = rungs[idx];
                idx++;
                continue;
            }
            count += (rungs[idx] - current) / dist;
            count = (rungs[idx] - current) % dist == 0 ? count - 1 : count;
            current=rungs[idx];
            idx++;
        }
        return count;
    }
}
