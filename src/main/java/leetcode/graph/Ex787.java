package leetcode.graph;

import edu.princeton.cs.algs4.AcyclicSP;
import javafx.util.Pair;
import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/3/18 上午8:58
 * TODO
 */
public class Ex787 {
    public static void main(String[] args) {
        int[][] flights = {{0, 1, 1}, {1, 2, 1}, {0, 2, 5}, {2, 3, 1}};
        Ex787 ex787 = new Ex787();
        System.out.println(ex787.findCheapestPrice(4, flights, 0, 3, 1));
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // int[i][j] > 0: 定点i和定点j连通
        int[][] graph = new int[n][n];
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }
        // {i,price,k}:{定点i,到达i的费用，剩余的中转次数}
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        priorityQueue.offer(new int[]{src, 0, K+1});
        while (!priorityQueue.isEmpty()) {
            int[] info = priorityQueue.poll();
            int vertex = info[0];
            int price = info[1];
            int k = info[2];
            if (vertex == dst) {
                return price;
            }
            if (k > 0) {
                for (int i = 0; i < n; i++) {
                    if (graph[vertex][i] > 0) {
                        priorityQueue.offer(new int[]{i, price + graph[vertex][i], k - 1});
                    }
                }
            }
        }
        return -1;
    }
}
