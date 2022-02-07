package leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex1996 {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o2[1] - o1[1];
        });
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int[] property : properties) {
            while (!stack.isEmpty() && stack.peek() < property[1]) {
                stack.pop();
                ans++;
            }
            stack.push(property[1]);
        }
        return ans;
    }
}
