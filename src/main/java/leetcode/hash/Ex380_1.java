package leetcode.hash;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/3/18 下午2:14
 * TODO
 */
public class Ex380_1 {
    private Map<Integer, Integer> map;

    private List<Integer> list;

    /**
     * Initialize your data structure here.
     */
    public Ex380_1() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int idx = map.get(val);
        list.set(idx, list.get(list.size() - 1));
        map.put(list.get(idx), idx);
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        Random random = new Random();
        int idx = random.nextInt(list.size());
        return list.get(idx);
    }
}
