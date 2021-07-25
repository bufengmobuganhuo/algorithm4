package leetcode.graph;

import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/7/25 下午4:30
 * TODO
 */
public class Ex1743 {
    public static void main(String[] args) {
        int[][] adjacentPairs = {
                {2, 1},
                {3, 4},
                {3, 2},
        };
        Ex1743 ex1743 = new Ex1743();
        System.out.println(Arrays.toString(ex1743.restoreArray(adjacentPairs)));
    }

    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        // 构建图中的邻接表
        for (int[] adjacentPair : adjacentPairs) {
            adj.computeIfAbsent(adjacentPair[0], key -> new ArrayList<>()).add(adjacentPair[1]);
            adj.computeIfAbsent(adjacentPair[1], key -> new ArrayList<>()).add(adjacentPair[0]);
        }
        // 只有数组中开头和结尾的元素的邻接表长度=1
        int startVertex = -1;
        for (Map.Entry<Integer, List<Integer>> entry : adj.entrySet()) {
            if (entry.getValue().size() == 1) {
                startVertex = entry.getKey();
                break;
            }
        }
        // 类似图中找路径的方式，构建出数组
        int[] res = new int[adj.size()];
        res[0] = startVertex;
        res[1] = adj.get(startVertex).get(0);
        for (int i = 2; i < res.length; i++) {
            List<Integer> adjVertexes = adj.get(res[i - 1]);
            res[i] = res[i - 2] == adjVertexes.get(0) ? adjVertexes.get(1) : adjVertexes.get(0);
        }
        return res;
    }
}
