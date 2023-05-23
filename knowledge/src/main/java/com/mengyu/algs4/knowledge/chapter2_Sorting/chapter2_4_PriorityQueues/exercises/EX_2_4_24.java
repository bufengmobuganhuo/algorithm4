package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import com.mengyu.algs4.utils.ArrayUtil;

/**
 * @author zhangyu
 * 2020/2/24 15:46
 * 练习2.4.24：使用链接的优先队列 见笔记
 * https://www.cnblogs.com/ikesnowy/p/9470750.html
 */
public class EX_2_4_24<T extends Comparable<T>> {
    public static void main(String[] args) {
        EX_2_4_24<Integer> ex_2_4_24=new EX_2_4_24<>();
        Integer[] arr= ArrayUtil.createInt(20,100);
        //Integer[] arr={4,1,12,5,12,3,12,6};
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
            ex_2_4_24.insert(arr[i]);
        }
        System.out.println();
        for(int i=0;i<arr.length;i++){
            System.out.print(ex_2_4_24.delMax()+" ");
        }
    }
    private TreeNode root;
    private TreeNode last;
    private int size;

    public T delMax(){
        if (isEmpty()){
            throw new ArrayIndexOutOfBoundsException();
        }
        exch(root,last);
        T max=last.value;
        if (size==1){
            root=null;
            last=null;
            size--;
            return max;
        }else if(size==2){
            root.leftChild=null;
            last=root;
            size--;
            return max;
        }
        TreeNode lastFather=last.father;
        //如果最后一个节点是其根节点的右子节点,
        //则直接删除右子节点，last赋值给他的左兄弟
        if (lastFather.rightChild!=null){
            lastFather.rightChild=null;
            last=lastFather.leftChild;
        }else{
            while (lastFather!=root){
                //保证当前遍历节点在其父节点的左子树上
                if(lastFather!=lastFather.father.leftChild){
                    break;
                }
                lastFather=lastFather.father;
            }
            //如果是last指向根节点左子树，则从根节点开始一直向右找
            if (lastFather==root){
                while (lastFather.rightChild!=null){
                    lastFather=lastFather.rightChild;
                }
                last.father.leftChild=null;
                last=lastFather;
            }else{
                //换一个方向，一直向右找
                lastFather=lastFather.father.leftChild;
                while (lastFather.rightChild!=null){
                    lastFather=lastFather.rightChild;
                }
                last.father.leftChild=null;
                last=lastFather;
            }
        }
        sink(root);
        size--;
        return max;
    }

    public void insert(T item){
        //堆为空
        if (root==null){
            root=new TreeNode();
            root.value=item;
            root.no=1;
            last=root;
            size++;
            return;
        }
        TreeNode node = new TreeNode();
        node.value=item;
        //堆只有一个节点
        if (root==last){
            root.leftChild=node;
            node.father=root;
            last=node;
            swim(last);
            size++;
            return;
        }
        TreeNode lastFather=last.father;
        //当前最后一个节点是左子节点，则向其父节点的右子节点插入新元素
        //对应情况一
        if (lastFather.rightChild==null){
            last.father.rightChild=node;
            node.father=last.father;
            last=node;
            //最后一个节点是右子节点
        }else{
            //保证被遍历元素一直在根节点的左子树
            //向上回溯，如果是从根节点的右子树开始向上回溯，到达根节点，
            // 如果是从根节点的左子树向上回溯，最高到达lasfFather.father的左子节点
            while (lastFather!=root){
                if (lastFather!=lastFather.father.rightChild){
                    break;
                }
                lastFather=lastFather.father;
            }
            //如果到达根节点，说明是从根节点的右子节点回溯，说明整个树已满，
            // 则从左子树开始一直想下找，最终赋值给左子节点
            if (lastFather==root){
                while (lastFather.leftChild!=null){
                    lastFather=lastFather.leftChild;
                }
                node.father=lastFather;
                lastFather.leftChild=node;
            }else{
                lastFather=lastFather.father.rightChild;
                while (lastFather.leftChild!=null){
                    lastFather=lastFather.leftChild;
                }
                node.father=lastFather;
                lastFather.leftChild=node;
            }
        }
        last=node;
        swim(last);
        size++;
    }

    private void sink(TreeNode node){
        while (node.leftChild!=null){
            TreeNode temp=node.leftChild;
            if (node.rightChild!=null&&less(temp.value,node.rightChild.value)){
                temp=node.rightChild;
            }
            if (less(temp.value,node.value)){
                break;
            }
            exch(node,temp);
            node=temp;
        }
    }

    private void swim(TreeNode node){
        while (node.father!=null&&less(node.father.value,node.value)){
            exch(node,node.father);
            node=node.father;
        }
    }

    private void exch(TreeNode node1,TreeNode node2){
        T temp=node1.value;
        node1.value=node2.value;
        node2.value=temp;
    }

    private boolean less(T value1,T value2){
        return value1.compareTo(value2)<0;
    }

    public boolean isEmpty(){
        return size==0;
    }

    private class TreeNode{
        T value;
        int no;
        TreeNode father;
        TreeNode leftChild;
        TreeNode rightChild;
    }
}
