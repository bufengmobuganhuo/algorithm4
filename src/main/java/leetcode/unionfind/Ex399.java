package leetcode.unionfind;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/10/27 9:36 上午
 * TODO
 */
public class Ex399 {
    /**
     * key：当前节点，value：其父节点
     */
    private Map<String, String> parents = new HashMap<>();
    /**
     * key：当前节点，value：父节点/当前节点
     */
    private Map<String, Double> values = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        return solution1(equations, values, queries);
    }

    /**
     * 解法一：使用并查集
     * https://leetcode-cn.com/problems/evaluate-division/solution/ni-zhen-de-zhi-dao-bing-cha-ji-de-gou-zao-guo-chen/
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    private double[] solution1(List<List<String>> equations, double[] values, List<List<String>> queries) {
        for (int i = 0; i < equations.size(); i++) {
            union(equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String e = queries.get(i).get(0);
            String q = queries.get(i).get(1);
            if (!(parents.containsKey(e) && parents.containsKey(q))) {
                ans[i] = -1.0;
                continue;
            }
            if (e.equals(q)) {
                ans[i] = 1.0;
                continue;
            }
            String eRoot = findRoot(e);
            String qRoot = findRoot(q);
            if (!eRoot.equals(qRoot)) {
                ans[i] = -1.0;
                continue;
            }
            ans[i] = pm(q) / pm(e);
        }
        return ans;
    }

    private void union(String parent, String child, double value) {
        add(parent);
        add(child);
        String parentRoot = findRoot(parent);
        String childRoot = findRoot(child);
        if (parentRoot.equals(childRoot)) {
            return;
        }
        parents.put(childRoot, parentRoot);
        values.put(childRoot, value * (pm(parent) / pm(child)));
    }

    private double pm(String x) {
        double value = 1.0;
        while (!x.equals(parents.get(x))) {
            value *= values.get(x);
            x = parents.get(x);
        }
        return value;
    }

    private String findRoot(String x) {
        while (!x.equals(parents.get(x))) {
            x = parents.get(x);
        }
        return x;
    }

    private void add(String x) {
        if (!parents.containsKey(x)) {
            parents.put(x, x);
            values.put(x, 1.0);
        }
    }

    /**
     * <被除数，<除数，被除数/除数的值>>
     */
    private Map<String,Map<String,Double>> nodes = new HashMap<>();

    /**
     * 构造有向图
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    private double[] solution2(List<List<String>> equations, double[] values, List<List<String>> queries){
        // 构造图
        for (int i = 0; i < equations.size(); i++) {
            String start = equations.get(i).get(0);
            String end = equations.get(i).get(1);
            // 保存正向路径
            if (nodes.containsKey(start)){
                Map<String,Double> adj = nodes.get(start);
                adj.put(end,values[i]);
            }else{
                Map<String,Double> adj = new HashMap<>();
                adj.put(end,values[i]);
                nodes.put(start,adj);
            }
            // 保存反向路径
            if (nodes.containsKey(end)){
                Map<String,Double> adj = nodes.get(end);
                adj.put(start,1/values[i]);
            }else{
                Map<String,Double> adj = new HashMap<>();
                adj.put(start,1/values[i]);
                nodes.put(end,adj);
            }
        }
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            ans[i]=bfs(queries.get(i));
        }
        return ans;
    }

    private double bfs(List<String> query){
        // 如果从已知节点无法到达待求节点，则返回-1
        if (!nodes.containsKey(query.get(0))||!nodes.containsKey(query.get(1))){
            return -1.0;
        }
        // 相等返回1
        if (query.get(0).equals(query.get(1))){
            return 1.0;
        }
        Queue<Strdou> queue = new LinkedList<>();
        // 走过的节点，用于避免走回头路
        Set<String> paths = new HashSet<>();
        queue.offer(new Strdou(query.get(0),1.0));
        paths.add(query.get(0));
        while (!queue.isEmpty()){
            Strdou strdou = queue.poll();
            Map<String,Double> adj = nodes.get(strdou.x);
            for (String adjVertex : adj.keySet()) {
                if (paths.contains(adjVertex)){
                    continue;
                }
                // 如果找到了目标节点
                if (adjVertex.equals(query.get(1))){
                    return strdou.value*adj.get(adjVertex);
                }
                // 继续向下找
                paths.add(adjVertex);
                queue.offer(new Strdou(adjVertex,strdou.value*adj.get(adjVertex)));
            }
        }
        return -1;
    }

    static class Strdou {
        // 除数
        String x;
        // 当前值
        double value;

        public Strdou(String x, double value) {
            this.x = x;
            this.value = value;
        }
    }
}
