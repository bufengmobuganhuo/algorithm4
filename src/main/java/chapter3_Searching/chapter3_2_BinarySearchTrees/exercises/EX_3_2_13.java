package chapter3_Searching.chapter3_2_BinarySearchTrees.exercises;

import chapter3_Searching.chapter3_2_BinarySearchTrees.BinarySearchTree;
import chapter3_Searching.chapter3_2_BinarySearchTrees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/7/6 10:18 上午
 * TODO
 */
public class EX_3_2_13<Key extends Comparable<Key>,Value> {
    public static void main(String[] args) {
        EX_3_2_13<Integer,Integer> binarySearchTree=new EX_3_2_13<>();
        binarySearchTree.put(100,100);
        binarySearchTree.put(80,80);
        binarySearchTree.put(120,120);
        binarySearchTree.put(90,90);
        binarySearchTree.put(110,110);
        binarySearchTree.put(125,125);
        binarySearchTree.put(95,95);
        System.out.println(binarySearchTree.min());
        System.out.println(binarySearchTree.max());
        System.out.println(binarySearchTree.rank(110));
        System.out.println(binarySearchTree.select(4));
        binarySearchTree.delete(100);
        binarySearchTree.deleteMax();
        binarySearchTree.deleteMin();
        System.out.println(binarySearchTree.get(125));
        for (Integer key:binarySearchTree.keys(70,130)){
            System.out.println(key);
        }
    }
    private TreeNode<Key,Value> root;
    public EX_3_2_13() {
    }

    public void put(Key key,Value value){
        checkKey(key);
        root=putRecursive(root,key,value);
    }

    private TreeNode<Key,Value> putRecursive(TreeNode<Key,Value> node,Key key,Value value){
        if (node==null){
            return new TreeNode<>(key,value);
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes>0){
            node.right=putRecursive(node.right,key,value);
        }else if(compareRes<0){
            node.left=putRecursive(node.left,key,value);
        }else{
            node.value=value;
        }
        node.nodeCount=size(node.left)+size(node.right)+1;
        return node;
    }

    public Value get(Key key){
        checkKey(key);
        TreeNode<Key,Value> res=getRecursive(root,key);
        return res==null?null:res.value;
    }

    private TreeNode<Key,Value> getRecursive(TreeNode<Key,Value> node,Key key){
        if (node==null){
            return null;
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes==0){
            return node;
        }else if(compareRes>0){
            return getRecursive(node.right,key);
        }else{
            return getRecursive(node.left,key);
        }
    }

    public boolean isEmpty(){
        return root==null;
    }

    public Key min(){
        if (isEmpty()){
            throw new IllegalArgumentException();
        }
        return minRecursive(root).key;
    }

    public Key max(){
        if (isEmpty()){
            throw new IllegalArgumentException();
        }
        return maxRecursive(root);
    }

    private Key maxRecursive(TreeNode<Key,Value> root){
        if (root.right==null){
            return root.key;
        }
        return maxRecursive(root.right);
    }

    public void deleteMin(){
        if (isEmpty()){
            throw new IllegalArgumentException();
        }
        root=deleteMinRecursive(root);
    }

    private TreeNode<Key,Value> deleteMinRecursive(TreeNode<Key,Value> root){
        if (root.left==null){
            return root.right;
        }
        root.left=deleteMinRecursive(root.left);
        root.nodeCount=size(root.left)+size(root.right)+1;
        return root;
    }

    public void delete(Key key){
        checkKey(key);
        root=deleteRecursive(root,key);
    }

    private TreeNode<Key, Value> deleteRecursive(TreeNode<Key,Value> root,Key key){
        if (root==null){
            return null;
        }
        int compareRes=key.compareTo(root.key);
        if (compareRes>0){
             root.right = deleteRecursive(root.right,key);
        }else if(compareRes<0){
            root.left = deleteRecursive(root.left,key);
        }else{
            // 对于单个结点的情况
            if (root.left==null){
                return root.right;
            }else if(root.right==null){
                return root.left;
            }

            // 1.保存引用,方便后面使用
            TreeNode<Key,Value> deletedNode=root;
            // 2.找到后继结点来替换被删除结点
            root=minRecursive(deletedNode.right);
            // 3. 删除后继结点
            root.right=deleteMinRecursive(deletedNode.right);
            root.left=deletedNode.left;
        }
        root.nodeCount=size(root.left)+size(root.right)+1;
        return root;
    }

    public void deleteMax(){
        if (isEmpty()){
            throw new IllegalArgumentException();
        }
        root=deleteMaxRecursive(root);
    }

    private TreeNode<Key,Value> deleteMaxRecursive(TreeNode<Key,Value> root){
        if (root.right==null){
            return root.left;
        }
        root.right=deleteMaxRecursive(root.right);
        root.nodeCount=size(root.right)+size(root.left)+1;
        return root;
    }

    private TreeNode<Key,Value> minRecursive(TreeNode<Key,Value> root){
        if (root.left==null){
            return root;
        }
        return minRecursive(root.left);
    }

    public Key floor(Key key){
        checkKey(key);
        TreeNode<Key,Value> res=floorRecursive(root,key);
        return res==null?null:res.key;
    }

    private TreeNode<Key,Value> floorRecursive(TreeNode<Key,Value> root,Key key){
        if (root==null){
            return null;
        }
        int compareRes=key.compareTo(root.key);
        if (compareRes==0){
            return root;
        }else if(compareRes<0){
            return floorRecursive(root.left,key);
        }else{
            TreeNode<Key,Value> node = floorRecursive(root.right,key);
            return node==null?root:node;
        }
    }

    public Key ceiling(Key key){
        checkKey(key);
        TreeNode<Key,Value> res=ceillingRecursive(root,key);
        return res==null?null:res.key;
    }

    private TreeNode<Key,Value> ceillingRecursive(TreeNode<Key,Value> root,Key key){
        if (root==null){
            return null;
        }
        int compareRes=key.compareTo(root.key);
        if (compareRes==0){
            return root;
        }else if(compareRes>0){
            return ceillingRecursive(root.right,key);
        }else{
            TreeNode<Key,Value> node =ceillingRecursive(root.left,key);
            return node==null?root:node;
        }
    }

    public int rank(Key key){
        checkKey(key);
        return rankRecursive(root,key);
    }

    private int rankRecursive(TreeNode<Key,Value> root,Key key){
        if (root==null){
            return 0;
        }
        int compareRes=key.compareTo(root.key);
        if (compareRes==0){
            return size(root.left);
        }else if(compareRes>0){
            return size(root.left)+rankRecursive(root.right,key)+1;
        }else{
            return rankRecursive(root.left,key);
        }

    }

    public Key select(int k){
        if (k<0){
            return null;
        }
        TreeNode<Key,Value> res =selectRecursive(root,k);
        return res==null?null:res.key;
    }

    private TreeNode<Key,Value> selectRecursive(TreeNode<Key,Value> root,int k){
        if (root==null){
            return null;
        }
        int rank=size(root.left);
        if (rank==k){
            return root;
        }else if (rank>k){
            return selectRecursive(root.left,k);
        }else{
            return selectRecursive(root.right,k-rank-1);
        }
    }

    public Iterable<Key> keys(Key lo,Key hi){
        checkKey(lo);
        checkKey(hi);
        Queue<Key> queue=new LinkedList<>();
        keysRecursive(root,queue,lo,hi);
        return queue;
    }

    private void keysRecursive(TreeNode<Key,Value> root, Queue<Key> queue,Key lo,Key hi){
        if (root==null){
            return;
        }
        int compareLo=lo.compareTo(root.key);
        int compareHi=hi.compareTo(root.key);
        if (compareLo<0){
            keysRecursive(root.left,queue,lo,hi);
        }
        if (compareLo<=0&&compareHi>=0){
            queue.offer(root.key);
        }
        if (compareHi>0){
            keysRecursive(root.right,queue,lo,hi);
        }
    }



    private void checkKey(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
    }

    private int size(TreeNode<Key,Value> node){
        if (node==null){
            return 0;
        }
        return node.nodeCount;
    }


}
