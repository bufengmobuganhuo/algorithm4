package com.mengyu.algs4.exercise.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex1104 {
    public static void main(String[] args) {
        Ex1104 ex1104 = new Ex1104();
        System.out.println(ex1104.pathInZigZagTree(14));
    }

    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> ans = new ArrayList<>();
        while (label != 1) {
            ans.add(0, label);
            label = findParent(label);
        }
        ans.add(0, 1);
        return ans;
    }

    private int findParent(int label) {
        // 找到在第几层
        long layer = (long) (Math.floor(log(label) + 1));
        if (layer == 2) {
            return 1;
        }
        if (layer % 2 == 0) {
            long layerRight = (layer << 1) - 1;
            long layerLeft = (long) Math.pow(2, layer - 1);
            // 当前层是偶数层，需要进行一个转化
            label = (int) (layerRight - (label - layerLeft));
            return label / 2;
        }
        // 正序情况下，上一层最右边节点编号
        long lastLayerRightNode = (long) Math.pow(2, layer - 1) - 1;
        long lastLayerLeftNode = (long) Math.pow(2, layer - 2);
        // 上一层按照正序的话,父亲节点的编号
        long parentInOrder = label / 2;
        // 上一层是奇数层，则需要进行一个转化
        return (int) (lastLayerRightNode - (parentInOrder - lastLayerLeftNode));
    }

    private double log(int num) {
        return Math.log(num) / Math.log(2);
    }

}
