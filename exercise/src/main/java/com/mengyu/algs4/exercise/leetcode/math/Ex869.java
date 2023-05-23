package com.mengyu.algs4.exercise.leetcode.math;

import java.util.HashSet;

/**
 * @author yuzhang
 * @date 2021/10/28 上午11:16
 * TODO
 */
public class Ex869 {
    public static void main(String[] args) {
        Ex869 ex869 = new Ex869();
        System.out.println(ex869.reorderedPowerOf2(1));
    }
    private HashSet<String> initSet;
    /**
     * 1. 由于可以按照任何顺序排序，对于两个数a,b，如果它们对应的每个数字字符从小到大排序后相同的话，则如果a是2的幂，则b也是
     * 2. 更进一步，如果a,b中每个字符的出现次数相同，则二者的结果是等价的
     * 3. 在[1,10^9]范围内，共有2^0,2^1,...2^29共30个数符合条件，则可以使用上述原理提前处理这30个数并保存到哈希表中；
     * 对于给定的一个数，只需要统计每个字符的出现次数后看哈希表中是否存在则可知道是否是2的幂
     */
    public boolean reorderedPowerOf2(int n) {
        String countStr = countDigits(n);
        return initSet.contains(countStr);
    }

    public Ex869() {
        initSet = new HashSet<>();
        for (int i = 1; i <= 1e9; i<<=1) {
            initSet.add(countDigits(i));
        }
    }

    private String countDigits(int num) {
        // char占用的字节数更少
        char[] count = new char[10];
        while (num != 0) {
            count[num % 10]++;
            num /= 10;
        }
        return String.valueOf(count);
    }
}
