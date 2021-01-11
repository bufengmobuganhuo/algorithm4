package leetcode.hash;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/1/13 下午5:16
 * TODO
 */
public class Ex380 {


    class RandomizedSet {
        private List<Integer> keys;
        /**
         * (key,value):插入数值在keys中对应的索引
         */
        private Map<Integer, Integer> map;

        private Random random;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            keys = new ArrayList<>();
            map = new HashMap<>();
            random = new Random();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            keys.add(val);
            map.put(val, keys.size() - 1);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int delIdx = map.get(val);
            // 在keys中，将最后一个值与被删除元素交换位置
            int lastEle = keys.get(keys.size() - 1);
            keys.set(delIdx, lastEle);
            keys.remove(keys.size() - 1);
            // 更新keys中最后一个值在map中的信息
            map.put(lastEle, delIdx);
            map.remove(val);
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            int idx = (int) (random.nextDouble() * keys.size());
            return keys.get(idx);
        }
    }
}
