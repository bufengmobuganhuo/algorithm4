package leetcode.queue;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/1/18 上午9:45
 * TODO
 */
public class Ex692 {
    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        Ex692 ex692 = new Ex692();
        System.out.println(Arrays.toString(ex692.topKFrequent2(words, 2).toArray()));
    }

    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQue priorityQue = new PriorityQue(k, (o1, o2) -> {
            int count1 = count.get(o1);
            int count2 = count.get(o2);
            if (count1 != count2) {
                return count1 - count2;
            }
            return o1.compareTo(o2);
        });
        for (String word : count.keySet()) {
            priorityQue.insert(word);
            if (priorityQue.size > k) {
                priorityQue.delMin();
            }
        }
        List<String> res = new ArrayList<>();
        while (priorityQue.size > 0) {
            res.add(priorityQue.delMin());
        }
        Collections.reverse(res);
        return res;
    }

    static class PriorityQue {
        private int size;
        private String[] que;
        private Comparator<String> comparator;

        public PriorityQue(int cap, Comparator<String> comparator) {
            que = new String[cap + 2];
            this.comparator = comparator;
        }

        public void insert(String item) {
            que[++size] = item;
            swim(size);
        }

        public String delMin() {
            String res = que[1];
            exch(1, size--);
            que[size + 1] = null;
            sink(1);
            return res;
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j + 1 <= size && less(j + 1, j)) {
                    j++;
                }
                if (less(k, j)) {
                    break;
                }
                exch(k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && less(k, k / 2)) {
                exch(k / 2, k);
                k /= 2;
            }
        }

        private boolean less(int i, int j) {
            return comparator.compare(que[i], que[j]) < 0;
        }

        private void exch(int i, int j) {
            String tmp = que[i];
            que[i] = que[j];
            que[j] = tmp;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        Map<String, Integer> map = new HashMap<>();
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
        for (String word : words) {
            int count = map.getOrDefault(word, 0);
            map.put(word, count + 1);
            if (count != 0) {
                priorityQueue.remove(new Pair(word, count));
            }
            priorityQueue.add(new Pair(word, count + 1));
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(priorityQueue.poll().word);
        }
        return res;
    }

    static class Pair implements Comparable<Pair> {
        String word;
        int count;

        public Pair(String word, int count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pair) {
                return ((Pair) obj).count == this.count && ((Pair) obj).word.equals(this.word);
            }
            return false;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.count != (o.count)) {
                return o.count - this.count;
            }
            return o.word.compareTo(this.word);
        }
    }
}
