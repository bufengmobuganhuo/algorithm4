package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_4_HashTables;

import java.lang.reflect.Array;

/**
 * @author yuzhang
 * @date 2020/11/13 8:46 上午
 * TODO
 */
public class LinearProbingHashST2<Key extends Comparable<Key>, Value> {
    private int size;
    private Key[] keys;
    private Value[] values;
    private final int hashTableSize;

    public LinearProbingHashST2(int hashTableSize) {
        this.hashTableSize = hashTableSize;
        keys = (Key[]) Array.newInstance(Object.class, 16);
        values = (Value[]) Array.newInstance(Object.class, 16);
    }

    public void put(Key key, Value value) {
        if (key == null) {
            return;
        }
        if (size >= hashTableSize / 2) {
            resize(hashTableSize * 2);
        }
        int idx = getHashCode(key);
        while (keys[idx] != null) {
            if (keys[idx].compareTo(key) == 0) {
                values[idx] = value;
                return;
            }
            idx = (idx + 1) % hashTableSize;
        }
        keys[idx] = key;
        values[idx] = value;
        size++;
    }

    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        int idx = getHashCode(key);
        while (keys[idx] != null) {
            if (keys[idx].compareTo(key) == 0) {
                return values[idx];
            }
            idx = (idx + 1) % hashTableSize;
        }
        return null;
    }

    public void lazyDelete(Key key) {
        if (key == null) {
        } else if (containsKey(key)) {
            int idx = getHashCode(key);
            while (keys[idx] != null) {
                if (keys[idx].compareTo(key) == 0) {
                    values[idx] = null;
                    size--;
                    return;
                }
            }

        }
    }

    public void delete(Key key) {
        if (key == null) {
            return;
        } else if (containsKey(key)) {
            int idx = getHashCode(key);
            while (keys[idx].compareTo(key) != 0) {
                idx = (idx + 1) % hashTableSize;
            }
            keys[idx] = null;
            values[idx] = null;
            size--;
            idx = (idx + 1) % hashTableSize;
            while (keys[idx] != null) {
                Key keyToMove = keys[idx];
                Value valueToMove = values[idx];
                size--;
                keys[idx] = null;
                values[idx] = null;
                put(keyToMove, valueToMove);
                idx = (idx + 1) % hashTableSize;
            }
            if (size < hashTableSize / 8) {
                resize(hashTableSize / 2);
            }
        }
    }

    public boolean containsKey(Key key) {
        return get(key) != null;
    }

    private void resize(int cap) {
        LinearProbingHashST2<Key, Value> linearProbingHashST2 = new LinearProbingHashST2<>(cap);
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null && values[i] != null) {
                linearProbingHashST2.put(keys[i], values[i]);
            }
        }
        this.keys = linearProbingHashST2.keys;
        this.values = linearProbingHashST2.values;
    }

    private int getHashCode(Key key) {
        return (key.hashCode() & 0x7fffff) % hashTableSize;
    }
}
