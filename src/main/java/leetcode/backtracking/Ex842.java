package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/12/8 下午2:42
 * TODO
 */
public class Ex842 {
    public static void main(String[] args) {
        Ex842 ex842 = new Ex842();
        String S = "17522";
        System.out.println(Arrays.toString(ex842.splitIntoFibonacci(S).toArray()));
    }

    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        backtracking(S, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtracking(String num, int startIdx, List<Integer> track, List<Integer> res) {
        // 只要拿到结果就返回，不需要得到所有结果
        if (res.size() > 0) {
            return;
        }
        // 字符串无法再分出新数字
        if (startIdx >= num.length()) {
            if (track.size() >= 3) {
                res.addAll(track);
            }
            return;
        }
        // 如果数列中数字的个数超过2，则可以使用斐波那契数列的规则生成下一个数
        if (track.size() >= 2) {
            int num1 = track.get(track.size() - 2);
            int num2 = track.get(track.size() - 1);
            if (num.startsWith(Integer.toString(num1 + num2), startIdx)) {
                track.add(num1 + num2);
                backtracking(num, startIdx + Integer.toString(num1 + num2).length(), track, res);
                track.remove(track.size() - 1);
            }
            // 用于生成数列中第0，1个数
        } else {
            // 每个数的位数最多只能是整个字符串长度的1/3
            for (int i = 1; i <= num.length() / 3 + 1; i++) {
                String num1 = num.substring(startIdx, startIdx + i);
                // 如果是以"0"开头的非0数字，则不符合条件，直接break
                if (num1.length() > 1 && num1.startsWith("0")) {
                    break;
                }
                // 前面的数一定要比后面的数小（当前数的位数 < 后面数的位数）
                if (num1.length() > num.length() - (startIdx + i)) {
                    break;
                }
                try{
                    int value = Integer.parseInt(num1);
                    track.add(value);
                    backtracking(num, startIdx + i, track, res);
                    track.remove(track.size() - 1);
                }catch (NumberFormatException e){
                    // 题目返回是int类型，如果超过了int，则不符合条件，直接break
                    break;
                }
            }
        }
    }
}
