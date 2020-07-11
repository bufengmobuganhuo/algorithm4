package leetcode.stack.monotonic;

import java.util.LinkedList;

/**
 * @author yuzhang
 * @date 2020/7/5 11:18 上午
 * leetcode402:移掉k位数字
 * 1. 对于两个数字：A: 1axxxx和B: 1bxxxx，最左边第一个不同的数字决定了他们的大小：a>b则 A>B
 * 2. 由此可知道删除数字需要从左边开始删除（以 425 为例）：
 * 1⃣️ 对于一个带删除的数字（此处是4），就需要和他的左邻居比较（2），如果这个数>左邻居，则可以删除，
 * 因为如果保留4，则得到的42或45均>25
 * 2⃣️ 对于一种极端情况，如果数字的每一位是递增的：12345678，则从左到右开始，每一位都比左邻居小，此时只需要从右往左删除即可
 */
public class Ex402 {
    public static void main(String[] args) {
        String num = "9";
        Ex402 ex402 = new Ex402();
        System.out.println(ex402.removeKdigits(num, 1));
    }

    public String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList<>();
        for (Character digit : num.toCharArray()) {
            // stack的最后一位元素是带删除元素，digit是他的左邻居(最多删除k个)
            while (!stack.isEmpty() && k > 0 && stack.peekLast() > digit) {
                stack.removeLast();
                k--;
            }
            stack.addLast(digit);

        }
        // 处理第二种情况：123456789
        for (int i = 0; i < k; i++) {
            stack.removeLast();
        }
        StringBuilder res = new StringBuilder();
        // stack中可能剩下的是：00200，需要去除前导零
        boolean leadingzero = true;
        for (Character digit : stack) {
            if (leadingzero && digit == '0') {
                continue;
            }
            leadingzero = false;
            res.append(digit);
        }
        return res.length()==0?"0":res.toString();
    }
}
