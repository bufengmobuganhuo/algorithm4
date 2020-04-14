package chapter3_Searching.chapter3_5_Applications.exercises;

import chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises.linkedlist.LinkedList;
import com.sun.javafx.charts.ChartLayoutAnimator;
import edu.princeton.cs.algs4.RedBlackBST;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author zhangyu
 * 2020/4/14 14:16
 * 练习2.5.17
 */
public class EX_3_5_17_MathSET<Key extends Comparable<Key>> {
    public static void main(String[] args) {
        Integer[] keys1={1,3,5,7,9,8};
        Integer[] keys2={2,4,6,8,10};
        EX_3_5_17_MathSET<Integer> a1=new EX_3_5_17_MathSET<>(keys1);
        EX_3_5_17_MathSET<Integer> a2=new EX_3_5_17_MathSET<>(keys2);
        a1.union(a2);
        a2.print();
        System.out.println();

        a1.complement(a2);
        a2.print();

        System.out.println();

        a1.intersection(a2);
        a2.print();

        System.out.println();
    }
    private final static boolean RED=true;
    private final static boolean BLACK=false;
    private MathSETNode root;
    private int size;

    public EX_3_5_17_MathSET(Key[] keys) {
        if (keys==null){
            throw new IllegalArgumentException();
        }
        for (Key key:keys){
            add(key);
        }
    }

    public void print(){
        print(root);
    }

    public void print(MathSETNode node){
        if (node==null){
            return;
        }
        print(node.left);
        System.out.print(node.key+" ");
        print(node.right);
    }

    /**
     * @param a 差集，所有不在this中的键
     */
    public void complement(EX_3_5_17_MathSET<Key> a){
        if (a==null){
            return;
        }
        complementRecursive(a,root);
    }

    public void complementRecursive(EX_3_5_17_MathSET<Key> a,MathSETNode node){
        if (node==null){
            return;
        }
        complementRecursive(a,node.left);
        if (a.contains(node.key)){
            a.delete(node.key);
        }
        complementRecursive(a,node.right);
    }

    /**
     * @param a 求交集
     */
    public void intersection(EX_3_5_17_MathSET<Key> a){
        if (a==null){
            return;
        }
        intersectionRecursive(a,root);
    }

    private void intersectionRecursive(EX_3_5_17_MathSET<Key> a,MathSETNode node){
        if (node==null){
            return;
        }
        intersectionRecursive(a,node.left);
        if (!a.contains(node.key)){
            a.delete(node.key);
        }
        intersectionRecursive(a,node.right);
    }

    /**
     * @param a 并集
     */
    public void union(EX_3_5_17_MathSET<Key> a){
        if (a==null){
            return;
        }
        unionRecursive(root,a);
    }

    private void unionRecursive(MathSETNode node,EX_3_5_17_MathSET<Key> a){
        if (node==null){
            return;
        }
        unionRecursive(node.left,a);
        a.add(node.key);
        unionRecursive(node.right,a);
    }

    public void delete(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        if (!contains(key)){
            return;
        }
        if (!isRed(root.left)&&!isRed(root.right)){
            root.color=RED;
        }
        root=deleteRecursive(key,root);
        size--;
        if (!isEmpty()){
            root.color=BLACK;
        }
    }

    private MathSETNode deleteRecursive(Key key,MathSETNode node){
        if (key.compareTo(node.key)<0){
            if (!isRed(node.left)&&!isRed(node.left.left)){
                node=moveRedLeft(node);
            }
            node.left= deleteRecursive(key,node.left);
        }else{
            if (isRed(node.left)){
                node=rotateRight(node);
            }
            //如果找到键，并且该节点没有右子树，则不需要使用后继节点替换
            if (key.compareTo(node.key)==0&&node.right==null){
                return null;
            }
            //保证是3-，4-节点
            if (!isRed(node.right)&&isRed(node.right.left)){
                node=moveRedRight(node);
            }
            if (key.compareTo(node.key)==0){
                MathSETNode susNode=minRecursive(node.right);
                node.key=susNode.key;
                node.right=delMinRecursive(node.right);
            }else{
                node.right=deleteRecursive(key,node.right);
            }
        }
        return balance(node);
    }

    private MathSETNode delMinRecursive(MathSETNode node){
        if (node.left==null){
            //在遍历时保证了为3-、4-节点
            return null;
        }
        //如果在向左的过程中遇到了2-节点
        if (!isRed(node.left)&&!isRed(node.left.left)){
            node=moveRedLeft(node);
        }
        node.left=delMinRecursive(node.left);
        return balance(node);
    }

    private MathSETNode balance(MathSETNode node){
        if (isRed(node.right)){
            node=rotateLeft(node);
        }
        if (isRed(node.left)&&isRed(node.left.left)){
            node=rotateRight(node);
        }
        if (isRed(node.left)&&isRed(node.right)){
            flipColors(node);
        }
        return node;
    }

    private MathSETNode moveRedRight(MathSETNode node){
        flipColors(node);
        if (isRed(node.left.left)){
            node=rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private MathSETNode moveRedLeft(MathSETNode node){
        flipColors(node);
        if (isRed(node.right.left)){
            node.right= rotateRight(node.right);
            node=rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private MathSETNode minRecursive(MathSETNode node){
        if (node.left==null){
            return node;
        }
        return minRecursive(node.left);
    }

    public boolean contains(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        return getRecursive(key,root)!=null;
    }

    private MathSETNode getRecursive(Key key,MathSETNode node){
        if (node==null){
            return null;
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes==0){
            return node;
        }else if(compareRes>0){
            return getRecursive(key,node.right);
        }else{
            return getRecursive(key,node.left);
        }

    }

    public void add(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        if (contains(key)){
            return;
        }
        root=addRecursive(key,root);
        root.color=BLACK;
        size++;
    }

    private MathSETNode addRecursive(Key key,MathSETNode node){
        if (node==null){
            return new MathSETNode(key,RED);
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes>0){
            node.right=addRecursive(key,node.right);
        }else if(compareRes<0){
            node.left=addRecursive(key,node.left);
        }
        if (!isRed(node.left)&&isRed(node.right)){
            node=rotateLeft(node);
        }
        if (isRed(node.left)&&isRed(node.left.left)){
            node=rotateRight(node);
        }
        if (isRed(node.left)&&isRed(node.right)){
            flipColors(node);
        }
        return node;
    }

    private void flipColors(MathSETNode node){
        node.color=!node.color;
        node.left.color=!node.left.color;
        node.right.color=!node.right.color;
    }

    private MathSETNode rotateLeft(MathSETNode node){
        MathSETNode temp=node.right;
        node.right=temp.left;
        temp.left=node;
        temp.color=node.color;
        node.color=RED;
        return temp;
    }

    private MathSETNode rotateRight(MathSETNode node){
        MathSETNode temp=node.left;
        node.left=temp.right;
        temp.right=node;
        temp.color=node.color;
        node.color=RED;
        return temp;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return root==null;
    }

    private boolean isRed(MathSETNode node){
        return node==null?BLACK:node.color;
    }

    class MathSETNode{
        Key key;
        boolean color;
        MathSETNode left;
        MathSETNode right;

        public MathSETNode(Key key, boolean color) {
            this.key = key;
            this.color = color;
        }
    }
}
