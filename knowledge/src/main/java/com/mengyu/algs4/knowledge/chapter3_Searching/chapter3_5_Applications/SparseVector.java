package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_5_Applications;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyu
 * 2020/4/9 15:32
 * 稀疏向量，表示稀疏矩阵的一行
 */
public class SparseVector {
    public static void main(String[] args) {
        /**
         * 使用稀疏向量表示的稀疏矩阵与向量相乘
         *    0 0.9     0   0     0    0.05
         *    0   0 0.36 0.36 0.18  * 0.04
         *   0   0    0  0.9    0    0.36
         *  0.9  0   0    0    0    0.37
         * 0.47 0 0.47    0    0   0.19
         * */
        //使用稀疏矩阵每一行的非零项构造矩阵

        SparseVector[] matrix = new SparseVector[5];
        for (int i = 0; i < 5; i++) {
            matrix[i] = new SparseVector();
        }
        matrix[0].put(1, 0.9);

        matrix[1].put(2, 0.36);
        matrix[1].put(3, 0.36);
        matrix[1].put(4, 0.18);

        matrix[2].put(3, 0.9);

        matrix[3].put(0, 0.9);

        matrix[4].put(0, 0.47);
        matrix[4].put(2, 0.47);

        Double[] vector = {0.05, 0.04, 0.36, 0.37, 0.19};
        Double[] result = new Double[5];
        for (int i = 0; i < 5; i++) {
            result[i] = matrix[i].dot(vector);
        }
        System.out.println(Arrays.toString(result));
    }

    private Map<Integer, Double> map;

    public SparseVector() {
        map = new HashMap<>();
    }

    public int size() {
        return map.size();
    }

    public Double get(Integer key) {
        return map.get(key);
    }

    /**
     * @param key   这一行第i个数
     * @param value 这一行第i个数的值
     */
    public void put(Integer key, Double value) {
        map.put(key, value);
    }

    /**
     * 这一行与该向量的点乘
     *
     * @param vector 向量
     * @return
     */
    public Double dot(Double[] vector) {
        double sum = 0.0;
        for (Integer key : map.keySet()) {
            sum += map.get(key) * vector[key];
        }
        return sum;
    }
}
