package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_5_Applications.exercises;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/11/16 上午8:25
 * mybatis的实现方式，之所以采用单独的Map保存键值对，
 * 是为了后续使用装饰者模式在此基础上进行扩展
 */
public class EX_3_5_26_LRU_1<Key extends Comparable<Key>, Value> {
    /**
     * 存储缓存key和缓存value
     */
    private Map<Key, Value> cache;
    /**
     * 用于存储缓存键，以及维护缓存的顺序
     */
    private Map<Key, Key> keyMap;
    /**
     * 链表最后一个键
     */
    private Key eldestKey;

    public EX_3_5_26_LRU_1(Map<Key, Value> cache) {
        this.cache = cache;
        setSize(1024);
    }

    private void setSize(int size) {
        keyMap = new LinkedHashMap<Key, Key>(size, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Key, Key> eldest) {
                boolean tooBig = size() > size;
                if (tooBig) {
                    eldestKey = eldest.getKey();
                }
                return tooBig;
            }
        };
    }

    public void putObject(Key key, Value value) {
        cache.put(key, value);
        // 维护 keyMap中键的相对顺序
        cycleKeyList(key);
    }

    private void cycleKeyList(Key key) {
        /**
         * 如果accessOrder=true，LinkedHashMap会维护一个访问顺序，
         * 在插入时，LinkedHashMap内部的tail指针会指向最新插入的元素；
         */
        keyMap.put(key, key);
        /**
         * 在插入过程中，回调用removeEldestEntry(),以判断当前map是否已满
         */
        if (eldestKey != null) {
            cache.remove(eldestKey);
            eldestKey = null;
        }
    }

    public Value getObject(Key key) {
        /**
         * 在LinkedHashMap内部，get操作获取到key之后调用afterNodeAccess()
         * 这个方法会将刚获取到的key放到链表尾部，用tail指向他
         */
        keyMap.get(key);
        return cache.get(key);
    }
}
