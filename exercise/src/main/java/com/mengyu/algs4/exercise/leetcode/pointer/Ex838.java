package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yuzhang
 * @date 2020/9/23 9:58 上午
 * 1. 将"R"或"L"分段，一个区间内的骨牌状态不受区间外"R"或"L"的影响
 * 2. 对于一个区间内的骨牌，有如下四种情况：
 * 1⃣️ A(A或B重复)B：若A=B，则该区间内的字符串变为："AAAAAA"
 * 2⃣️ "R....L"：会变为RRRLLL或RR.LL，这取决于R，L之间"."的奇偶性，如果奇数，则为RR.LL；如果是偶数，则为RRRLLL
 * 3⃣️ "L...R"：保持不变
 */
public class Ex838 {
    public static void main(String[] args) {
        Ex838 ex838 = new Ex838();
        System.out.println(ex838.pushDominoes(".L.R...LR..L.."));
    }

    public String pushDominoes(String dominoes) {
        if (dominoes == null || dominoes.length() == 0) {
            return dominoes;
        }
        int len = dominoes.length();
        // 记录分段位置字符在字符串中的索引
        int[] symbolIdx = new int[len + 2];
        // 记录分段位置的字符，
        // 假设symbolIdx[i]=left, symbolIdx[i+1]=right, symbol[i]=R, symbol[i+1]=L,表示dominoes[left...right]=R...L
        char[] symbol = new char[len + 2];
        int index = 1;
        // 哨兵
        symbolIdx[0] = -1;
        symbol[0] = 'L';
        // 记录分段的字符
        for (int i = 0; i < len; i++) {
            if (dominoes.charAt(i) != '.') {
                symbolIdx[index] = i;
                symbol[index++] = dominoes.charAt(i);
            }
        }
        // 哨兵
        symbolIdx[index] = len;
        symbol[index] = 'R';
        char[] ans = dominoes.toCharArray();
        for (int i = 0; i < index; i++) {
            int leftPtr = symbolIdx[i], rightPtr = symbolIdx[i + 1];
            char leftSys = symbol[i], rightSys = symbol[i + 1];
            if (leftSys == rightSys) {
                for (int j = leftPtr + 1; j < rightPtr; j++) {
                    ans[j] = leftSys;
                }
                // R...L形式， L...R形式不影响
            } else if (leftSys > rightSys) {
                for (int k = leftPtr + 1; k < rightPtr; k++) {
                    if (k - leftPtr == rightPtr - k) {
                        ans[k] = '.';
                        // k位置距离右边近，则右边对他有影响，会倒向左边
                    } else if (k - leftPtr > rightPtr - k) {
                        ans[k] = 'L';
                    } else {
                        ans[k] = 'R';
                    }
                }
            }
        }
        return String.valueOf(ans);
    }
}
