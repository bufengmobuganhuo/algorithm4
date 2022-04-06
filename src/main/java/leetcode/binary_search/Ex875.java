package leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex875 {
    public static void main(String[] args) {
        int[] piles = {312884470};
        System.out.println(new Ex875().minEatingSpeed(piles, 312884469));
    }

    private int[] piles;

    private int h;

    public int minEatingSpeed(int[] piles, int h) {
        this.piles = piles;
        this.h = h;
        int minK = 1, maxK = 1_000_000_000;
        while (minK < maxK) {
            int midK = minK + (maxK - minK) / 2;
            if (canEat(midK)) {
                maxK = midK;
            } else {
                minK = midK + 1;
            }
        }
        return minK;
    }

    private boolean canEat(int k) {
        int count = 0;
        for (int pile : piles) {
            count += (pile - 1) / k + 1;
        }
        return count <= h;
    }
}
