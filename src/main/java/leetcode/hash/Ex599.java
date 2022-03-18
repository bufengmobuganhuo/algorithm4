package leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex599 {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            idxMap.put(list1[i], i);
        }
        int minIdx = Integer.MAX_VALUE;
        List<String> ans = new ArrayList<>();
        for (int j = 0; j < list2.length; j++) {
            if (!idxMap.containsKey(list2[j])) {
                continue;
            }
            int i = idxMap.get(list2[j]);
            if (i + j < minIdx) {
                ans.clear();
                ans.add(list2[j]);
                minIdx = i + j;
            } else if (i + j == minIdx) {
                ans.add(list2[j]);
            }
        }
        String[] res = new String[ans.size()];
        return ans.toArray(res);
    }
}
