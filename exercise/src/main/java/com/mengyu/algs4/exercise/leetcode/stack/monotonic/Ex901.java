package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/7/7 8:48 上午
 * leetcode901
 */
public class Ex901 {
    // 从栈底 -> 栈顶 递减的单调栈
    private Stack<StockInfo> stack;
    public Ex901() {
        stack=new Stack<>();
    }

    public int next(int price) {
        StockInfo stockInfo=new StockInfo(price,1);
        while(!stack.isEmpty()&&price>=stack.peek().price){
            stockInfo.count+=stack.pop().count;
        }
        stack.push(stockInfo);
        return stockInfo.count;
    }

    static class StockInfo{
        int price;
        int count;

        public StockInfo(int price,int count) {
            this.price = price;
            this.count=count;
        }
    }
}
