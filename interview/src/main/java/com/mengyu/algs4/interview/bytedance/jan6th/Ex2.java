package com.mengyu.algs4.interview.bytedance.jan6th;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/1/6 上午9:28
 * TODO
 */
public class Ex2 {
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        if (customfunction == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int x = 1; x < 1001; x++) {
            if (customfunction.f(x,1)>z){
                break;
            }
            for (int y = 1; y < 1001; y++) {
                int funcRes = customfunction.f(x,y);
                if (funcRes==z){
                    res.add(Arrays.asList(x,y));
                }else if (funcRes>z){
                    break;
                }
            }
        }
        return res;
    }

    class CustomFunction {
        // Returns f(x, y) for any given positive integers x and y.
        // Note that f(x, y) is increasing with respect to both x and y.
        // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
        public int f(int x, int y) {
            return 0;
        }
    }

    ;
}
