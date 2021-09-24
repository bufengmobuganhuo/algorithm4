package leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex380_2 {
    private List<Integer> list;

    private Map<Integer, Integer> idxMap;

    private Random random;

    /** Initialize your data structure here. */
    public Ex380_2() {
        list = new ArrayList<>();
        idxMap = new HashMap<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (idxMap.containsKey(val)) {
            return false;
        }
        list.add(val);
        idxMap.put(val, list.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!idxMap.containsKey(val)) {
            return false;
        }
        int delIdx = idxMap.get(val);
        // 在keys中，将最后一个值与被删除元素交换位置
        int lastEle = list.get(list.size() - 1);
        list.set(delIdx, lastEle);
        list.remove(list.size() - 1);
        // 更新keys中最后一个值在map中的信息
        idxMap.put(lastEle, delIdx);
        idxMap.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int bound = list.size();
        return list.get(random.nextInt(bound));
    }
}
