package chapter2_Sorting.chapter2_5_Applications.exercises;

import utils.ArrayUtil;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/2/7 上午9:29
 * TODO
 */
public class Ex_2_5_2_2 {
    public static void main(String[] args) {
        String[] words = ArrayUtil.createCompoundStrs(8);
        System.out.println(solution2(words));
    }

    public static List<String> solution2(String[] words) {
        shellSort(words);
        int len = words.length;
        int minCompoundLen = words[0].length() * 2;
        int minCompoundIdx = 0;
        List<String> res = new ArrayList<>();
        while (minCompoundIdx < len && words[minCompoundIdx].length() < minCompoundLen) {
            minCompoundIdx++;
        }
        while (minCompoundIdx < len) {
            int compoundLen = words[minCompoundIdx].length();
            String compoundWord = words[minCompoundIdx];
            for (int i = 0; i < minCompoundIdx; i++) {
                int another = binarySearch(words, compoundLen - words[i].length(), i + 1, minCompoundIdx - 1);
                if (another==-1){
                    continue;
                }
                while (words[another].length() + words[i].length() == compoundLen) {
                    if (compoundWord.equals(words[another] + words[i])) {
                        res.add(compoundWord);
                    } else if (compoundWord.equals(words[i] + words[another])) {
                        res.add(compoundWord);
                    }
                    another++;
                }
            }
            minCompoundIdx++;
        }
        return res;
    }

    private static int binarySearch(String[] words, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (words[mid].length() == target) {
                while (mid >= start && words[mid].length() == target) {
                    mid--;
                }
                return mid + 1;
            } else if (words[mid].length() < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    private static void shellSort(String[] words) {
        int h = 1;
        while (h < words.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < words.length; i++) {
                for (int j = i; j >= h && words[j].length() < words[j - h].length(); j -= h) {
                    String tmp = words[j];
                    words[j] = words[j - h];
                    words[j - h] = tmp;
                }
            }
            h /= 3;
        }
    }

    /**
     * 复杂度高
     */
    public static List<String> solution1(String[] words) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        TreeSet<String> wordSet = new TreeSet<>(Arrays.asList(words));
        String first = wordSet.first();
        while (first != null) {
            String second = wordSet.higher(first);
            if (second == null) {
                break;
            }
            if (second.startsWith(first)) {
                String third = second.substring(first.length());
                if (wordSet.contains(third)) {
                    res.add(second);
                }
            }
            first = second;
        }
        return res;
    }
}
