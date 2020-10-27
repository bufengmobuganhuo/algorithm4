package leetcode.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/10/20 9:54 上午
 * TODO
 */
public class Ex385_1 {
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
        boolean isNegative = false;
        int num = 0;
        while (curIdx != chars.length) {
            curIdx++;
            if (chars[curIdx] == ',') {
                // 如果以'['开头，则递归获取
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
