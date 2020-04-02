package chapter3_Searching.chapter3_3_BalancedSearchTrees;

/**
 * @author zhangyu
 * 2020/3/26 17:04
 * TODO
 */
public class RedBlackTreeNode<Key extends Comparable<Key>,Value> {
    protected Key key;
    protected Value value;
    protected RedBlackTreeNode<Key,Value> left,right;
    protected boolean color;
    protected int nodeCount;

    public RedBlackTreeNode(Key key, Value value, boolean color, int nodeCount) {
        this.key = key;
        this.value = value;
        this.color = color;
        this.nodeCount = nodeCount;
    }
}
