package chapter3_Searching.chapter3_2_BinarySearchTrees;

/**
 * @author zhangyu
 * 2020/3/17 14:26
 * TODO
 */
public class TreeNode<Key extends Comparable<Key>,Value>{
    public Key key;
    public Value value;
    public TreeNode<Key,Value> left;
    public TreeNode<Key,Value> right;
    public int nodeCount;
    public int height;

    public TreeNode(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public TreeNode(Key key, Value value, int nodeCount) {
        this.key = key;
        this.value = value;
        this.nodeCount = nodeCount;
    }

    public TreeNode(Key key, Value value, int nodeCount, int height) {
        this.key = key;
        this.value = value;
        this.nodeCount = nodeCount;
        this.height=height;
    }
}
