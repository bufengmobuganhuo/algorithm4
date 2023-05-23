package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_5_Applications.exercises;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yuzhang
 * @date 2020/11/16 下午4:04
 * 线程安全的LRU
 */
public class EX_3_5_26_LRU_3<Key extends Comparable<Key>, Value> {
    private final int MAX_SIZE;
    /**
     * 保存缓存键->缓存值
     */
    private ConcurrentHashMap<Key,Value> cache;
    /**
     * 维护缓存键的相对顺序，队首 -> 队尾：老缓存 -> 旧缓存
     */
    private ConcurrentLinkedDeque<Key> keyMap;
    /**
     * 保证读写安全
     */
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    public EX_3_5_26_LRU_3(int max_size) {
        MAX_SIZE = max_size;
        cache = new ConcurrentHashMap<>();
        keyMap = new ConcurrentLinkedDeque<>();
    }

    public void putObject(Key key,Value value){
        // 加写锁
        writeLock.lock();
        try{
            if (cache.containsKey(key)){
                cache.put(key,value);
                // 新添加的放到链表最后
                moveToTail(key);
                return;
            }
            // 如果超过最大长度，则删去最老的缓存
            if (cache.size()>=MAX_SIZE){
                removeOldestKey();
            }
            cache.put(key,value);
            keyMap.offerLast(key);
        }finally {
            writeLock.unlock();
        }
    }

    public Value getObject(Key key){
        // 加写锁
        readLock.lock();
        try{
            // 不存在则直接返回
            if (!cache.containsKey(key)){
                return null;
            }
            moveToTail(key);
            return cache.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public Value remove(Key key){
        writeLock.lock();
        try{
            if (cache.containsKey(key)){
                keyMap.remove(key);
                return cache.remove(key);
            }
            return null;
        }finally {
            writeLock.unlock();
        }
    }

    private void moveToTail(Key key){
        keyMap.remove(key);
        keyMap.offerLast(key);
    }

    private void removeOldestKey(){
        Key oldestKey = keyMap.pollFirst();
        if (oldestKey!=null){
            cache.remove(oldestKey);
        }
    }
}
