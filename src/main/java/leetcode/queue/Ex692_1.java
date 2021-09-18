package leetcode.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex692_1 {
    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(new Ex692_1().topKFrequent(words, 3));
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> countMap = new HashMap<>();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            int count1 = countMap.getOrDefault(o1, 0);
            int count2 = countMap.getOrDefault(o2, 0);
            if (count1 == count2) {
                return o2.compareTo(o1);
            }
            return count1 - count2;
        });
        for (String word : words) {
            int count = countMap.getOrDefault(word, 0);
            countMap.put(word, count + 1);
        }
        for (String word : countMap.keySet()) {
            priorityQueue.offer(word);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        List<String> res = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            res.add(priorityQueue.poll());
        }
        Collections.reverse(res);
        return res;
    }
}
