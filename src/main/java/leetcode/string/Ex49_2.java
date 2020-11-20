package leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/11/6 8:49 上午
 * TODO
 */
public class Ex49_2 {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Ex49_2 ex49_2 = new Ex49_2();
        ex49_2.groupAnagrams(strs);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chrs = str.toCharArray();
            quickSort(chrs, 0, chrs.length - 1);
            List<String> list = map.getOrDefault(String.valueOf(chrs), new ArrayList<>());
            list.add(str);
            map.put(String.valueOf(chrs),list);
        }
        List<List<String>> ans = new ArrayList<>(map.size());
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }
        return ans;
    }

    private void quickSort(char[] chrs, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int partitionIdx = partition(chrs, lo, hi);
        quickSort(chrs, lo, partitionIdx - 1);
        quickSort(chrs, partitionIdx + 1, hi);
    }

    private int partition(char[] chrs, int lo, int hi) {
        int leftPtr = lo, rightPtr = hi + 1;
        char partitionEle = chrs[lo];
        while (true) {
            while (chrs[++leftPtr] < partitionEle) {
                if (leftPtr >= hi) {
                    break;
                }
            }
            while (chrs[--rightPtr] > partitionEle) {
                if (rightPtr <= lo) {
                    break;
                }
            }
            if (rightPtr <= leftPtr) {
                break;
            }
            exch(chrs, leftPtr, rightPtr);
        }
        exch(chrs, lo, rightPtr);
        return rightPtr;
    }

    private void exch(char[] chrs, int i, int j) {
        char tmp = chrs[i];
        chrs[i] = chrs[j];
        chrs[j] = tmp;
    }
}
