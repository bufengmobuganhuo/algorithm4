package chapter4_Graphs.chapter4_2_DirectedGraphs;

/**
 * @author zhangyu
 * 2020/5/1 22:53
 * 使用传递闭包实现可达性检测
 */
public class TransitiveClosure {
    //directedDFS[i]: 顶点i对应的与其他vertexNum个顶点的可达性关系
    private DirectedDFS[] directedDFS;

    public TransitiveClosure(Digraph digraph) {
        directedDFS=new DirectedDFS[digraph.getVertexNum()];
        //对每个顶点都执行深度优先搜索，来检测可达性
        for (int i=0;i<digraph.getVertexNum();i++){
            directedDFS[i]=new DirectedDFS(digraph,i);
        }
    }

    /**
     * @param v
     * @param w
     * @return w是否是从v可达的
     */
    public boolean reachable(int v,int w){
        return directedDFS[v].marked(w);
    }
}
