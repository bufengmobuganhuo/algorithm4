package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_5_UnionFind;

import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {
        UF quickFind=new UF_QuickFind(10);
        UF quickUnion=new UF_QuickUnion(10);
        UF weightQuickUnion=new UF_WeightedQuickUnion(10);
        UF weightQuckUnionBasedOnDynamicArr=new UF_QuickUnionBasedOnDynamicArr();

        quickFind.union(9,0);
        printArr("quick-find",quickFind.connectedComponentId);
        quickFind.union(3,4);
        printArr("quick-find",quickFind.connectedComponentId);
        quickFind.union(5,8);
        printArr("quick-find",quickFind.connectedComponentId);
        quickFind.union(7,2);
        printArr("quick-find",quickFind.connectedComponentId);
        quickFind.union(2,1);
        printArr("quick-find",quickFind.connectedComponentId);
        quickFind.union(5,7);
        printArr("quick-find",quickFind.connectedComponentId);
        quickFind.union(0,3);
        printArr("quick-find",quickFind.connectedComponentId);
        quickFind.union(4,2);
        printArr("quick-find",quickFind.connectedComponentId);

        quickUnion.union(9,0);
        printArr("quick-union",quickUnion.connectedComponentId);
        quickUnion.union(3,4);
        printArr("quick-union",quickUnion.connectedComponentId);
        quickUnion.union(5,8);
        printArr("quick-union",quickUnion.connectedComponentId);
        quickUnion.union(7,2);
        printArr("quick-union",quickUnion.connectedComponentId);
        quickUnion.union(2,1);
        printArr("quick-union",quickUnion.connectedComponentId);
        quickUnion.union(5,7);
        printArr("quick-union",quickUnion.connectedComponentId);
        quickUnion.union(0,3);
        printArr("quick-union",quickUnion.connectedComponentId);
        quickUnion.union(4,2);
        printArr("quick-union",quickUnion.connectedComponentId);

        weightQuickUnion.union(9,0);
        printArr("weight-quick-union",weightQuickUnion.connectedComponentId);
        weightQuickUnion.union(3,4);
        printArr("weight-quick-union",weightQuickUnion.connectedComponentId);
        weightQuickUnion.union(5,8);
        printArr("weight-quick-union",weightQuickUnion.connectedComponentId);
        weightQuickUnion.union(7,2);
        printArr("weight-quick-union",weightQuickUnion.connectedComponentId);
        weightQuickUnion.union(2,1);
        printArr("weight-quick-union",weightQuickUnion.connectedComponentId);
        weightQuickUnion.union(5,7);
        printArr("weight-quick-union",weightQuickUnion.connectedComponentId);
        weightQuickUnion.union(0,3);
        printArr("weight-quick-union",weightQuickUnion.connectedComponentId);
        weightQuickUnion.union(4,2);
        printArr("weight-quick-union",weightQuickUnion.connectedComponentId);

        weightQuckUnionBasedOnDynamicArr.union(9,0);
        printArr("dynamic-weight-quick-union",weightQuckUnionBasedOnDynamicArr.connectedComponentId);
        weightQuckUnionBasedOnDynamicArr.union(3,4);
        printArr("dynamic-weight-quick-union",weightQuckUnionBasedOnDynamicArr.connectedComponentId);
        weightQuckUnionBasedOnDynamicArr.union(5,8);
        printArr("dynamic-weight-quick-union",weightQuckUnionBasedOnDynamicArr.connectedComponentId);
        weightQuckUnionBasedOnDynamicArr.union(7,2);
        printArr("dynamic-weight-quick-union",weightQuckUnionBasedOnDynamicArr.connectedComponentId);
        weightQuckUnionBasedOnDynamicArr.union(2,1);
        printArr("dynamic-weight-quick-union",weightQuckUnionBasedOnDynamicArr.connectedComponentId);
        weightQuckUnionBasedOnDynamicArr.union(5,7);
        printArr("dynamic-weight-quick-union",weightQuckUnionBasedOnDynamicArr.connectedComponentId);
        weightQuckUnionBasedOnDynamicArr.union(0,3);
        printArr("dynamic-weight-quick-union",weightQuckUnionBasedOnDynamicArr.connectedComponentId);
        weightQuckUnionBasedOnDynamicArr.union(4,2);
        printArr("dynamic-weight-quick-union",weightQuckUnionBasedOnDynamicArr.connectedComponentId);
    }

    private static void printArr(String msg,int[] arr){
        System.out.println(msg+" "+ Arrays.toString(arr));
    }
}
