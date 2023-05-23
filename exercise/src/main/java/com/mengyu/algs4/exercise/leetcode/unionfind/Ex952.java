package com.mengyu.algs4.exercise.leetcode.unionfind;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/1/6 上午9:42
 * TODO
 */
public class Ex952 {
    public static void main(String[] args) {
        int[] A = {4, 6, 15, 35};
        Ex952 ex952 = new Ex952();
        System.out.println(ex952.largestComponentSize(A));
    }

    public int largestComponentSize(int[] A) {
        if (A == null || A.length == 0) {
            return 1;
        }
        int len = A.length;
        // 存储质因数，去重
        Set<Integer> primFactorSet = new HashSet<>();
        // 质因数 ---> A中元素的数组索引 （<x,y> : 质因数为x的元素在数组A中的下标）
        Map<Integer, LinkedList<Integer>> prim2NumIdx = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int primFactor = 2, num = A[i];
            // 是否包含非本身的质因数
            boolean hasPrimFactorExceptSelf = false;
            // 最大质因数就是sqrt(num)
            while (primFactor * primFactor <= num) {
                // 说明primFactor是num的质因数
                if (num % primFactor == 0) {
                    while (num % primFactor == 0) {
                        num /= primFactor;
                    }
                    hasPrimFactorExceptSelf = true;
                    primFactorSet.add(primFactor);
                    LinkedList<Integer> numsIdx = prim2NumIdx.getOrDefault(primFactor, new LinkedList<>());
                    numsIdx.offer(i);
                    prim2NumIdx.put(primFactor, numsIdx);
                }
                primFactor++;
            }
            // 如果还有剩下的没除尽（如6=2*3）|| 没有非本身的质因数
            if (num > 1 || !hasPrimFactorExceptSelf) {
                LinkedList<Integer> list = prim2NumIdx.getOrDefault(num,new LinkedList<>());
                list.offer(i);
                prim2NumIdx.put(num, list);
                primFactorSet.add(num);
            }
        }
        UnionFind unionFind = new UnionFind(A.length);
        // 把质因数相同的都连起来
        for (Integer primFactor : primFactorSet) {
            LinkedList<Integer> numIdx = prim2NumIdx.get(primFactor);
            int point1 = numIdx.poll();
            while (!numIdx.isEmpty()) {
                unionFind.connect(point1,numIdx.poll());
            }
        }
        return unionFind.maxSize;
    }

    static class UnionFind {
        int[] ids;
        int[] weights;
        int maxSize;

        public UnionFind(int cap) {
            ids = new int[cap];
            weights = new int[cap];
            for (int i = 0; i < cap; i++) {
                ids[i] = i;
                weights[i] = 1;
            }
            maxSize = 1;
        }

        public void connect(int point1, int point2) {
            int root1 = find(point1);
            int root2 = find(point2);
            if (root1 == root2) {
                return;
            }
            if (weights[root1] > weights[root2]) {
                weights[root1] += weights[root2];
                ids[root2] = root1;
                maxSize = Math.max(weights[root1], maxSize);
            } else {
                weights[root2] += weights[root1];
                ids[root1] = root2;
                maxSize = Math.max(weights[root2], maxSize);
            }
        }

        private int find(int point1) {
            while (ids[point1] != point1) {
                ids[point1] = ids[ids[point1]];
                point1 = ids[point1];
            }
            return point1;
        }
    }
}
