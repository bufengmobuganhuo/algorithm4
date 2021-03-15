package leetcode.heap;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2021/3/15 上午10:22
 * TODO
 */
public class Ex264 {
    public static void main(String[] args) {
        Ex264 ex264 = new Ex264();
        System.out.println(ex264.nthUglyNumber(10));
    }

    private static Ugly2 ugly2 = new Ugly2(1690);

    public int nthUglyNumber(int n) {
        return (int) ugly2.ugly[n - 1];
    }
}

// https://leetcode-cn.com/problems/ugly-number-ii/solution/chou-shu-ii-by-leetcode/
class Ugly2 {
    long[] ugly;
    int i2 = 0, i3 = 0, i5 = 0;
    private int[] primes = {2, 3, 5};

    public Ugly2(int cap) {
        ugly = new long[cap];
        ugly[0] = 1;
        for (int i = 1; i < cap; i++) {
            long uglyNum = Math.min(ugly[i2] * 2, Math.min(ugly[i3] * 3, ugly[i5] * 5));
            ugly[i] = uglyNum;

            if (uglyNum == ugly[i2] * 2) {
                i2++;
            }
            if (uglyNum == ugly[i3] * 3) {
                i3++;
            }
            if (uglyNum == ugly[i5] * 5) {
                i5++;
            }
        }
    }
}

class Ugly1 {
    private Set<Long> seen;
    long[] ugly;
    private long[] pq;
    private int N;

    public Ugly1(int cap) {
        pq = new long[cap + 1];
        ugly = new long[cap];
        seen = new HashSet<>();
        int[] primes = {2, 3, 5};
        add(1);
        seen.add((long) 1);
        for (int i = 1; i < cap; i++) {
            long lastUgly = delMin();
            ugly[i - 1] = lastUgly;
            for (int prime : primes) {
                long ugly = prime * lastUgly;
                if (!seen.contains(ugly)) {
                    add(ugly);
                    seen.add(ugly);
                }
            }
        }
    }

    private long delMin() {
        long res = pq[1];
        exch(1, N--);
        sink(1);
        return res;
    }

    private void add(long item) {
        pq[++N] = item;
        swim(N);
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && pq[j + 1] < pq[j]) {
                j++;
            }
            if (pq[k] < pq[j]) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while (k > 1 && pq[k] < pq[k / 2]) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    private void exch(int i, int j) {
        long tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }
}