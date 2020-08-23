package chapter5_Strings.chapter5_2_Tries.exercises;

import chapter5_Strings.chapter5_2_Tries.TrieST;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/8/17 10:25 上午
 * TODO
 */
public class TrieSt extends TrieST<Integer> {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>(4);
        map.put("she",0);
        map.put("sells",1);
        map.put("sea",2);
        map.put("shells",3);
        TrieSt trieST=new TrieSt();
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            trieST.put(entry.getKey(),entry.getValue());
        }
        System.out.println(trieST.get("sells"));
        Queue<String> queue=trieST.keysWithPrefix("sh");
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
        System.out.println("------------");
        queue=trieST.keys();
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
        System.out.println("------------");
        queue=trieST.keysThatMatch("se..s");
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
        System.out.println("------------");
        System.out.println(trieST.longestPrefixOf("sheIsAGirl"));
        trieST.delete("sea");
        System.out.println("------------");
        System.out.println(trieST.size());
    }
    private int size;
    private static final int R=256;
    private Node root;

    @Override
    public void delete(String key) {
        root=delete(root,key,0);
    }

    private Node delete(Node node,String key,int len){
        if (node==null){
            return null;
        }
        if (len==key.length()){
            if (node.val!=null){
                node.val=null;
                size--;
            }
        }else{
            delete(node.next[key.charAt(len)],key,len+1);
        }
        // 如果没找到，则直接返回
        if (node.val!=null){
            return node;
        }
        for (int i = 0; i < R; i++) {
            if (node.next[i]!=null){
                return node.next[i];
            }
        }
        return null;
    }

    @Override
    public String longestPrefixOf(String str) {
        int maxLen=search(root,str,0,0);
        return str.substring(0,maxLen);
    }

    private int search(Node node,String str,int index,int maxLen){
        if (node==null){
            return maxLen;
        }
        if (node.val!=null){
            maxLen=index;
        }
        char nextChr=str.charAt(index);
        return search(node.next[nextChr],str,index+1,maxLen);
    }

    @Override
    public Queue<String> keysThatMatch(String pattern) {
        Queue<String> queue=new LinkedList<>();
        collect(root,"",pattern,queue);
        return queue;
    }

    private void collect(Node node,String pre,String pattern,Queue<String> queue){
        if (node==null){
            return;
        }
        if (pre.length()==pattern.length()&&node.val!=null){
            queue.offer(pre);
            return;
        }
        // 虽然找到了匹配，但是并不是键
        if (pre.length()==pattern.length()){
            return;
        }
        int len=pre.length();
        for (char chr = 0; chr < R; chr++) {
            if (pattern.charAt(len)=='.'||pattern.charAt(len)==chr){
                collect(node.next[chr],pre+chr,pattern,queue);
            }
        }

    }

    @Override
    public Queue<String> keys() {
        return keysWithPrefix("");
    }

    @Override
    public Queue<String> keysWithPrefix(String pre) {
        Queue<String> queue=new LinkedList<>();
        collect(get(pre,0,root),pre,queue);
        return queue;
    }

    private void collect(Node node,String pre, Queue<String> queue){
        if (node==null){
            return;
        }
        if (node.val!=null){
            queue.offer(pre);
        }
        for (char chr = 0; chr < R; chr++) {
            collect(node.next[chr],pre+chr,queue);
        }
    }

    @Override
    public void put(String key, Integer s) {
        root = put(key,s,root,0);
    }

    private Node put(String key,Integer value,Node node, int len){
        if (node==null){
            node = new Node();
        }
        if (len==key.length()){
            size=node.val==null?size+1:size;
            node.val=value;
            return node;
        }
        char nextChr=key.charAt(len);
        node.next[nextChr]= put(key,value,node.next[nextChr],len+1);
        return node;
    }

    @Override
    public Integer get(String key) {
        Node node = get(key, 0, root);
        return node==null?null: (Integer) node.val;
    }

    private Node get(String key,int len,Node node){
        if (node==null){
            return null;
        }
        if (len==key.length()){
            return node;
        }
        char nextChr=key.charAt(len);
        return get(key,len+1,node.next[nextChr]);
    }

    @Override
    public int size() {
        return size;
    }
}
