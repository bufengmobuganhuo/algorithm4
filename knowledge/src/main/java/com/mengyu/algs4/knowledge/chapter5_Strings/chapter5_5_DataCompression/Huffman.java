package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_5_DataCompression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2020/6/16 8:13 下午
 * 哈夫曼编码
 */
public class Huffman {
    private final int R=256;

    /**
     * @return 读取单词查找树，比特字符串 -->> 单词查找树
     */
    private Node readTrie(){
        //如果读入的是叶子结点
        if (BinaryStdIn.readBoolean()){
            //创建一个叶子结点（其左右子树位null）
            return new Node(BinaryStdIn.readChar(),0,null,null);
        }
        //不是叶子结点，则创建一个新的内部结点，并递归调用
        return new Node('\0',0,readTrie(),readTrie());
    }


    /**
     * @param node 写入一个单词查找树 查找树 -->> 比特字符串
     */
    private void writeTrie(Node node){
        //如果是叶子结点
        if (node.isLeaf()){
            //写入一个比特=1
            BinaryStdOut.write(true);
            //写入叶子结点的字符
            BinaryStdOut.write(node.chr);
            return;
        }
        //前序遍历，遍历左右子树
        writeTrie(node.left);
        writeTrie(node.right);
    }

    /**
     * 将从输入流中获取的字符串转化成哈夫曼编码（压缩）
     */
    public void compress(){
        //从输入流中读取一个字符串
        String input = BinaryStdIn.readString();
        char[] inputChrs = input.toCharArray();

        //统计频率
        int[] freq = new int[R];
        for (int i = 0; i < inputChrs.length; i++) {
            freq[inputChrs[i]]++;
        }

        //根据频率创建一个单词查找树
        Node root = buildTrie(freq);

        //构造每个字符与对应的哈夫曼编码的映射表
        String[] st=buildST(root);

        //打印解码用的单词查找树
        writeTrie(root);

        //打印原字符串的字符总数
        BinaryStdOut.write(input);

        //使用哈夫曼编码处理上述输入字符串
        for (int i = 0; i < input.length(); i++) {
            //找到字符对应的哈夫曼编码
            String code=st[input.charAt(i)];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j)=='1'){
                    BinaryStdOut.write(true);
                }else{
                    BinaryStdOut.write(false);
                }
            }
        }
    }

    /**
     * 构造单词查找树
     * @param freq freq[i]:字符i出现的频率
     * @return
     */
    private Node buildTrie(int[] freq){
        //使用优先队列，可以很方便的从中删除频率最小的结点
        PriorityQueue<Node> priorityQueue=new PriorityQueue<>(R);
        for (char chr = 0; chr < R; chr++) {
            //大于0的说明在文本中出现过，才有统计意义
            //构造n个
            if (freq[chr]>0){
                priorityQueue.add(new Node(chr,freq[chr],null,null));
            }
        }
        while(priorityQueue.size()>1){
            Node left=priorityQueue.poll();
            Node right=priorityQueue.poll();
            Node parent= new Node('\0',left.freq+right.freq,left,right);
            priorityQueue.add(parent);
        }
        return priorityQueue.poll();
    }

    /**
     * @param root
     * @return 构建字符 <--> 比特字符串之间的映射
     */
    private String[] buildST(Node root){
        String[] st=new String[R];
        buildST(st,root,"");
        return st;
    }

    private void buildST(String[] st, Node node,String res){
        //如果是叶子结点，则找到了一个字符 <--> 比特字符串 的映射
        if (node.isLeaf()){
            st[node.chr]=res;
            return;
        }
        buildST(st,node.left,res+'0');
        buildST(st,node.right,res+'1');
    }

    /**
     * 解码，比特流 -> 字符串
     */
    public void expand(){
        //读入单词查找树
        Node root=readTrie();
        //字符串的长度（编码前的原字符串长度）
        int length= BinaryStdIn.readInt();
        for (int i = 0; i < length; i++) {
            Node node=root;
            while(!node.isLeaf()){
                //如果读取的是1，向右找
                if (BinaryStdIn.readBoolean()){
                    node=node.right;
                }else{
                    node=node.left;
                }
            }
            //遇到了叶子结点，则写入
            BinaryStdOut.write(node.chr);
        }
        BinaryStdOut.close();
    }

    private static class Node implements Comparable<Node>{
        //叶子结点上对应的字符
        private char chr;
        //字符出现的频率
        private int freq;
        private final Node left,right;

        public Node(char chr,int freq,Node left,Node right){
            this.chr=chr;
            this.freq=freq;
            this.left=left;
            this.right=right;
        }

        //当前结点是否是叶子结点
        public boolean isLeaf(){
            return right==null&&left==null;
        }

        @Override
        public int compareTo(Node o) {
            return freq-o.freq;
        }
    }
}
