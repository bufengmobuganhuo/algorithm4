package leetcode.rank.year2022.june12;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2022/6/12 10:22
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[] cookies = {93138, 59863, 10226, 96108, 29297, 40027, 28150};
        System.out.println(new Ex3().distributeCookies(cookies, 3));
    }

    public int distributeCookies(int[] cookies, int k) {
        int sum = Arrays.stream(cookies).sum();
        int leftPtr = sum / k, rightPtr = sum;
        while (leftPtr <= rightPtr) {
            int mid = leftPtr + (rightPtr - leftPtr) / 2;
            if (check(cookies, k, mid)) {
                rightPtr = mid - 1;
            } else {
                leftPtr = mid + 1;
            }
        }
        return leftPtr;
    }

    private boolean check(int[] cookies, int k, int target) {
        int[] children = new int[k];
        return backtracking(cookies, children, target, 0);
    }

    private boolean backtracking(int[] cookies, int[] children, int target, int idx) {
        if (idx == cookies.length) {
            return true;
        }
        for (int j = 0; j < children.length; j++) {
            if (cookies[idx] + children[j] <= target) {
                children[j] += cookies[idx];
                if (backtracking(cookies, children, target, idx + 1)) {
                    return true;
                }
                children[j] -= cookies[idx];
            }
        }
        return false;
    }
}
