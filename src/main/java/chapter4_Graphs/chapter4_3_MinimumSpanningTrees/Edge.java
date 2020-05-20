package chapter4_Graphs.chapter4_3_MinimumSpanningTrees;

/**
 * @author zhangyu
 * 2020/5/8 10:17
 * 代表一条加权边
 */
public class Edge implements Comparable<Edge>{
    //连接两条边的顶点
    private int vertex1;
    private int vertex2;
    //权重
    private double weight;

    public Edge(int vertex1, int vertex2, double weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public double getWeight(){
        return weight;
    }

    /**
     * @return 获取一条边
     */
    public int either(){
        return vertex1;
    }

    /**
     * @param vertex
     * @return 返回vertex外的另一条边
     */
    public int other(int vertex){
        if (vertex==vertex1){
            return vertex2;
        }else if(vertex==vertex2){
            return vertex1;
        }else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return String.format("%d—%d %.2f",vertex1,vertex2,weight);
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight>o.getWeight()){
            return 1;
        }else if (this.weight<o.getWeight()){
            return -1;
        }
        return 0;
    }
}
