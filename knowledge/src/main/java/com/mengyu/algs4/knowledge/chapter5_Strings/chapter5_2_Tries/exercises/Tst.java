package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_2_Tries.exercises;

import com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_2_Tries.TST;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/8/17 11:29 上午
 * TODO
 */
public class Tst extends TST<Integer> {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>(4);
        map.put("she",0);
        map.put("sells",1);
        map.put("sea",2);
        map.put("shells",3);
        Tst tst=new Tst();
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            tst.put(entry.getKey(),entry.getValue());
        }
        System.out.println(tst.get("sells"));
        Queue<String> queue=tst.keysWithPrefix("sh");
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
        System.out.println("------------");
        queue=tst.keys();
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
        System.out.println("------------");
        queue=tst.keysThatMatch("se..s");
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
        System.out.println("------------");
        System.out.println(tst.longestPrefixOf("sheIsAGirl"));
    }
    private int size;
    private TSTNode root;
    @Override
    public String longestPrefixOf(String str) {
        TSTNode node=root;
        int index=0;
        int length=0;
        while(node!=null&&index<str.length()){
            char chr=str.charAt(index);
            if (node.chr<chr){
                node=node.right;
            }else if (node.chr>chr){
                node=node.left;
            }else{
                index++;
                if (node.val!=null){
                    length=index;
                }
                node=node.mid;
            }
        }
        return str.substring(0,length);
    }

    @Override
    public Queue<String> keysThatMatch(String pattern) {
        Queue<String> queue=new LinkedList<>();
        collect(root,new StringBuilder(),pattern,0,queue);
        return queue;
    }

    private void collect(TSTNode node,StringBuilder pre,String pattern,int index,Queue<String> queue){
        if (node==null){
            return;
        }
        char chr=pattern.charAt(index);
        // 从左边找
        if (chr=='.'||node.chr>chr){
            collect(node.left,pre,pattern,index,queue);
        }
        if (chr=='.'||chr==node.chr){
            if (index==pattern.length()-1&&node.val!=null){
                queue.offer(pre.toString()+node.chr);
            }
            if (index<pattern.length()-1){
                collect(node.mid,pre.append(node.chr),pattern,index+1,queue);
                pre.deleteCharAt(pre.length()-1);
            }
        }
        if (chr=='.'||node.chr<chr){
            collect(node.left,pre,pattern,index,queue);
        }
    }

    @Override
    public Queue<String> keys() {
        Queue<String> queue=new LinkedList<>();
        collect(root,new StringBuilder(""),queue);
        return queue;
    }

    @Override
    public Queue<String> keysWithPrefix(String pre) {
        Queue<String> queue=new LinkedList<>();
        TSTNode node = get(root,pre,0);
        if (node==null){
            return queue;
        }else if (node.val!=null){
            queue.offer(pre);
        }
        collect(node.mid,new StringBuilder(pre),queue);
        return queue;
    }

    private void collect(TSTNode node,StringBuilder pre,Queue<String> queue){
        if (node==null){
            return;
        }
        collect(node.left,pre,queue);
        if (node.val!=null){
            queue.offer(pre.toString());
        }
        collect(node.mid,pre.append(node.chr),queue);
        collect(node.right,pre.deleteCharAt(pre.length()-1),queue);
    }

    @Override
    public Integer get(String key) {
        TSTNode node =get(root,key,0);
        return node==null?null: (Integer) node.val;
    }

    private TSTNode get(TSTNode node,String key,int index){
        if (node==null){
            return null;
        }
        char chr=key.charAt(index);
        if (chr<node.chr){
            return get(node.left,key,index);
        }else if (chr>node.chr){
            return get(node.right,key,index);
        }else if (index<key.length()-1){
            return get(node.mid,key,index+1);
        }
        return node;
    }

    @Override
    public void put(String key, Integer val) {
        if (!containsKey(key)){
            size++;
        }
        root=put(root,key,val,0);
    }

    private TSTNode put(TSTNode node,String key,Integer val,int index){
        char chr=key.charAt(index);
        if (node==null){
            node = new TSTNode();
            node.chr=chr;
        }
        if (node.chr<chr){
            node.right=put(node.right,key,val,index);
        }else if (node.chr>chr){
            node.left=put(node.left,key,val,index);
        }else if (index<key.length()-1){
            node.mid=put(node.mid,key,val,index+1);
        }else{
            node.val=val;
        }
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(String key) {
        return super.containsKey(key);
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }
}
