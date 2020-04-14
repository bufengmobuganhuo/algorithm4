package chapter3_Searching.chapter3_5_Applications.exercises;

/**
 * @author zhangyu
 * 2020/4/10 10:34
 * 练习3.5.1：使用红黑树实现set
 */
public class EX_3_5_1_SET<Key extends Comparable<Key>> {
    public static void main(String[] args) {
        EX_3_5_1_SET<Integer> set=new EX_3_5_1_SET<>();
        set.put(1);
        set.put(3);
        set.put(5);
        set.put(6);
        set.put(2);
        System.out.println(set.contains(3));
        System.out.println(set.contains(4));
        set.delete(5);
        set.delete(4);
        System.out.println(set.size);
        System.out.println(set.contains(5));

    }
    private final boolean RED=true;
    private final boolean BLACK=false;
    private RedBlackSetNode root;
    protected int size;

    public void delete(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        if (!contains(key)){
            return;
        }
        if (!isRED(root.left)&&!isRED(root.right)){
            root.color=RED;
        }
        root=deleteRecursive(root,key);
        size--;
        if (!isEmpty()){
            root.color=BLACK;
        }
    }

    private RedBlackSetNode deleteRecursive(RedBlackSetNode node,Key key){
        //向左删除
        if (key.compareTo(node.key)<0){
            if (!isRED(node.left)&&!isRED(node.left.left)){
                node= moveRedLeft(node);
            }
            node.left=deleteRecursive(node.left,key);
        }else{
            if (isRED(node.left)){
                node=rotateRight(node);
            }
            //找到待删除键，则赋值为null
            if (key.compareTo(node.key)==0&&node.right==null){
                return null;
            }
            //向右删除，如果右边遇到了2-节点，则从左边移动一个
            if (!isRED(node.right)&&!isRED(node.right.left)){
                node=moveRedRight(node);
            }
            //向右删除时找到待删除节点，
            // 删除时找到后继节点替换，并删除后继节点原来的位置
            if (key.compareTo(node.key)==0){
                node.key=minRecursive(node.right).key;
                node.right=deleteMinRecursive(node.right);
            }else{
                node.right=deleteRecursive(node.right,key);
            }
        }
        return balance(node);
    }

    private RedBlackSetNode minRecursive(RedBlackSetNode node){
        if (node.left==null){
            return node;
        }
        return minRecursive(node.left);
    }

    private RedBlackSetNode deleteMinRecursive(RedBlackSetNode node){
        if (node.left==null){
            //在查找最小键的过程中，已经保证了都是3-或4-节点，
            // 故此处直接将找到的最小键赋值为null
            return null;
        }
        //如果当前节点是一个2-节点，则从右边移动一个节点到左边
        if (!isRED(node.left)&&!isRED(node.left.left)){
            node=moveRedLeft(node);
        }
        node.left=deleteMinRecursive(node.left);
        //重新调整
        return balance(node);
    }

    /**
     * @param node 重新调整红黑树，使其满足条件
     * @return
     */
    private RedBlackSetNode balance(RedBlackSetNode node){
        //如果右边是红色，则向左旋转
        if (isRED(node.right)){
            node=rotateLeft(node);
        }
        //如果左子树出现连续红色链接，则将上层红色向右旋转
        if (isRED(node.left)&&isRED(node.left.left)){
            node=rotateRight(node);
        }
        //如果Node的左右子节点都是红色，则反转颜色
        if (isRED(node.left)&&isRED(node.right)){
            flipColors(node);
        }
        return node;
    }

    /**
     * @param node 移动一个节点到左边
     * @return
     */
    private RedBlackSetNode moveRedLeft(RedBlackSetNode node){
        flipColors(node);
        if (isRED(node.right.left)){
            //先右旋转，将较小值上移
            node.right=rotateRight(node.right);
            //左旋转，将较小值下移到左子树
            node=rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    /**
     * @param node 移动一个节点到右边
     * @return
     */
    private RedBlackSetNode moveRedRight(RedBlackSetNode node){
        flipColors(node);
        if (!isRED(node.left.left)){
            //右旋转将当前节点移入右边
            node=rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    public void put(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        root=putRecursive(root,key);
        //根节点颜色必须为黑色
        root.color=BLACK;
        size++;
    }

    private RedBlackSetNode putRecursive(RedBlackSetNode node, Key key){
        if (node==null){
            return new RedBlackSetNode(key,RED);
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes>0){
            node.right=putRecursive(node.right,key);
        }else if(compareRes<0){
            node.left=putRecursive(node.left,key);
        }
        //如果右子节点红色，左子节点黑色，则左旋转
        if (isRED(node.right)&&!isRED(node.left)){
            node=rotateLeft(node);

        }
        //如果连续两个左子节点均为红色，则将上层左节点右旋转
        if (isRED(node.left)&&isRED(node.left.left)){
            node= rotateRight(node.left);
        }
        //如果左右节点均为红色，则反转颜色
        if(isRED(node.right)&&isRED(node.left)){
            flipColors(node);

        }
        return node;
    }

    private boolean isRED(RedBlackSetNode node){
        if (node==null){
            return false;
        }
        return node.color;
    }

    public boolean contains(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        return containsRecursive(root,key);
    }

    private boolean containsRecursive(RedBlackSetNode node, Key key){
        if (node==null){
            return false;
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes==0){
            return true;
        }else if(compareRes>0){
            return containsRecursive(node.right,key);
        }else{
            return containsRecursive(node.left,key);
        }
    }

    public boolean isEmpty(){
        return size==0;
    }

    private void flipColors(RedBlackSetNode node){
        node.color=!node.color;
        node.left.color=!node.left.color;
        node.right.color=!node.right.color;
    }

    //将红链接向左旋转
    private RedBlackSetNode rotateLeft(RedBlackSetNode fatherNode){
        RedBlackSetNode temp=fatherNode.right;
        fatherNode.right=temp.left;
        temp.left=fatherNode;
        temp.color=fatherNode.color;
        fatherNode.color=RED;
        return temp;
    }

    //将红链接向右旋转
    private RedBlackSetNode rotateRight(RedBlackSetNode fatherNode){
        RedBlackSetNode temp=fatherNode.left;
        fatherNode.left=temp.right;
        temp.right=fatherNode;
        temp.color=fatherNode.color;
        fatherNode.color=RED;
        return temp;
    }

    class RedBlackSetNode{
        Key key;
        boolean color;
        RedBlackSetNode left;
        RedBlackSetNode right;

        public RedBlackSetNode(Key key, boolean color) {
            this.key = key;
            this.color = color;
        }
    }
}
