package chapter3_Searching.chapter3_5_Applications.exercises;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/11/16 上午9:32
 * 使用LinkedHashMap实现
 */
public class EX_3_5_26_LRU_2<Key extends Comparable<Key>,Value> extends LinkedHashMap<Key,Value> {
    private final int MAX_SIZE;

    public EX_3_5_26_LRU_2(int initialCapacity) {
        super(initialCapacity,0.75F,true);
        this.MAX_SIZE = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Key, Value> eldest) {
        return size() > MAX_SIZE;
    }
}
