package chapter3_Searching.chapter3_5_Applications.exercises;

import com.sun.xml.internal.ws.wsdl.parser.MemberSubmissionAddressingWSDLParserExtension;
import edu.princeton.cs.algs4.In;

import javax.xml.bind.ValidationEvent;
import java.util.HashMap;

/**
 * @author zhangyu
 * 2020/4/17 14:32
 * 练习3.5.26：“最近最少使用”缓存
 * 1.使用链表+HashMap实现
 */
public class EX_3_5_26_LRU<Key extends Comparable<Key>, Value> {
    public static void main(String[] args) {
        EX_3_5_26_LRU<Integer, Integer> ex_3_5_26_lru = new EX_3_5_26_LRU<>(3);
        ex_3_5_26_lru.put(1, 1);
        ex_3_5_26_lru.put(2, 2);
        ex_3_5_26_lru.put(3, 3);
        ex_3_5_26_lru.put(4, 4);
        ex_3_5_26_lru.put(2, 1);
        System.out.println(ex_3_5_26_lru.get(2));
        System.out.println(ex_3_5_26_lru.get(3));
        System.out.println(ex_3_5_26_lru.get(4));
        System.out.println(ex_3_5_26_lru.get(2));
        System.out.println(ex_3_5_26_lru.get(2));
        ex_3_5_26_lru.remove(4);
    }

    private final int MAX_SIZE;
    private Entry<Key, Value> first;
    private Entry<Key, Value> tail;
    private HashMap<Key, Entry<Key, Value>> cache;

    public EX_3_5_26_LRU(int MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;
        cache = new HashMap<>();
    }

    public void put(Key key, Value val) {
        Entry<Key, Value> entry = cache.get(key);
        if (entry == null) {
            if (cache.size() > MAX_SIZE) {
                cache.remove(tail.key);
                removeLast();
            }
            entry = new Entry<>();
            entry.key = key;
        }
        entry.val = val;
        moveToFirst(entry);
        cache.put(key, entry);
    }

    public Value get(Key key) {
        Entry<Key, Value> entry = cache.get(key);
        if (entry == null) {
            return null;
        }
        moveToFirst(entry);
        return entry.val;
    }

    public void remove(Key key) {
        Entry<Key, Value> entry = cache.get(key);
        if (entry == null) {
            return;
        }
        if (entry.next != null) {
            entry.next.pre = entry.pre;
        }
        if (entry.pre != null) {
            entry.pre.next = entry.next;
        }
        // 调整首尾指针的位置
        if (entry == first) {
            first = entry.next;
        }
        if (entry == tail) {
            tail = entry.pre;
        }
        cache.remove(key);
    }

    private void removeLast() {
        if (tail != null) {
            tail = tail.pre;
            // 如果删除后链表为空，则需要更新first的指向
            if (tail == null) {
                first = null;
            } else {
                tail.next = null;
            }
        }
    }

    private void moveToFirst(Entry<Key, Value> entry) {
        if (entry == first) {
            return;
        }
        // 先删除
        if (entry.pre != null) {
            entry.pre.next = entry.next;
        }
        if (entry.next != null) {
            entry.next.pre = entry.pre;
        }
        // 如果是最后一个节点，则需要调整tail指针
        if (entry == tail) {
            tail = tail.pre;
        }
        // 如果在原有位置删除后链表为空，则直接赋值
        if (tail == null || first == null) {
            first = tail = entry;
            return;
        }
        entry.next = first;
        first.pre = entry;
        first = entry;
        entry.pre = null;
    }

    class Entry<Key, Value> {
        Entry<Key, Value> pre;
        Entry<Key, Value> next;
        Key key;
        Value val;
    }
}
