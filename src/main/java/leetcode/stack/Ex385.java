package leetcode.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/7/4 8:43 下午
 * TODO
 */
public class Ex385 {
    public static void main(String[] args) {
        String exp = "[-1]";
        Ex385 ex385 = new Ex385();
        NestedInteger nestedInteger = ex385.deserialize(exp);
    }

    private int curIdx;
    private char[] chars;

    public NestedInteger deserialize(String s) {
        if (!s.startsWith("[")) {
            return new NestedInteger(Integer.parseInt(s));
        }
        curIdx = 0;
        chars = s.toCharArray();
        return getNext();
    }

    private NestedInteger getNext() {
        NestedInteger nestedInteger = new NestedInteger();
        // 表示整数
        int num = 0;
        boolean isNegative = false;
        while (curIdx != chars.length - 1) {
            curIdx++;
            if (chars[curIdx] == ',') {
                // 递归获取子集合
            } else if (chars[curIdx] == '[') {
                nestedInteger.add(getNext());
            } else if (chars[curIdx] == '-') {
                isNegative = true;
            } else if (chars[curIdx] == ']') {
                return nestedInteger;
            } else {
                if (isNegative) {
                    num = 10 * num - (chars[curIdx] - 48);
                } else {
                    num = 10 * num + (chars[curIdx] - 48);
                }
                // 如果下一个为非数字，则说明数字组合完毕
                if (chars[curIdx + 1] == ',' || chars[curIdx + 1] == ']') {
                    nestedInteger.add(new NestedInteger(num));
                    num = 0;
                    isNegative = false;
                }
            }
        }
        return null;
    }

    static class NestedInteger {
        Integer value;
        List<NestedInteger> list;

        public NestedInteger(int value) {
            this.value = value;
        }

        public NestedInteger() {
        }

        public boolean isInteger() {
            return value != null;
        }

        public List<NestedInteger> getList() {
            return list;
        }

        public void setInteger(int value) {
            this.value = value;
        }

        public void add(NestedInteger ni) {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(ni);
        }

    }
}
