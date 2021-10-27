package leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex301_1 {
    private Set<String> validParentheses;

    private char[] charArr;

    public List<String> removeInvalidParentheses(String s) {
        int leftRemove = 0, rightRemove = 0;
        charArr = s.toCharArray();
        validParentheses = new HashSet<>();
        for (char chr : charArr) {
            if (chr == '(') {
                leftRemove++;
            } else if (chr == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } else if (leftRemove == 0) {
                    rightRemove++;
                }
            }
        }
        backtracking(0, 0, leftRemove, rightRemove, 0, new StringBuilder());
        return new ArrayList<>(validParentheses);
    }

    private void backtracking(int leftCount, int rightCount, int leftRemove, int rightRemove, int idx, StringBuilder sb) {
        if (idx == charArr.length) {
            if (leftRemove == 0 && rightRemove == 0) {
                validParentheses.add(sb.toString());
            }
            return;
        }
        char chr = charArr[idx];
        if (chr == '(' && leftRemove > 0) {
            backtracking(leftCount, rightCount, leftRemove - 1, rightRemove, idx + 1, sb);
        }
        if (chr == ')' && rightRemove > 0) {
            backtracking(leftCount, rightCount, leftRemove, rightRemove - 1, idx + 1, sb);
        }
        sb.append(chr);
        if (chr == '(') {
            backtracking(leftCount + 1, rightCount, leftRemove, rightRemove, idx + 1, sb);
        } else if (chr == ')' && leftCount > rightCount) {
            backtracking(leftCount, rightCount + 1, leftRemove, rightRemove, idx + 1, sb);
        } else if (chr != ')') {
            backtracking(leftCount, rightCount, leftRemove, rightRemove, idx + 1, sb);
        }
        sb.deleteCharAt(sb.length() - 1);
    }
}
