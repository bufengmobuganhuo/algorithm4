package leetcode.queue;

import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/7/11 3:54 下午
 * leetcode: 面试题17.09
 */
public class Ex17_09 {
    public static void main(String[] args) {
        Ex17_09 ex17_09=new Ex17_09();
        ex17_09.getKthMagicNumber2(5);
    }
    public int getKthMagicNumber(int k) {
        Queue<Long> queue= new PriorityQueue<>();
        queue.offer(1L);
        long res=1L;
        for (int i = 0; i < k; i++) {
            res=queue.poll();
            if (!queue.contains(res*3)){
                queue.offer(res*3);
            }
            if(!queue.contains(res*5)){
                queue.offer(res*5);
            }
            if(!queue.contains(res*7)){
                queue.offer(res*7);
            }
        }
        return (int)res;
    }
    // 解法二: 用TreeSet，减少判重的过程
    public int getKthMagicNumber2(int k) {
        TreeSet<Long> set= new TreeSet<>();
        set.add(1L);
        long res=1L;
        for (int i = 0; i < k; i++) {
            res=set.pollFirst();
            set.add(res*3);
            set.add(res*5);
            set.add(res*7);
        }
        return (int)res;
    }
}
