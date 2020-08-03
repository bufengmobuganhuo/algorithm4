package chapter3_Searching.chapter3_3_BalancedSearchTrees.exercises;

import chapter3_Searching.chapter3_3_BalancedSearchTrees.RedBlackBST;
import chapter3_Searching.chapter3_3_BalancedSearchTrees.RedBlackTreeNode;
import edu.princeton.cs.algs4.BlackFilter;
import org.omg.CORBA.INTERNAL;

/**
 * @author yuzhang
 * @date 2020/7/13 2:25 下午
 * 红黑树
 */
public class RedBlackTree extends RedBlackBST<String,String> {
    public static void main(String[] args) {
        RedBlackTree redBlackBST=new RedBlackTree();
        redBlackBST.put("Y","S");
        redBlackBST.put("L","E");
        redBlackBST.put("P","A");
        redBlackBST.put("M","R");
        redBlackBST.put("X","C");
        redBlackBST.put("H","H");
        redBlackBST.put("C","X");
        redBlackBST.put("R","M");
        redBlackBST.put("A","P");
        redBlackBST.put("E","L");
        redBlackBST.put("S","L");
        System.out.println(redBlackBST.min().key);
        redBlackBST.deleteMin();
        System.out.println(redBlackBST.max().key);
        System.out.println(redBlackBST.rank("H"));
        System.out.println(redBlackBST.select(3).key);
        System.out.println(redBlackBST.floor("G").key);
        System.out.println(redBlackBST.ceil("G").key);
        redBlackBST.delete("x");
        System.out.println(redBlackBST.get("x").key);
    }
    private RedBlackTreeNode<String,String> root;
    private static final boolean RED=true;
    private static final boolean BLACK=false;

    @Override
    public boolean contains(String String) {
        return get(String)!=null;
    }

    @Override
    public RedBlackTreeNode<String, String> ceil(String integer) {
        return ceilRecursive(root,integer);
    }

    private RedBlackTreeNode<String,String> ceilRecursive(RedBlackTreeNode<String,String> node, String integer){
        if (node==null){
            return null;
        }
        if (node.key.equals(integer)){
            return node;
        }else if(node.key.compareTo(integer)>0){
            RedBlackTreeNode<String,String> res= ceilRecursive(root.left,integer);
            return res==null?node:res;
        }else{
            return ceilRecursive(root.right,integer);
        }
    }

    @Override
    public RedBlackTreeNode<String, String> floor(String integer) {

        return floorRecursive(root,integer);
    }

    private RedBlackTreeNode<String,String> floorRecursive(RedBlackTreeNode<String,String> node,String integer){
        if (node==null){
            return null;
        }
        if (node.key.equals(integer)){
            return node;
        }else if(node.key.compareTo(integer)>0){
            return floorRecursive(node.left,integer);
        }else{
            RedBlackTreeNode<String,String> res=floorRecursive(node.right,integer);
            return res==null?node:res;
        }
    }

    @Override
    public RedBlackTreeNode<String, String> select(int rank) {
        return selectRecursive(root,rank);
    }

    private RedBlackTreeNode<String,String> selectRecursive(RedBlackTreeNode<String,String> node,int rank){
        if (node==null){
            return null;
        }
        int nodeRank=size(node);
        if (nodeRank==rank){
            return node;
        }else if(nodeRank>rank){
            return selectRecursive(node.left,rank);
        }else{
            return selectRecursive(node.right,nodeRank-rank-1);
        }
    }

    @Override
    public int rank(String integer) {
        return rankRecursive(root,integer);
    }

    private int rankRecursive(RedBlackTreeNode<String,String> node,String integer){
        if (node==null){
            return -1;
        }
        if (node.key.equals(integer)){
            return size(node.left);
        }else if(node.key.compareTo(integer)>0){
            return rankRecursive(node.left,integer);
        }else{
            return rankRecursive(node.right,integer)+size(node.left)+1;
        }
    }

    @Override
    public RedBlackTreeNode<String, String> max() {
        return maxRecursive(root);
    }

    private RedBlackTreeNode<String,String> maxRecursive(RedBlackTreeNode<String,String> node){
        if (node.right==null){
            return node;
        }
        return maxRecursive(node.right);
    }

    @Override
    public RedBlackTreeNode<String, String> min() {
        return minRecursive(root);
    }

    private RedBlackTreeNode<String,String> minRecursive(RedBlackTreeNode<String,String> node){
        if (node.left==null){
            return node;
        }
        return minRecursive(node.left);
    }

    @Override
    public RedBlackTreeNode<String, String> get(String integer) {
        return getRecursive(root,integer);
    }

    private RedBlackTreeNode<String,String> getRecursive(RedBlackTreeNode<String,String> root,String integer){
        if (root==null){
            return null;
        }
        if (root.key.equals(integer)){
            return root;
        }else if(root.key.compareTo(integer)>0){
            return getRecursive(root.left,integer);
        }else{
            return getRecursive(root.left,integer);
        }
    }

    @Override
    public void delete(String integer) {
        super.delete(integer);
    }

    private RedBlackTreeNode<String,String> deleteRecursive(RedBlackTreeNode<String,String> node,Integer integer){
        if (integer.compareTo(integer)<0){
            if (!isRed(node.left)&&!isRed(node.left.left)){
                node=moveRedLeft(node);
            }
            node.left=deleteRecursive(node.left,integer);
        }else{
            if (isRed(node.left)){
                node=rotateRight(node);
            }
            if (node.key.equals(integer)&&node.right==null){
                return null;
            }
            if (!isRed(node.right)&&!isRed(node.right.left)){
                node=moveRedRight(node);
            }
            if (node.key.equals(integer)){
                RedBlackTreeNode<String,String> susNode=minRecursive(node.right);
                node.value=susNode.value;
                node.key=susNode.key;
                node.right=deleteMin(node.right);
            }else{
                node.right=deleteRecursive(node.right,integer);
            }
        }
        return balance(node);
    }

    @Override
    public void deleteMax() {
        if (!isRed(root.left)&&!isRed(root.right)){
            root.color=RED;
        }
        deleteMax(root);
        if (!isEmpty()){
            root.color=BLACK;
        }
    }

    private RedBlackTreeNode<String,String> deleteMax(RedBlackTreeNode<String,String> node){
        if (isRed(node.left)){
            node = rotateRight(node);
        }
        if (node.right==null){
            return null;
        }
        // 在遍历过程中，保证都是2-3结点
        if (!isRed(node.right)&&!isRed(node.right.left)){
            node=moveRedRight(node);
        }
        node.right=deleteMax(node.right);
        return balance(node);
    }


    @Override
    public void deleteMin() {
        if (!isRed(root.left)&&!isRed(root.right)){
            root.color=RED;
        }
        root=deleteMin(root);
        if (!isEmpty()){
            root.color=BLACK;
        }
    }

    private RedBlackTreeNode<String,String> deleteMin(RedBlackTreeNode<String,String> node){
        if (node.left==null){
            return null;
        }
        //如果在遍历过程中，发现非2-3结点，则从右边移动一个红色过去
        if (!isRed(node.left)&&!isRed(node.left.left)){
            node=moveRedLeft(node);
        }
        node.left=deleteMin(node.left);
        return balance(node);
    }

    private RedBlackTreeNode<String,String> moveRedLeft(RedBlackTreeNode<String,String> node){
        flipColors(node);
        // 如果右边有红色结点，才移动
        if (isRed(node.right.left)){
            node.right=rotateRight(node.right);
            node=rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private RedBlackTreeNode<String,String> moveRedRight(RedBlackTreeNode<String,String> node){
        flipColors(node);
        if (isRed(node.left)&&isRed(node.left)){
            node=rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private RedBlackTreeNode<String,String> balance(RedBlackTreeNode<String,String> node){
        if (isRed(node.right)){
            node = rotateLeft(node);
        }
        if (isRed(node.left)&&isRed(node.left.left)){
            node = rotateRight(node);
        }
        if (isRed(node.left)&&isRed(node.right)){
            flipColors(node);
        }
        node.nodeCount=size(node.left)+size(node.right)+1;
        return node;
    }

    @Override
    public void put(String integer, String integer2) {
        root=putRecursive(root,integer,integer2);
        // 根结点必须为黑色
        root.color=BLACK;
    }

    private RedBlackTreeNode<String,String> putRecursive(RedBlackTreeNode<String,String> node,String integer,String integer2){
        if (node==null){
            return new RedBlackTreeNode<>(integer,integer2,RED,1);
        }
        if (node.key.compareTo(integer)>0){
            node.left= putRecursive(node.left,integer,integer2);
        }else if(node.key.compareTo(integer)<0){
            node.right= putRecursive(node.right,integer,integer2);
        }else{
            node.value=integer2;
        }
        if (isRed(node.right)&&!isRed(node.left)){
            node = rotateLeft(node);
        }
        if (isRed(node.left)&&isRed(node.left.left)){
            node = rotateRight(node.left);
        }
        if (isRed(node.left)&&isRed(node.right)){
            flipColors(node);
        }
        node.nodeCount=size(node.left)+size(node.right)+1;
        return node;
    }

    @Override
    public boolean isEmpty() {
        return size(root)==0;
    }

    private void flipColors(RedBlackTreeNode<String,String> node){
        node.color=!node.color;
        node.left.color=!node.left.color;
        node.right.color=!node.right.color;
    }

    private RedBlackTreeNode<String,String> rotateLeft(RedBlackTreeNode<String,String> fatherNode){
        RedBlackTreeNode<String,String> tmp=fatherNode.right;
        fatherNode.right=tmp.left;
        tmp.left=fatherNode;
        tmp.color=fatherNode.color;
        tmp.nodeCount=fatherNode.nodeCount;
        fatherNode.nodeCount=size(fatherNode.right)+size(fatherNode.left)+1;
        fatherNode.color=RED;
        return tmp;
    }

    private RedBlackTreeNode<String,String> rotateRight(RedBlackTreeNode<String,String> fatherNode){
        RedBlackTreeNode<String,String> tmp=fatherNode.left;
        fatherNode.left=tmp.right;
        tmp.right=fatherNode;
        tmp.color=fatherNode.color;
        tmp.nodeCount=fatherNode.nodeCount;
        fatherNode.color=RED;
        fatherNode.nodeCount=size(fatherNode.left)+size(fatherNode.right)+1;
        return tmp;
    }

    private boolean isRed(RedBlackTreeNode<String,String> node){
        return node==null? BLACK : node.color;
    }

    private int size(RedBlackTreeNode<String,String> node){
        if (node==null){
            return 0;
        }
        return node.nodeCount;
    }
}
