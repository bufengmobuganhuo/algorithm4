package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_3_BalancedSearchTrees;


/**
 * @author zhangyu
 * 2020/3/26 17:04
 * TODO
 */
public class RedBlackBST<Key extends Comparable<Key>,Value> {
    public static void main(String[] args) {
        RedBlackBST<String,String> redBlackBST=new RedBlackBST<>();
        redBlackBST.put("Y","S");
        redBlackBST.put("L","E");
        redBlackBST.put("P","A");
        redBlackBST.put("M","R");
        redBlackBST.put("X","C");
        redBlackBST.put("H","H");
        redBlackBST.put("C","X");
        redBlackBST.put("R","M");
        redBlackBST.put("A","P");
        redBlackBST.put("E","L");
        redBlackBST.put("S","L");
        System.out.println(redBlackBST.min().key);
        redBlackBST.deleteMin();
        System.out.println(redBlackBST.max().key);
        System.out.println(redBlackBST.rank("H"));
        System.out.println(redBlackBST.select(3).key);
        System.out.println(redBlackBST.floor("G").key);
        System.out.println(redBlackBST.ceil("G").key);
        redBlackBST.delete("x");
        //redBlackBST.deleteMin();
        //redBlackBST.deleteMin();
    }
    private final boolean RED=true;
    private final boolean BLACK=false;
    protected RedBlackTreeNode<Key,Value> root;

    public boolean contains(Key key){
        return get(key)!=null;
    }

    /**
     * @param key >=key的最小值
     * @return
     */
    public RedBlackTreeNode<Key,Value> ceil(Key key){
        if (key==null||root==null){
            throw new IllegalArgumentException();
        }
        return ceilRecursive(root,key);
    }

    private RedBlackTreeNode<Key,Value> ceilRecursive(RedBlackTreeNode<Key,Value> node, Key key){
        if (node==null){
            return null;
        }
        int compareRes=key.compareTo(node.key);
        //如果key=node.key，则就是>=key的最小值
        if (compareRes==0){
            return node;
            //如果key>node.key，则向右找
        }else if (compareRes>0){
            return ceilRecursive(node.right,key);
        }
        //如果key<node.key，则只有在node的左子树中存在>=key的最小键时，才是结果
        //否则就是node
        RedBlackTreeNode<Key,Value> ceilNode=ceilRecursive(node.left, key);
        return ceilNode==null?node : ceilNode;
    }

    /**
     * @param key <=key的最大值
     * @return
     */
    public RedBlackTreeNode<Key,Value> floor(Key key){
        if (key==null||root==null){
            throw new IllegalArgumentException();
        }
        return floorRecursive(root,key);
    }

    private RedBlackTreeNode<Key,Value> floorRecursive(RedBlackTreeNode<Key,Value> node, Key key){
        if (node==null){
            return null;
        }
        int compareRes=key.compareTo(node.key);
        //如果key=node.key，则<=key的最大值，就是node
        if (compareRes==0){
            return node;
            //如果key<node.key，则<=key的最大值在node的左子树中
        }else if(compareRes<0){
            return floorRecursive(node.left, key);
        }
        //如果key>node.key，则只有在node的右子树中存在<=key的最大值时，才是结果，
        // 否则就是Node
        RedBlackTreeNode<Key,Value> floorNode=floorRecursive(node.right,key);
        return floorNode==null?node : floorNode;
    }

    public RedBlackTreeNode<Key,Value> select(int rank){
        if (root==null||rank<0){
            return null;
        }
        return selectRecursive(root,rank);
    }

    private RedBlackTreeNode<Key,Value> selectRecursive(RedBlackTreeNode<Key,Value> node, int rank){
        if (node==null){
            return null;
        }
        int nodeRank=size(node.left);
        //如果排名相等，则找到了键
        if (nodeRank==rank){
            return node;
            //node的排名>rank，说明node较大，则向左子树找
        }else if(nodeRank>rank){
            return selectRecursive(node.left,rank);
        }else{
            //node的排名<rank，说明node较小，则向右子树找
            return selectRecursive(node.right,rank-size(node.left)-1);
        }
    }

    public int rank(Key key){
        if (key==null||root==null){
            return -1;
        }
        return rankRecursive(root,key);
    }

    private int rankRecursive(RedBlackTreeNode<Key,Value> node, Key key){
        //node=null，说明没有找到键
        if (node==null){
            return -1;
        }
        int compareRes=key.compareTo(node.key);
        //如果找到节点，则排名是左子树节点的个数
        if (compareRes==0){
            return size(node.left);
            //key<node.key，说明要查找的键在左子树，排名为在左子树中的排名
        }else if(compareRes<0){
            return rankRecursive(node.left, key);
        }else{
            //key>node.key，说明要查找的键在右子树，
            // 排名为左子树节点个数+1（root）+该键在右子树中的排名
            return size(node.left)+1+rankRecursive(node.right,key);
        }
    }

    public RedBlackTreeNode<Key,Value> max(){
        if (isEmpty()){
            return null;
        }
        return maxRecursive(root);
    }

    private RedBlackTreeNode<Key,Value> maxRecursive(RedBlackTreeNode<Key,Value> node){
        if (node.right==null){
            return node;
        }
        return maxRecursive(node.right);
    }

    public RedBlackTreeNode<Key,Value> min(){
        if (isEmpty()) {
            return null;
        }
        return minRecursive(root);
    }

    private RedBlackTreeNode<Key,Value> minRecursive(RedBlackTreeNode<Key,Value> node){
        if (node.left==null){
            return node;
        }
        return minRecursive(node.left);
    }

    public RedBlackTreeNode<Key,Value> get(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        return getRecursive(root,key);
    }

    private RedBlackTreeNode<Key,Value> getRecursive(RedBlackTreeNode<Key,Value> node, Key key){
        if (node==null){
            return null;
        }
        int compareRes=key.compareTo(node.key);
        if (compareRes==0){
            return node;
        }else if(compareRes>0){
            return getRecursive(node.right,key);
        }else{
            return getRecursive(node.left,key);
        }
    }

    public void delete(Key key){
        if (!contains(key)){
            return;
        }
        if (!isRed(root.left)&&!isRed(root.right)){
            root.color=RED;
        }

        root=deleteRecursive(root,key);
        if (!isEmpty()){
            root.color=BLACK;
        }
    }

    private RedBlackTreeNode<Key,Value> deleteRecursive(RedBlackTreeNode<Key,Value> node,Key key){
        if (key.compareTo(node.key)<0){
            //向左删除,类似删除最小值，所以如果遇到2-节点，从右边移动一个过来
            if (!isRed(node.left)&&!isRed(node.left.left)){
                node=moveRedLeft(node);
            }
            node.left=deleteRecursive(node.left,key);
        }else{
            if (isRed(node.left)){
                node=rotateRight(node);
            }
            //如果找到了，并且这个节点是子节点，直接删除，不需要替换
            if (key.compareTo(node.key)==0&&(node.right==null)){
                return null;
            }
            //向右删除，类似删除最大值，如果要2-节点，从左边移动一个过来
            if (!isRed(node.right)&&!isRed(node.right.left)){
                node=moveRedRight(node);
            }
            if (key.compareTo(node.key)==0){
                //找到后继节点替换
                RedBlackTreeNode<Key,Value> susNode=minRecursive(node.right);
                node.value=susNode.value;
                node.key=susNode.key;
                node.right=deleteMinRecursive(node.right);
            }else{
                node.right=deleteRecursive(node.right,key);
            }
        }
        return balance(node);
    }


    public void deleteMax(){
        //如果根节点是一个2-节点，则将其变为3-节点
        if (!isRed(root.left)&&!isRed(root.right)){
            root.color=RED;
        }
        root=deleteMaxRecursive(root);
        if (!isEmpty()){
            root.color=BLACK;
        }
    }

    private RedBlackTreeNode<Key,Value> deleteMaxRecursive(RedBlackTreeNode<Key,Value> node){
        if (isRed(node.left)){
            rotateRight(node);
        }
        if (node.right==null){
            //插入操作保证所有节点都是3-节点，或4-节点，
            // 所以直接返回一个null给node.right赋值即可（直接删除）
            return null;
        }
        //如果当前节点的右子节点是2-节点，则从右子节点的兄弟节点移动一个节点到该右子节点
        if (!isRed(node.right)&&!isRed(node.right.left)){
            node=moveRedRight(node);
        }
        node.right=deleteMaxRecursive(node.right);
        //重新调整，令其满足红黑树条件
        return balance(node);
    }

    /**
     * @param node 从左子树中移动节点到右边（移动的是传入的父节点：node）
     * @return
     */
    private RedBlackTreeNode<Key,Value> moveRedRight(RedBlackTreeNode<Key,Value> node){
        //先将node，node.left，node.right中的某几个拉平
        flipColors(node);
        if (!isRed(node.left.left)){
            node=rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    public void deleteMin(){
        if (root==null){
            return;
        }
        //根节点是一个2-节点
        if (!isRed(root.left)&&!isRed(root.right)){
            root.color=RED;
        }
        root=deleteMinRecursive(root);
        if (!isEmpty()){
            root.color=BLACK;
        }
    }

    private RedBlackTreeNode<Key,Value> deleteMinRecursive(RedBlackTreeNode<Key,Value> node){
        if (node.left==null){
            //插入操作保证所有节点都是3-节点，或4-节点，
            // 所以直接返回一个null给node.left赋值即可（直接删除）
            return null;
        }
        //如果当前节点的左子节点是一个2-节点
        if(!isRed(node.left)&&!isRed(node.left.left)){
            node=moveRedLeft(node);
        }
        node.left=deleteMinRecursive(node.left);
        //重新调整，令其满足红黑树条件
        return balance(node);
    }

    /**
     * @param node 将node右子节点移入左子节点，使其左子节点变为3-节点
     * @return
     */
    private RedBlackTreeNode<Key,Value> moveRedLeft(RedBlackTreeNode<Key,Value> node){
        //先将node,node.left,node.right组合成一个4-节点
        flipColors(node);
        //节点的右子节点不是2-节点，则从右子节点移入左子节点
        if (isRed(node.right.left)){
            //右旋转可以将较小值上移
            node.right=rotateRight(node.right);
            //左旋转可以将较大值上移
            node=rotateLeft(node);
            //经过上述两步，可以将node的右子节点中较小值上移到根节点，原来的根节点变成了左子节点，
            //从而node.left,node至少组成了一个3-节点
            flipColors(node);
        }
        return node;
    }

    private RedBlackTreeNode<Key,Value> balance(RedBlackTreeNode<Key,Value> node){
        //如果红链接在右边，左旋转
        if (isRed(node.right)){
            node=rotateLeft(node);
        }
        //如果有两条连续红链接，则上层先右旋转
        if(isRed(node.left)&&isRed(node.left.left)){
            node= rotateRight(node);
        }
        //如果左右子节点都是红色，则需要变换颜色
        if (isRed(node.left)&&isRed(node.right)){
            flipColors(node);
        }
        node.nodeCount=size(node.left)+size(node.right)+1;
        return node;
    }

    public void put(Key key,Value value){
        if (key==null){
            throw new IllegalArgumentException();
        }
        root=putRecursive(root,key,value);
        //根节点的颜色必须是黑色
        root.color=BLACK;
    }

    private RedBlackTreeNode<Key,Value> putRecursive(RedBlackTreeNode<Key,Value> node,Key key,Value value){
        if (node==null){
            return new RedBlackTreeNode<>(key,value,RED,1);
        }
        //插入的常规操作
        int compareRes= key.compareTo(node.key);
        if (compareRes==0){
            node.value=value;
        }else if(compareRes>0){
            node.right=putRecursive(node.right,key,value);
        }else{
            node.left=putRecursive(node.left,key,value);
        }
        //如果右子节点是红色，左子节点是黑色，则左旋转
        if(isRed(node.right)&&!isRed(node.left)){
            node=rotateLeft(node);
        }
        //如果有两条连续红链接，则上层先右旋转
        if(isRed(node.left)&&isRed(node.left.left)){
            node= rotateRight(node);
        }
        //如果左右子节点都是红色，则需要变换颜色
        if (isRed(node.left)&&isRed(node.right)){
            flipColors(node);
        }
        node.nodeCount=size(node.left)+size(node.right)+1;
        return node;
    }

    /**
     * @param node 变换颜色，传入节点的左右子节点都为红色
     */
    private void flipColors(RedBlackTreeNode<Key,Value> node){
        node.color=!node.color;
        node.left.color= !node.left.color;
        node.right.color=!node.right.color;
    }
    private boolean isRed(RedBlackTreeNode<Key,Value> node){
        return node!=null?node.color:BLACK;
    }
    /**
     * 右旋转：将在左边的红链接旋转到右边
     * @param fatherNode
     * @return
     */
    private RedBlackTreeNode<Key,Value> rotateRight(RedBlackTreeNode<Key,Value> fatherNode){
        RedBlackTreeNode<Key,Value> temp=fatherNode.left;
        fatherNode.left=temp.right;
        temp.right=fatherNode;
        temp.color=fatherNode.color;
        fatherNode.color=RED;
        temp.nodeCount=fatherNode.nodeCount;
        fatherNode.nodeCount=size(fatherNode.left)+size(fatherNode.right)+1;
        return temp;
    }

    /**
     * 左旋转：将右边的红链接旋转到左边
     * @param fatherNode
     * @return
     */
    private RedBlackTreeNode<Key,Value> rotateLeft(RedBlackTreeNode<Key,Value> fatherNode){
        RedBlackTreeNode<Key,Value> temp=fatherNode.right;
        fatherNode.right=temp.left;
        temp.left=fatherNode;
        temp.color=fatherNode.color;
        fatherNode.color=RED;
        temp.nodeCount=fatherNode.nodeCount;
        fatherNode.nodeCount=size(fatherNode.left)+size(fatherNode.right)+1;
        return temp;
    }

    private int size(RedBlackTreeNode<Key,Value> node){
        if (node==null){
            return 0;
        }
        return node.nodeCount;
    }

    public boolean isEmpty(){
        return root==null;
    }
}
