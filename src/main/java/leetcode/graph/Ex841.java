package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/8/15 10:59 上午
 * TODO
 */
public class Ex841 {
    public static void main(String[] args) {
        List<List<Integer>> rooms=new ArrayList<>();
        rooms.add(Arrays.asList(1,3));
        rooms.add(Arrays.asList(3,0,1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(0));
        Ex841 ex841=new Ex841();
        System.out.println(ex841.canVisitAllRooms(rooms));
    }
    private int count;
    private boolean[] marked;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        marked=new boolean[rooms.size()];
        for (int i = 0; i < rooms.size(); i++) {
            if (!marked[i]){
                dfs(rooms,i);
                count++;
            }
        }
        return count<2;
    }

    private void dfs(List<List<Integer>> rooms,int startVertex){
        marked[startVertex]=true;
        for (int adjVertex : rooms.get(startVertex)) {
            if (!marked[adjVertex]){
                dfs(rooms,adjVertex);
            }
        }
    }
}
