package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises;

import edu.princeton.cs.algs4.StdIn;

/**
 * 约瑟夫问题
 */
public class EX_1_3_37_Josephus {
    public static void main(String[] args) {
        while (!StdIn.isEmpty()){
            int people= StdIn.readInt();
            int distance=StdIn.readInt();
            long start= System.currentTimeMillis();
            System.out.println(josephusRingList(people,distance));
            long end=System.currentTimeMillis();
            System.out.println("耗时："+(end-start)+"ms");
            start= System.currentTimeMillis();
            System.out.println(josephusArr(people,distance));
            end=System.currentTimeMillis();
            System.out.println("耗时："+(end-start)+"ms");
        }

    }

    public static String josephusArr(int people,int distance){
        StringBuilder res=new StringBuilder(people);
        if (people<distance){
            for (int i=0;i<people;i++){
                res.append(i);
                res.append(" ");
            }
            return res.toString();
        }
        Node[] nodes=new Node[people];
        for (int i=0;i<nodes.length;i++){
            nodes[i]=new Node(i);
        }
        int count=1;
        int index=0;
        int leftPeople=people;
        while (leftPeople>=distance){
            if (count==distance&&!nodes[index].isKilled){
                nodes[index].isKilled=true;
                leftPeople--;
                count=1;
                res.append(index);
                res.append(" ");
            }else if(!nodes[index].isKilled){
                count++;
            }
            index= index+1==nodes.length?0:index+1;
        }
        for (Node node:nodes){
            if (!node.isKilled){
                res.append(node.item);
                res.append(" ");
            }
        }
        return res.toString();
    }

    /**
     * @param people 使用环形链表实现
     * @param distance
     * @return
     */
    public static String josephusRingList(int people, int distance){
        StringBuilder res=new StringBuilder(people);
        if (people<distance){
            for (int i=0;i<people;i++){
                res.append(i);
                res.append(" ");
            }
            return res.toString();
        }
        Node current=createRingList(people);
        int index=1;
        int size=people;
        while (size>=distance){
            //如果数到了distance，并且还未被杀
            if (index==distance&&!current.isKilled){
                current.isKilled=true;
                size--;
                index=1;
                res.append(current.item);
                res.append(" ");
            }else if(!current.isKilled){
                index++;
            }
            current=current.next;
        }
        //未被杀掉的人
        while (people>=0){
            res.append(current.isKilled?"":current.item+" ");
            people--;
            current=current.next;
        }
        return res.toString();
    }
    private static Node createRingList(int people){
        RingList ringList=new RingList();
        for (int i=0;i<people;i++){
            ringList.add(i);
        }
        return ringList.head;
    }
    private static class RingList{
        int size;
        Node head;
        Node end;
        public void add(int item){
            Node oldLast=end;
            end=new Node(item);
            end.next=head;
            if (size==0){
                head=end;
            }else{
                oldLast.next = end;
            }
            size++;
        }
    }
    private static class Node{
        int item;
        boolean isKilled;
        Node next;

        public Node(int item) {
            this.item = item;
        }
    }
}
