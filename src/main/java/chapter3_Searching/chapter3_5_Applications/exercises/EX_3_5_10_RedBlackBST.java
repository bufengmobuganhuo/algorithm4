package chapter3_Searching.chapter3_5_Applications.exercises;

import chapter3_Searching.chapter3_3_BalancedSearchTrees.RedBlackBST;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author zhangyu
 * 2020/4/12 15:16
 * TODO
 */
public class EX_3_5_10_RedBlackBST<Key extends Comparable<Key>,Value> {
    public static void main(String[] args) {
        EX_3_5_10_RedBlackBST<Integer,Integer> ex_3_5_9_bst=new EX_3_5_10_RedBlackBST<>();
        ex_3_5_9_bst.put(1,1);
        ex_3_5_9_bst.put(1,3);
        ex_3_5_9_bst.put(3,2);
        ex_3_5_9_bst.put(2,5);
        ex_3_5_9_bst.put(6,4);
        ex_3_5_9_bst.put(3,7);
        ex_3_5_9_bst.put(7,9);
        ex_3_5_9_bst.put(8,0);
        System.out.println(Arrays.toString(ex_3_5_9_bst.get(1).toArray()));
        System.out.println(ex_3_5_9_bst.get(4));
        ex_3_5_9_bst.delete(1);
        ex_3_5_9_bst.delete(4);
        System.out.println(ex_3_5_9_bst.get(4));
        ex_3_5_9_bst.put(1,1);
        ex_3_5_9_bst.put(1,3);
        System.out.println(ex_3_5_9_bst.get(1));
    }
    private final static boolean RED=true;
    private final static boolean BLACK=false;

    private RedBlackBSTNode root;

    public void delete(Key key){
        if(key==null){
            throw new IllegalArgumentException();
        }
        if (!contains(key)){
            return ;
        }
        if (!isRed(root.left)&&!isRed(root.right)){
            root.color=RED;
        }
        root=deleteRecursive(key,root);
        if (root!=null){
            root.color=BLACK;
        }
    }

    public boolean contains(Key key){
        return get(key)!=null;
    }

    private RedBlackBSTNode deleteRecursive(Key key,RedBlackBSTNode node){
        if (key.compareTo(node.key)<0){
            //向左删除，如果遇到2-节点，则从右边移动一个过去
            if (!isRed(node.left)&&!isRed(node.left.left)){
                node=moveRedLeft(node);
            }
            node.left=deleteRecursive(key,node.left);
        }else {
            if (isRed(node.left)){
                node=rotateRight(node);
            }
            //如果找到了，并且这个节点是子节点，直接删除，不需要替换
            if (key.compareTo(node.key)==0&&(node.right==null)){
                return null;
            }
            if (!isRed(node.right)&&isRed(node.right.left)){
                node=moveRedRight(node);
            }
            //如果找到了，则用后继节点替换
            if (key.compareTo(node.key)==0){
                //替换value
                node.set=minRecursive(node.right).set;
                node.key=minRecursive(node.right).key;
                node.right=delMinRecursive(node.right);
            }else{
                //如果没找到，向右找
                node.right=deleteRecursive(key,node.right);
            }
        }
        return balance(node);
    }

    private RedBlackBSTNode delMinRecursive(RedBlackBSTNode node){
        if (node.left==null){
            //在寻找最小节点的过程中，所有节点变成3-/4-节点，
            //此时只需要将找到的节点为nulL,并返回给node.left赋值即可
            return null;
        }
        //如果当前节点不是3-/4-节点，则从右边移动一个过来
        if (!isRed(node.left)&&!isRed(node.left.left)){
            node=moveRedLeft(node);
        }
        node.left=delMinRecursive(node.left);
        return balance(node);
    }

    private RedBlackBSTNode balance(RedBlackBSTNode node){
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

    private RedBlackBSTNode minRecursive(RedBlackBSTNode node){
        if (node.left==null){
            return node;
        }
        return minRecursive(node.left);
    }

    private RedBlackBSTNode moveRedRight(RedBlackBSTNode node){
        flipColors(node);
        if (!isRed(node.left.left)){
            node=rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private RedBlackBSTNode moveRedLeft(RedBlackBSTNode node){
        //先全部拉平
        flipColors(node);
        //如果右子节点是3-节点，则移动一个过去
        if (isRed(node.right.left)){
            node.right=rotateRight(node.right);
            node= rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    public HashSet<Value> get(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        RedBlackBSTNode node = getRecursive(key,root);
        return node==null?null:node.set;
    }

    private RedBlackBSTNode getRecursive(Key key,RedBlackBSTNode node){
        if (node==null){
            return null;
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes>0){
            return getRecursive(key,node.right);
        }else if(compareRes<0){
            return getRecursive(key,node.left);
        }else{
            return node;
        }
    }

    public void put(Key key,Value value){
        if (key==null||value==null){
            throw new IllegalArgumentException();
        }
        root=putRecursive(key,value,root);
        root.color=BLACK;
    }

    private RedBlackBSTNode putRecursive(Key key,Value value,RedBlackBSTNode node){
        if (node==null){
            HashSet<Value> set = new HashSet<>();
            set.add(value);
            return new RedBlackBSTNode(key,set,RED);
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes>0){
            node.right=putRecursive(key,value,node.right);
        }else if (compareRes<0){
            node.left=putRecursive(key,value,node.left);
        }else{
            node.set.add(value);
        }
        if (isRed(node.right)&&!isRed(node.left)){
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

    private RedBlackBSTNode rotateLeft(RedBlackBSTNode node){
        RedBlackBSTNode temp=node.right;
        node.right=temp.left;
        temp.left=node;
        temp.color=node.color;
        node.color=RED;
        return temp;
    }

    private RedBlackBSTNode rotateRight(RedBlackBSTNode node){
        RedBlackBSTNode temp=node.left;
        node.left=temp.right;
        temp.right=node;
        temp.color=node.color;
        node.color=RED;
        return temp;
    }

    private void flipColors(RedBlackBSTNode node){
        node.color=!node.color;
        node.left.color=!node.left.color;
        node.right.color=!node.right.color;
    }

    private boolean isRed(RedBlackBSTNode node){
        return node==null?BLACK:node.color;
    }

    class RedBlackBSTNode{
        Key key;
        HashSet<Value> set;
        boolean color;
        RedBlackBSTNode left;
        RedBlackBSTNode right;

        public RedBlackBSTNode(Key key, HashSet<Value> set, boolean color) {
            this.key = key;
            this.set = set;
            this.color = color;
        }
    }
}
