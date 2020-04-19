package chapter3_Searching.chapter3_5_Applications.exercises;

import com.sun.xml.internal.ws.wsdl.parser.MemberSubmissionAddressingWSDLParserExtension;
import edu.princeton.cs.algs4.In;

import javax.xml.bind.ValidationEvent;
import java.util.HashMap;

/**
 * @author zhangyu
 * 2020/4/17 14:32
 * 练习3.5.26：“最近最少使用”缓存
 *  1.使用map存储缓存键以及缓存在链表中的“位置”（这个位置并不太准确），双向链表存储缓存的键值对
 *  2.插入缓存时，插入到双向链表的表头，并将缓存键保存到map
 *  3.访问时，将双向链表中命中的缓存节点移动到表头，这样经常被访问的节点会向链表表头方向移动
 *  4.最不常访问的节点就是双向链表的尾节点
 */
public class EX_3_5_26_LRU<Key extends Comparable<Key>,Value> {
    public static void main(String[] args) {
        EX_3_5_26_LRU<Integer,Integer> ex_3_5_26_lru=new EX_3_5_26_LRU<>();
        ex_3_5_26_lru.insert(1,1);
        ex_3_5_26_lru.insert(2,2);
        ex_3_5_26_lru.insert(3,3);
        ex_3_5_26_lru.insert(4,4);
        ex_3_5_26_lru.insert(2,1);
        System.out.println(ex_3_5_26_lru.visit(2));
        System.out.println(ex_3_5_26_lru.visit(3));
        System.out.println(ex_3_5_26_lru.visit(4));
        System.out.println(ex_3_5_26_lru.visit(2));
        System.out.println(ex_3_5_26_lru.visit(2));
        ex_3_5_26_lru.delete();
    }
    private DeLinkedListNode head;
    private DeLinkedListNode end;
    private HashMap<Key,Integer> map;
    private int size;

    public EX_3_5_26_LRU() {
        map=new HashMap<>();
    }

    /**
     * @param key 访问节点
     * @return
     */
    public Value visit(Key key){
        //如果被访问节点已经存在，并且不是链表的头结点，
        //就需要先删除被访问节点，然后重新放入
        if (map.containsKey(key)&& head.key.compareTo(key)!=0){
            Value value= remove(key,map.get(key));
            put(key,value);
            map.put(key,size);
            return value;
            //如果包含被访问节点,并且是头结点，则直接返回
        }else if(map.containsKey(key)){
            return head.value;
        }
        return null;
    }

    /**
     * @param key 插入缓存
     * @param value
     */
    public void insert(Key key,Value value){
        //如果已经包含该键，则将该键移动到链表头
        if (map.containsKey(key)){
            remove(key,map.get(key));
            put(key,value);
            map.put(key,size);
        }else{
            put(key,value);
            map.put(key,size);
        }
    }

    /**
     * 删除最不被经常访问的节点
     */
    public void delete(){
        if (end==null){
            return;
        }
        //在访问过程中，被访问节点都被放到了表头
        //故最不常被访问的节点就是表尾节点
        map.remove(end.key);
        removeAtEnd();
    }

    private void put(Key key,Value value){
        if(head ==null){
            head =new DeLinkedListNode(key,value);
            end = head;
            size++;
            return;
        }
        DeLinkedListNode newNode=new DeLinkedListNode(key,value);
        head.last=newNode;
        newNode.next= head;
        head =newNode;
        size++;
    }

    private Value remove(Key key, int index){
        if (isEmpty()){
            return null;
        }
        int mid= size/2;
        DeLinkedListNode cur=null;
        if (index<mid){
            cur= end;
            //先找到这个键
            while (key.compareTo(cur.key)!=0){
                cur=cur.last;
            }
        }else{
            cur= head;
            while (key.compareTo(cur.key)!=0){
                cur=cur.next;
            }
        }
        Value value=cur.value;
        if (cur.next==null){
            removeAtHead();
        }else if(cur.last==null){
            removeAtEnd();
        }else{
            size--;
            cur.last.next=cur.next;
            cur.next.last=cur.last;
        }
        return value;
    }

    private void removeAtHead(){
        if (isEmpty()){
            return;
        }
        if (size==0){
            head=null;
            end=null;
            size--;
            return;
        }
        size--;
        head=head.next;
        head.last=null;
    }

    private void removeAtEnd(){
        if (isEmpty()){
            return;
        }
        if (size==1){
            head=null;
            end=null;
            size--;
            return;
        }
        size--;
        end=end.last;
        end.next=null;
    }

    public boolean isEmpty(){
        return head ==null;
    }

    class DeLinkedListNode{
        Key key;
        Value value;
        DeLinkedListNode last;
        DeLinkedListNode next;

        public DeLinkedListNode(Key key,Value value) {
            this.key = key;
            this.value= value;
        }
    }
}
