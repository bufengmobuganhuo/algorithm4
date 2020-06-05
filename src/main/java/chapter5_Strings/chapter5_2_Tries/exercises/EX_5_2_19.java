package chapter5_Strings.chapter5_2_Tries.exercises;

import chapter5_Strings.chapter5_2_Tries.TST;
import chapter5_Strings.chapter5_2_Tries.TrieST;

import java.util.Random;

/**
 * @author zhangyu
 * 2020/6/5 10:24
 * 练习5.2.19
 */
public class EX_5_2_19 {

    public static void main(String[] args) {
        Random random=new Random();
        EX_5_2_19 ex_5_2_19=new EX_5_2_19();
        for (int i = 0; i < 5; i++) {
            long randomInt= (long) (random.nextDouble()*10000000000L);
            while (ex_5_2_19.contains(String.valueOf(randomInt))){
                randomInt= (long) (random.nextDouble()*10000000000L);
            }
            String str=String.valueOf(randomInt);
            System.out.println(String.format("(%s) %s-%s",str.substring(0,3),str.substring(3,6),str.substring(6)));
        }
    }

    private final int R=10;
    private TrieST.Node root;

    public boolean contains(String key){
        if (key==null||key.length()==0){
            return false;
        }
        return get(root,key,0)!=null;
    }

    private TrieST.Node get(TrieST.Node node,String key,int index){
        if (node==null){
            return null;
        }
        if (index==key.length()-1){
            return node;
        }
        char chrIndex=key.charAt(index);
        return get(node.next[chrIndex],key,index+1);
    }

    public void add(String key){
        if (key==null||key.length()==0){
            return;
        }
        root=add(root,key,0);
    }

    private TrieST.Node add(TrieST.Node node,String key,int index){
        if (node==null){
            node=new TrieST.Node();
        }
        if (index==key.length()-1){
            return node;
        }
        char chrIndex=key.charAt(index);
        node.next[chrIndex]= add(node.next[chrIndex],key,index+1);
        return node;
    }
}
