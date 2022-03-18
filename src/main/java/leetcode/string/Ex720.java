package leetcode.string;

import chapter5_Strings.chapter5_2_Tries.TST;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex720 {
    public static void main(String[] args) {
        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        System.out.println(new Ex720().longestWord2(words));
    }
    public String longestWord(String[] words) {
        Arrays.sort(words, (o1, o2) -> {
            if (o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }
            return o2.compareTo(o1);
        });
        Set<String> set = new HashSet<>();
        set.add("");
        String longestWord = "";
        // 长度递增，相同长度的字典序递减
        for (String word : words) {
            // 如果set中有加一个字符就可以组成word的
            if (set.contains(word.substring(0, word.length() - 1))) {
                longestWord = word;
                set.add(word);
            }
        }
        return longestWord;
    }

    private TstNode root;

    public String longestWord2(String[] words) {
        for (String word : words) {
            put(word);
        }
        String longestWord = "";
        for (String word : words) {
            if (!contains(word)) {
                continue;
            }
            if (longestWord.length() < word.length() || (longestWord.length() == word.length() && longestWord.compareTo(word) > 0)) {
                longestWord = word;
            }
        }
        return longestWord;
    }

    private boolean contains(String word) {
        return get(root, word, 0) != null;
    }

    private TstNode get(TstNode node, String word, int idx) {
        if (node == null) {
            return null;
        }
        char chr = word.charAt(idx);
        if (chr < node.chr) {
            return get(node.left, word, idx);
        } else if (chr > node.chr) {
            return get(node.right, word, idx);
            // 不仅要相等，而且上一个也得是在字典里出现过才行
        } else if (idx < word.length() - 1 && node.isEnd) {
            return get(node.mid, word, idx + 1);
        } else if (node.isEnd) {
            return node;
        }
        return null;
    }


    private void put(String word) {
        root = put(root, word, 0);
    }

    private TstNode put(TstNode node, String word, int idx) {
        char chr = word.charAt(idx);
        if (node == null) {
            node = new TstNode(chr);
        }
        if (chr < node.chr) {
            node.left = put(node.left, word, idx);
        } else if (chr > node.chr) {
            node.right = put(node.right, word, idx);
        } else if (idx < word.length() - 1) {
            node.mid = put(node.mid, word, idx + 1);
        } else {
            node.isEnd = true;
        }
        return node;
    }

    private static class TstNode {
        private boolean isEnd;
        private char chr;
        private TstNode left, mid, right;

        public TstNode(char chr) {
            this.chr = chr;
        }
    }
}
