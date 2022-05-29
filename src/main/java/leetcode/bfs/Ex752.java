package leetcode.bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex752 {
    public static void main(String[] args) {
        String[] deadends = {"0000"};
        System.out.println(new Ex752().openLock(deadends, "0202"));
    }
    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        Set<String> locked = new HashSet<>();
        Collections.addAll(locked, deadends);
        if (locked.contains("0000")) {
            return -1;
        }
        Queue<String> que = new LinkedList<>();
        int step = 0;
        que.offer("0000");
        while (!que.isEmpty()) {
            int size = que.size();
            step++;
            for (int i = 0; i < size; i++) {
                String cur = que.poll();
                for (String nextNode : getNextNode(cur)) {
                    if (nextNode.equals(target)) {
                        return step;
                    }
                    if (!locked.contains(nextNode)) {
                        que.offer(nextNode);
                        locked.add(nextNode);
                    }
                }
            }
        }
        return -1;
    }

    private List<String> getNextNode(String cur) {
        List<String> nextNodes = new ArrayList<>();
        char[] chrs = cur.toCharArray();
        for (int i = 0; i < 4; i++) {
            char chr = chrs[i];
            chrs[i] = getNextChr(chr);
            nextNodes.add(String.valueOf(chrs));
            chrs[i] = getPrevChar(chr);
            nextNodes.add(String.valueOf(chrs));
            chrs[i] = chr;
        }
        return nextNodes;
    }

    private char getNextChr(char chr) {
        return chr - '0' >= 9 ? '0' : (char) (chr + 1);
    }

    private char getPrevChar(char chr) {
        return chr - '0' <= 0 ? '9' : (char) (chr - 1);
    }
}
