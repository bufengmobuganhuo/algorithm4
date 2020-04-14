package chapter3_Searching.chapter3_5_Applications.exercises;

import sun.awt.SunHints;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author zhangyu
 * 2020/4/12 14:12
 * TODO
 */
public class EX_3_5_9_BST<Key extends Comparable<Key>,Value> {
    public static void main(String[] args) {
        EX_3_5_9_BST<Integer,Integer> ex_3_5_9_bst=new EX_3_5_9_BST<>();
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
    private BSTNode root;

    public void delete(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        root=deleteRecursive(key,root);
    }

    private BSTNode deleteRecursive(Key key,BSTNode node){
        if (node==null){
            return null;
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes<0){
            node.left=deleteRecursive(key,node.left);
        }else if(compareRes>0){
            node.right=deleteRecursive(key,node.right);
            //找到了节点
        }else{
            //待删除节点只有一个子节点
            if (node.left==null){
                return node.right;
            }
            if (node.right==null){
                return node.left;
            }
            //临时保存待删除节点
            BSTNode deletedNode=node;
            //找到后继节点
            node=minRecursive(node.right);
            //将后继节点从原位置删除,后继节点在待删除节点右子树，删除后，会返回一个右子树的根节点，重新赋值给node
            node.right=delMinRecursive(deletedNode.right);
            node.left=deletedNode.left;
        }
        node.nodeCount=size(node.left)+size(node.right)+1;
        return node;
    }

    private BSTNode minRecursive(BSTNode node){
        if (node.left==null){
            return node;
        }
        return minRecursive(node.left);
    }

    private BSTNode delMinRecursive(BSTNode node){
        if (node.left==null){
            return node.right;
        }
        node.left=delMinRecursive(node.left);
        node.nodeCount=size(node.left)+size(node.right)+1;
        return node;
    }

    public HashSet<Value> get(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        BSTNode node = getRecursive(key,root);
        return node==null?null:node.values;
    }

    private BSTNode getRecursive(Key key,BSTNode node){
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

    public void put(Key key,Value value){
        if (key==null||value==null){
            throw new IllegalArgumentException();
        }
        root=putRecursive(key,value,root);
    }

    public BSTNode putRecursive(Key key,Value value,BSTNode node){
        if (node==null){
            HashSet<Value> set = new HashSet<>();
            set.add(value);
            return new BSTNode(key,set,1);
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes==0){
            node.values.add(value);
        }else if(compareRes>0){
            node.right=putRecursive(key,value,node.right);
        }else{
            node.left=putRecursive(key,value,node.left);
        }
        node.nodeCount=size(node.left)+size(node.right)+1;
        return node;
    }

    private int size(BSTNode node){
        if (node==null){
            return 0;
        }
        return node.nodeCount;
    }


    class BSTNode{
        Key key;
        HashSet<Value> values;
        int nodeCount;
        BSTNode left;
        BSTNode right;

        public BSTNode(Key key, HashSet<Value> values, int nodeCount) {
            this.key = key;
            this.values = values;
            this.nodeCount = nodeCount;
        }
    }
}
