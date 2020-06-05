package chapter5_Strings.chapter5_2_Tries.exercises;

import chapter5_Strings.chapter5_2_Tries.TST;

/**
 * @author zhangyu
 * 2020/6/5 9:59
 * TODO
 */
public class EX_5_2_17 {

    private TST.TSTNode root;

    public boolean contains(String key){
        if (key==null||key.length()==0){
            throw new IllegalArgumentException();
        }
        return get(root,key,0)!=null;
    }

    private TST.TSTNode get(TST.TSTNode node, String key,int index){
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
            throw new IllegalArgumentException();
        }

    }

    private TST.TSTNode add(TST.TSTNode node, String key,int index){
        char chr=key.charAt(index);
        if (node==null){
            node=new TST.TSTNode();
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
}
