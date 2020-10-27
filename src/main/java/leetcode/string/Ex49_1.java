package leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/10/18 3:24 下午
 * TODO
 */
public class Ex49_1 {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Ex49_1 ex49_1 = new Ex49_1();
        ex49_1.groupAnagrams(strs);
    }
    private char[] tmp;

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String word : strs) {
            char[] arr = word.toCharArray();
            tmp = new char[arr.length];
            mergeSort(arr,0,arr.length-1);
            String key = String.valueOf(arr);
            if (!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(word);
        }
        return new ArrayList<>(map.values());
    }

    private void mergeSort(char[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    private void merge(char[] arr, int start, int mid, int end) {
        int i = start, j = mid + 1;
        System.arraycopy(arr, start, tmp, start, end - start + 1);
        for (int k = start; k < end + 1; k++) {
            if (i > mid) {
                arr[k] = tmp[j++];
            } else if (j > end) {
                arr[k] = tmp[i++];
            } else if (tmp[i] > tmp[j]) {
                arr[k] = tmp[j++];
            } else {
                arr[k] = tmp[i++];
            }
        }
    }
}
