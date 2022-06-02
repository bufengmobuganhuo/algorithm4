package offer.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex114 {
    public static void main(String[] args) {
        String[] words = {"za", "za"};
        System.out.println(new Ex114().alienOrder(words));
    }

    private boolean invalid;

    private boolean[] marked;

    private boolean[] onStack;

    private Stack<Character> reversePostOrder;

    public String alienOrder(String[] words) {
        Set<Character>[] adj = adj(words);
        if (invalid) {
            return "";
        }
        checkCycle(adj);
        if (invalid) {
            return "";
        }
        StringBuilder ans = new StringBuilder();
        while (!reversePostOrder.isEmpty()) {
            ans.append(reversePostOrder.pop());
        }
        return ans.toString();
    }

    private void checkCycle(Set<Character>[] adj) {
        marked = new boolean[26];
        onStack = new boolean[26];
        reversePostOrder = new Stack<>();
        for (int i = 25; i >= 0; i--) {
            if (!marked[i] && adj[i] != null) {
                dfs(adj, (char) ('a' + i));
            }
        }
    }

    private void dfs(Set<Character>[] adj, char vertex) {
        int idx = vertex - 'a';
        marked[idx] = true;
        onStack[idx] = true;
        for (char adjVertex : adj[idx]) {
            if (invalid) {
                return;
            }
            if (!marked[adjVertex - 'a']) {
                dfs(adj, adjVertex);
            } else if (onStack[adjVertex - 'a'] && adjVertex != vertex) {
                invalid = true;
                return;
            }
        }
        reversePostOrder.push(vertex);
        onStack[idx] = false;
    }

    private Set<Character>[] adj(String[] words) {
        Set<Character>[] adj = new Set[26];
        for (String word : words){
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                char c = word.charAt(j);
                if (adj[c - 'a'] == null) {
                    adj[c - 'a'] = new HashSet<>();
                }
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i], word2 = words[i + 1];
            int len1 = word1.length(), len2 = word2.length();
            int len = Math.min(len1, len2);
            int idx = 0;
            while (idx < len) {
                if (word1.charAt(idx) == word2.charAt(idx)) {
                    idx++;
                    continue;
                }
                adj[word1.charAt(idx) - 'a'].add(word2.charAt(idx));
                break;
            }
            if (idx == len && len1 > len2) {
                invalid = true;
            }
        }
        return adj;
    }
}
