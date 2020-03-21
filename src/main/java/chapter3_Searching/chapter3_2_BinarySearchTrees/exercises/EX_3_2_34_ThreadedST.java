package chapter3_Searching.chapter3_2_BinarySearchTrees.exercises;

import chapter3_Searching.chapter3_2_BinarySearchTrees.BinarySearchTreeTemplate;
import chapter3_Searching.chapter3_2_BinarySearchTrees.TreeNode;

/**
 * @author zhangyu
 * 2020/3/20 9:51
 * 练习3.2.34：扩展的二叉查找树
 */
public class EX_3_2_34_ThreadedST<Key extends Comparable<Key>,Value> implements BinarySearchTreeTemplate<Key,Value> {
    public static void main(String[] args) {
        EX_3_2_34_ThreadedST<Integer, Integer> ex_3_2_34_threadedST=new EX_3_2_34_ThreadedST<>();
        ex_3_2_34_threadedST.put(100,100);
        ex_3_2_34_threadedST.put(80,80);
        ex_3_2_34_threadedST.put(120,120);
        ex_3_2_34_threadedST.put(90,90);
        ex_3_2_34_threadedST.put(110,110);
        ex_3_2_34_threadedST.put(125,125);
        ex_3_2_34_threadedST.put(95,95);
        ex_3_2_34_threadedST.put(50,50);
        ex_3_2_34_threadedST.put(60,60);
        ex_3_2_34_threadedST.put(40,40);
        ex_3_2_34_threadedST.put(45,45);
        System.out.println(ex_3_2_34_threadedST.select(2).key);
        System.out.println(ex_3_2_34_threadedST.rank(50));
        ex_3_2_34_threadedST.deleteMin();
        //ex_3_2_34_threadedST.deleteMax();
        ex_3_2_34_threadedST.delete(80);
        ex_3_2_34_threadedST.delete(120);
    }
    private TreeNode<Key,Value> root;

    public TreeNode<Key,Value> next(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        TreeNode<Key,Value> res=get(key);
        return res!=null?res.getSucc():null;
    }

    public TreeNode<Key,Value> prev(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        TreeNode<Key,Value> res=get(key);
        return res!=null?res.getPred():null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(TreeNode<Key,Value> node){
        return node==null?0:node.nodeCount;
    }

    @Override
    public TreeNode<Key,Value> get(Key key) {
        if (key==null){
            throw new IllegalArgumentException();
        }
        TreeNode<Key,Value> res= getRecursive(root,key);
        return res;
    }

    private TreeNode<Key,Value> getRecursive(TreeNode<Key,Value> root,Key key){
        if (root==null){
            return null;
        }
        int compareRes=key.compareTo(root.key);
        if (compareRes>0){
            return getRecursive(root.right,key);
        }else if(compareRes<0){
            return getRecursive(root.left,key);
        }
        return root;
    }

    @Override
    public void put(Key key, Value value) {
        if (key==null||value==null){
            throw new IllegalArgumentException();
        }
        root=putRecursive(root,key,value);
    }

    private TreeNode<Key,Value> putRecursive(TreeNode<Key,Value> root,Key key,Value value){
        if (root==null){
            return new TreeNode<>(key,value,1);
        }
        int compareRes=key.compareTo(root.key);
        if (compareRes>0){
            if (root.right==null){
                TreeNode<Key,Value> newNode= new TreeNode<>(key,value,1);
                root.right=newNode;
                //新节点插入右子树，则它的父节点的后继节点变成了新插入节点，
                // 也就是（在双向链表中）在root与root.secc中插入一个节点
                insertBetweenPrevAndSucc(root,newNode,root.getSucc());
            }else{
                root.right=putRecursive(root.right,key,value);
            }

        }else if(compareRes<0){
            if (root.left==null){
                TreeNode<Key,Value> newNode= new TreeNode<>(key,value,1);
                root.left=newNode;
                //新节点插入左子树，则它的父节点的前驱节点变成了新插入节点，
                // 也就是（在双向链表中）在root.prev和root之间插入一个节点
                insertBetweenPrevAndSucc(root.getPred(), newNode,root);
            }else{
                root.left=putRecursive(root.left,key,value);
            }
        }else{
            root.value=value;
        }
        root.nodeCount=size(root.left)+size(root.right)+1;
        return root;
    }
    //实际上是向双向链表两节点之间插入新节点
    private void insertBetweenPrevAndSucc(TreeNode<Key,Value> prev,TreeNode<Key,Value> node,TreeNode<Key,Value> succ){
        node.setSucc(succ);
        node.setPred(prev);
        if (prev!=null){
            prev.setSucc(node);
        }
        if (succ!=null){
            succ.setPred(node);
        }
    }

    @Override
    public TreeNode<Key, Value> min() {
        if (root==null){
            return null;
        }
        return minRecursive(root);
    }

    private TreeNode<Key,Value> minRecursive(TreeNode<Key,Value> root){
        if (root.left==null){
            return root;
        }
        return minRecursive(root.left);
    }

    @Override
    public TreeNode<Key, Value> max() {
        if (root==null){
            return null;
        }
        return maxRecursive(root);
    }

    private TreeNode<Key,Value> maxRecursive(TreeNode<Key,Value> root){
        if (root.right==null){
            return root;
        }
        return maxRecursive(root.right);
    }

    @Override
    public TreeNode<Key, Value> floor(Key key) {
        return null;
    }

    @Override
    public TreeNode<Key, Value> ceiling(Key key) {
        return null;
    }

    @Override
    public TreeNode<Key, Value> select(int rank) {
        if (rank<0||rank>size(root)){
            return null;
        }
        return selectRecursive(root,rank);
    }

    private TreeNode<Key,Value> selectRecursive(TreeNode<Key,Value> root,int rank){
        if (root==null){
            return null;
        }
        int nodeCount=size(root.left);
        if (rank==nodeCount){
            return root;
        }else if(rank<nodeCount){
            return selectRecursive(root.left,rank);
        }else{
            return selectRecursive(root.right,rank-1-size(root.left));
        }
    }

    @Override
    public int rank(Key key) {
        if (key==null){
            throw new IllegalArgumentException();
        }
        return rankRecursive(root,key);
    }

    private int rankRecursive(TreeNode<Key,Value> root,Key key){
        if (root==null){
            return -1;
        }
        int compareRes=key.compareTo(root.key);
        if (compareRes==0){
            return size(root.left);
        }else if(compareRes<0){
            return rankRecursive(root.left,key);
        }else{
            return size(root.left)+1+rankRecursive(root.right,key);
        }
    }

    @Override
    public void deleteMin() {
        if (root==null){
            return;
        }
        root=deleteMinRecursive(root);
    }

    private TreeNode<Key,Value> deleteMinRecursive(TreeNode<Key,Value> root){
        //如果无法再向左，则找到了最小值root，此时最小值的后继节点是root.right
        //最小值的后继节点的前驱节点是该最小值
        if (root.left==null){
            //找到待删除节点，从双向链表中删除
            deleteNodeInLinkedList(root);
            return root.right;
        }
        root.left=deleteMinRecursive(root.left);
        root.nodeCount=size(root.left)+size(root.right)+1;
        return root;
    }

    @Override
    public void deleteMax() {
        if (root==null){
            return;
        }
        root=deleteMaxRecursive(root);
    }

    private TreeNode<Key,Value> deleteMaxRecursive(TreeNode<Key,Value> root){
        //如果无法再向右找，则找到了最大值root，此时最大值的前驱节点是root.left
        //最大值的前驱节点的后继节点是该最大值
        if (root.right==null){
            //找到待删除节点，并从双向链表中删除
            deleteNodeInLinkedList(root);
            return root.left;
        }
        root.right=deleteMaxRecursive(root.right);
        root.nodeCount=size(root.left)+size(root.right)+1;
        return root;
    }

    /**
     * @param node 从一个双向链表中删除节点
     */
    private void deleteNodeInLinkedList(TreeNode<Key,Value> node){
        TreeNode<Key,Value> prev=node.getPred();
        TreeNode<Key,Value> succ=node.getSucc();
        if (prev!=null){
            prev.setSucc(succ);
        }
        if (succ!=null){
            succ.setPred(prev);
        }
    }

    @Override
    public void delete(Key key) {
        if (key==null){
            throw new IllegalArgumentException();
        }
        root=deleteRecursive(root,key);
    }

    private TreeNode<Key,Value> deleteRecursive(TreeNode<Key,Value> node,Key key){
        if (node==null){
            return null;
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes<0){
            node.left=deleteRecursive(node.left,key);
        }else if(compareRes>0){
            node.right=deleteRecursive(node.right,key);
        }else{
            deleteNodeInLinkedList(node);
            //待删除节点只有一个子节点的情况
            if (node.left==null){
                return node.right;
            }
            if (node.right==null){
                return node.left;
            }
            TreeNode<Key,Value> temp=node;
            //找到后继节点用于替换待删除节点
            node=minRecursive(temp.right);
            //删除后继节点,删除完后会返回node的右子节点，从而完成了右子节点的替换
            //此处不应使用deleteMinRecursive()方法，因为该方法会在双向链表中删除后继节点，
            // 而实际上后继节点在双向链表中的位置不需要改变
            node.right=deleteSuccNodeInTree(temp.right);
            node.left=temp.left;
        }
        node.nodeCount=size(node.left)+size(node.right)+1;
        return node;
    }

    private TreeNode<Key,Value> deleteSuccNodeInTree(TreeNode<Key,Value> node){
        if (node.left==null){
            return node.right;
        }
        node.left=deleteSuccNodeInTree(node.left);
        node.nodeCount=size(node.left)+size(node.right)+1;
        return node;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public double avgCompares() {
        return 0;
    }

    @Override
    public Key randomKey() {
        return null;
    }
}
