package chapter5_Strings.chapter5_2_Tries.exercises;

import chapter5_Strings.chapter5_2_Tries.TrieST;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @author zhangyu
 * 2020/6/4 14:44
 * 练习5.2.6
 */
public class EX_5_2_6_StringSet {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>(4);
        map.put("she",0);
        map.put("sells",1);
        map.put("sea",2);
        map.put("shells",3);
        EX_5_2_6_StringSet stringSet=new EX_5_2_6_StringSet();
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            stringSet.add(entry.getKey());
        }
        System.out.println(stringSet.contains("sells"));

    }
    private int size;
    private final int R=256;
    private StringSetNode root;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return root!=null;
    }

    public boolean contains(String key){
        return get(root,key,0)!=null;
    }

    private StringSetNode get(StringSetNode node, String key,int index){
        if (node==null){
            return null;
        }
        char chr=key.charAt(index);
        if (chr<node.chr){
            return get(node.left,key,index);
        }else if(chr>node.chr){
            return get(node.right,key,index);
        }else if(index<key.length()-1){
            return get(node.mid,key,index+1);
        }
        return node;
    }


    public void add(String key){
        if (key==null||key.length()==0){
            return;
        }
        if (!contains(key)){
            size++;
        }
        root=add(root,key,0);
    }

    private StringSetNode add(StringSetNode node, String key,int index){
        char chr=key.charAt(index);
        if (node==null){
            node=new StringSetNode();
            node.chr=chr;
        }
        if (chr<node.chr){
            node.left=add(node.left,key,index);
        }else if(chr>node.chr){
            node.right=add(node.right,key,index);
        }else if(index<key.length()-1){
            node.mid=add(node.mid,key,index+1);
        }
        return node;
    }

    private class StringSetNode{
        char chr;
        StringSetNode left,mid,right;
    }
}
