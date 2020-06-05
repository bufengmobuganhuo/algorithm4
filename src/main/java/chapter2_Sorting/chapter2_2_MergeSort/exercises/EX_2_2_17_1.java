package chapter2_Sorting.chapter2_2_MergeSort.exercises;

/**
 * @author zhangyu
 * 2020/5/21 15:24
 * 练习2.2.17：链表的自然排序
 */
public class EX_2_2_17_1 {
    public static void main(String[] args) {
        EX_2_2_17_1 ex_2_2_17_1=new EX_2_2_17_1();
        Node head=ex_2_2_17_1.getNode(1);
        head.next=ex_2_2_17_1.getNode(0);
        head.next.next=ex_2_2_17_1.getNode(2);
        head.next.next.next=ex_2_2_17_1.getNode(3);
        head.next.next.next.next=ex_2_2_17_1.getNode(6);
        head.next.next.next.next.next=ex_2_2_17_1.getNode(4);
        head.next.next.next.next.next.next=ex_2_2_17_1.getNode(5);
        head= ex_2_2_17_1.mergeSort(head);
        while (head!=null){
            System.out.println(head.item);
            head=head.next;
        }
    }


    public Node mergeSort(Node head){
        if (head==null||head.next==null){
            return head;
        }
        Node mid=getMid(head);
        Node second=mid.next;
        //将链表截取成两段
        mid.next=null;
        Node left= mergeSort(head);
        Node right=mergeSort(second);
        return inPlaceMerge(left,right);
    }

    private Node getMid(Node head){
        Node lowSpeed=head;
        Node hiSpeed=head.next;
        while (hiSpeed!=null&&hiSpeed.next!=null){
            lowSpeed=lowSpeed.next;
            hiSpeed=hiSpeed.next.next;
        }
        return lowSpeed;
    }

    private Node inPlaceMerge(Node node1,Node node2){
        Node res=new Node(-1);
        Node cur=res;
        while (node1!=null||node2!=null){
            if (node1==null){
                cur.next=node2;
                node2=node2.next;
            }else if(node2==null){
                cur.next=node1;
                node1=node1.next;
            }else if(node1.item<node2.item){
                cur.next=node1;
                node1=node1.next;
            }else{
                cur.next=node2;
                node2=node2.next;
            }
            cur=cur.next;
        }
        return res.next;
    }

    public Node getNode(Integer item){
        return new Node(item);
    }


    private class Node{
        Integer item;
        Node next;

        public Node(Integer item) {
            this.item = item;
        }
    }
}
