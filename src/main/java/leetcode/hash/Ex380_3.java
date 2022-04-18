package leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author yu zhang
 */
public class Ex380_3 {
    private Map<Integer, Integer> map;

    private List<Integer> list;

    private Random random;

    public Ex380_3() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int idx = map.remove(val);
        int last = list.get(list.size() - 1);
        if (last != val) {
            list.set(idx, last);
            map.put(last, idx);
        }
        list.remove(list.size() - 1);
        return true;
    }

    public int getRandom() {
        int idx = random.nextInt(list.size());
        return list.get(idx);
    }
}
