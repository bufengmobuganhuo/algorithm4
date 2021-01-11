package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/1/14 上午10:55
 * TODO
 */
public class Ex735 {
    public static void main(String[] args) {
        Ex735 ex735 = new Ex735();
        int[] asteroids = {5, 10, -5};
        System.out.println(Arrays.toString(ex735.asteroidCollision(asteroids)));
    }

    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) {
            return new int[0];
        }
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            while (!stack.isEmpty() && asteroid * stack.peek() < 0) {
                int peek = stack.pop();
                if (Math.abs(asteroid) < Math.abs(peek)) {
                    asteroid = peek;
                } else if (Math.abs(asteroid) == Math.abs(peek)) {
                    asteroid = 0;
                }
            }
            if (asteroid != 0) {
                stack.push(asteroid);
            }
        }
        int[] res = new int[stack.size()];
        int idx = res.length - 1;
        while (!stack.isEmpty()) {
            res[idx--] = stack.pop();
        }
        return res;
    }
}
