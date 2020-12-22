package leetcode.array;

/**
 * @author yuzhang
 * @date 2020/12/16 上午9:36
 * TODO
 */
public class Ex1011 {
    public static void main(String[] args) {
        Ex1011 ex1011 = new Ex1011();
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(ex1011.shipWithinDays(weights, 5));
    }

    public int shipWithinDays(int[] weights, int D) {
        int minCap = Integer.MIN_VALUE, maxCap = 0;
        for (int weight : weights) {
            // 船的最小载货量必须大于最重的单个货物
            minCap = Math.max(minCap, weight);
            // 船的最大载重是所有货物的总重量，一天运完
            maxCap += weight;
        }
        while (minCap < maxCap) {
            int mid = minCap + (maxCap - minCap) / 2;
            // 如果当前可以完成，则缩小范围
            if (canShip(weights, D, mid)) {
                maxCap = mid;
                // 否则扩大范围
            } else {
                minCap = mid + 1;
            }
        }
        return minCap;
    }

    private boolean canShip(int[] weights, int D, int capacity) {
        int count = 0;
        for (int i = 0; i < weights.length; i++) {
            int sum = 0;
            while (i < weights.length && sum <= capacity) {
                if (sum + weights[i] > capacity){
                    break;
                }
                sum += weights[i++];
            }
            count++;
            i--;
        }
        return count <= D;
    }
}
