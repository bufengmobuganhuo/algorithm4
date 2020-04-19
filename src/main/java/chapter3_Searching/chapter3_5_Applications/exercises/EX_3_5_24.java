package chapter3_Searching.chapter3_5_Applications.exercises;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyu
 * 2020/4/16 19:39
 * 练习3.5.24：不重叠区间的查找
 *  使用红黑树，每个节点增加两个字段，以表明该节点是上限还是下限
 */
public class EX_3_5_24 {
    public static void main(String[] args) {
        EX_3_5_24 ex_3_5_24=new EX_3_5_24();
        ex_3_5_24.put(1643,true);
        ex_3_5_24.put(2033,false);

        ex_3_5_24.put(5532,true);
        ex_3_5_24.put(7643,false);

        ex_3_5_24.put(8998,true);
        ex_3_5_24.put(10332,false);

        ex_3_5_24.put(5666653,true);
        ex_3_5_24.put(5669321,false);

        System.out.println(ex_3_5_24.get(9122));
        System.out.println(ex_3_5_24.get(8122));
        System.out.println(ex_3_5_24.get(0));
        System.out.println(ex_3_5_24.get(7642));
        System.out.println(ex_3_5_24.get(5669321));
    }
    private static final boolean RED=true;
    private static final boolean BLACK=false;
    private static final boolean LOWER=true;
    private static final boolean UPPER=false;
    private LimitNode root;

    public String get(int key){
        Map<String,LimitNode> map=new HashMap<>(2);
        getRecursive(key,root,map);
        LimitNode upper=map.get("upper");
        LimitNode lower=map.get("lower");
        if (upper!=null&&upper.key>=key&&lower!=null&&lower.key<=key){
            return lower.key+"-"+upper.key;
        }
        return "";
    }

    private LimitNode getRecursive(Integer key, LimitNode node, Map<String,LimitNode> map){
        if (node==null){
            return null;
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes>0){
            //如果待查找键>node.key,且node是下限节点，则放入map
            if (isLower(node)){
                map.put("lower",node);
            }
            return getRecursive(key,node.right,map);
        }else if(compareRes<0){
            //如果待查找键<node.key
            if (isUpper(node)){
                map.put("upper",node);
            }
            return getRecursive(key,node.left, map);
        }else{
            if (isUpper(node)){
                map.put("upper",node);
                if (!map.containsKey("lower")){
                    map.put("lower",maxRecursive(node.left));
                }
            }else{
                map.put("lower",node);
                if (!map.containsKey("upper")){
                    map.put("upper",minRecursive(node.right));
                }
            }
            return node;
        }
    }

    private LimitNode minRecursive(LimitNode node){
        if (node.left==null){
            return node;
        }
        return minRecursive(node.left);
    }

    private LimitNode maxRecursive(LimitNode node){
        if (node.right==null){
            return node;
        }
        return maxRecursive(node.right);
    }

    public void put(int key,boolean isLower){
        root=putRecursive(key,isLower,root);
        root.color=BLACK;
    }

    private LimitNode putRecursive(Integer key,boolean isLower,LimitNode node){
        if (node==null){
            return new LimitNode(key,isLower,RED);
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes>0){
            node.right=putRecursive(key,isLower,node.right);
        }else if(compareRes<0){
            node.left=putRecursive(key,isLower,node.left);
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

    private void flipColors(LimitNode node){
        node.color=!node.color;
        node.left.color=!node.left.color;
        node.right.color=!node.right.color;
    }

    private LimitNode rotateRight(LimitNode node){
        LimitNode temp=node.left;
        node.left=temp.right;
        temp.right=node;
        temp.color=node.color;
        node.color=RED;
        return temp;
    }

    private LimitNode rotateLeft(LimitNode node){
        LimitNode temp=node.right;
        node.right=temp.left;
        temp.left=node;
        temp.color=node.color;
        node.color=RED;
        return temp;
    }

    private boolean isLower(LimitNode node){
        return node != null && node.isLower;
    }

    private boolean isUpper(LimitNode node){
        return node != null && !node.isLower;
    }

    private boolean isRed(LimitNode node){
        return node==null?BLACK:node.color;
    }

    class LimitNode{
        int key;
        boolean isLower;
        boolean color;
        LimitNode right;
        LimitNode left;

        public LimitNode(int key, boolean isLower, boolean color) {
            this.key = key;
            this.isLower = isLower;
            this.color = color;
        }
    }
}
