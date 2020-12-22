package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks;

import java.util.Random;

/**
 * @author yuzhang
 * @date 2020/12/17 上午11:19
 * TODO
 */
public class SkipList {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        for (int i = 0; i < 10; i++) {
            skipList.insert(i);
        }
        skipList.printAll();
        System.out.println(skipList.find(5));
    }
    /**
     * 最大层数
     */
    private static final int MAX_LEVEL = 16;

    /**
     * 跳表的步长（一级索引每隔2个节点一个节点，二级索引每隔4个节点一个节点）
     */
    private static final float SKIPLIST_P = 0.5f;

    /**
     * 记录当前层数
     */
    private int levelCount = 1;
    /**
     * 头节点
     */
    private Node head = new Node();
    private Random random = new Random();

    public Node find(int value) {
        Node ptr = head;
        // 从最上层开始找
        for (int i = levelCount - 1; i >= 0; i--) {
            // 在当前第i层进行链表的遍历
            while (ptr.next[i] != null && ptr.next[i].data < value) {
                ptr = ptr.next[i];
            }
        }
        // 查找命中
        if (ptr.next[0] != null && ptr.next[0].data == value) {
            return ptr;
        } else {
            return null;
        }
    }

    public void insert(int value){
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node[] update = new Node[level];
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }
        // 在update记录下查找过程中，每一层中小于value的最大值
        Node ptr = head;
        for (int i = level - 1; i >= 0; i--) {
            while (ptr.next[i] != null && ptr.next[i].data < value){
                ptr = ptr.next[i];
            }
            update[i] = ptr;
        }
        // update记录了路径上所有应该插入的位置，在这些位置都执行插入操作
        for (int i = 0; i < level; i++) {
            // 对于每一层都执行插入操作
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
        // 如果跳表的"高度"发生了变化，则更新
        if (levelCount < level){
            levelCount = level;
        }
    }

    public void delete(int value){
        Node[] update = new Node[levelCount];
        Node ptr = head;
        // 查找要删除的节点，并记录遍历路径
        for (int i = levelCount - 1; i >= 0; i--) {
            while (ptr.next[i] != null && ptr.next[i].data < value){
                ptr = ptr.next[i];
            }
            update[i] = ptr;
        }
        // 如果查找命中,则逐层删除
        if (ptr.next[0] != null && ptr.next[0].data == value){
            for (int i = 0; i < levelCount; i++) {
                update[i].next[i] = update[i].next[i].next[i];
            }
        }
    }

    /**
     * 1. 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
     * 2. 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
     * 3. 该randomLevel方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
     *           50%的概率返回 1
     *           25%的概率返回 2
     *           12.5%的概率返回 3 ...
     * @return
     */
    private int randomLevel(){
        int level = 1;
        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL){
            level += 1;
        }
        return level;
    }

    public void printAll() {
        Node p = head;
        while(p.next[0] != null){
            System.out.println(p.next[0] + " ");
            p = p.next[0];
        }
        System.out.println();
    }

    static class Node {
        // 存储的数据
        private int data = -1;
        // next[i] 当前节点在第i层的下一个节点（相邻节点）
        private Node[] next = new Node[MAX_LEVEL];
        // 当前节点从下到上总共有多少层
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");

            return builder.toString();
        }
    }
}
