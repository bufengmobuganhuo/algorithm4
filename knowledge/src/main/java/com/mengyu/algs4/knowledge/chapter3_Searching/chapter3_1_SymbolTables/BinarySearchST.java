package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_1_SymbolTables;

import java.util.Iterator;

/**
 * @author zhangyu
 * 2020/3/13 11:13
 * 有序数组中的二分查找
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> implements SymbolTableTemplate<Key, Value> {
    public static void main(String[] args) {
        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<>();
        binarySearchST.put("a", 1);
        binarySearchST.put("b", 2);
        binarySearchST.put("d", 4);
        binarySearchST.put("c", 3);
        System.out.println(binarySearchST.floor("a"));
        System.out.println(binarySearchST.get("c"));
        binarySearchST.delete("c");
        System.out.println(binarySearchST.get("c"));
        System.out.println(binarySearchST.size());
        Iterator<String> iterator = binarySearchST.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private Key[] keys;
    private Value[] values;
    private int size;

    public BinarySearchST() {
        keys = (Key[]) new Comparable[16];
        values = (Value[]) new Object[16];
    }

    @Override
    public Key ceiling(Key key) {
        int index = rank(key);
        return index < size ? keys[index] : null;
    }

    @Override
    public Key floor(Key key) {
        int index = rank(key);
        if (index < size && keys[index].compareTo(key) == 0) {
            return keys[index];
        } else if (index > 0) {
            return keys[index - 1];
        }
        return null;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty(size)) {
            return null;
        }
        int index = rank(key);
        if (index < size && keys[index].compareTo(key) == 0) {
            return values[index];
        }
        return null;
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        int index = rank(key);
        //如果找到该键，则修改值
        if (index < size && keys[index].compareTo(key) == 0) {
            values[index] = value;
            return;
        }
        for (int i = size; i > index; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[index] = key;
        values[index] = value;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Key> keys() {
        return null;
    }

    @Override
    public void delete(Key key) {
        int index = rank(key);
        if (index < size && keys[index].compareTo(key) == 0) {
            for (int i = index; i < size - 1; i++) {
                keys[i] = keys[i + 1];
                values[i] = values[i + 1];
            }
            size--;
        }
    }

    @Override
    public int rank(Key key) {
        int lo = 0, hi = size - 1;
        int mid = 0;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (keys[mid].compareTo(key) == 0) {
                return mid;
            } else if (keys[mid].compareTo(key) > 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        //如果查找不到，则返回比这个键大的元素的索引
        return lo;
    }

    @Override
    public Iterator<Key> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Key> {
        private int size = 0;

        @Override
        public boolean hasNext() {
            return size < size();
        }

        @Override
        public Key next() {
            return keys[size++];
        }
    }
}
