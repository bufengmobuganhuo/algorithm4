package leetcode.graph;

import java.util.List;

/**
 * @author yuzhang
 * @date 2020/12/17 上午8:56
 * TODO
 */
public class Ex841_1 {
    private boolean[] marked;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        marked = new boolean[rooms.size()];
        int count = 0;
        for (int i = 0; i < rooms.size(); i++) {
            if (!marked[i]){
                dfs(rooms,i);
                count++;
            }
        }
        return count < 2;
    }

    private void dfs(List<List<Integer>> rooms, int vertex){
        marked[vertex] = true;
        for (int adjVertex : rooms.get(vertex)) {
            if (!marked[adjVertex]){
                dfs(rooms,adjVertex);
            }
        }
    }
}
