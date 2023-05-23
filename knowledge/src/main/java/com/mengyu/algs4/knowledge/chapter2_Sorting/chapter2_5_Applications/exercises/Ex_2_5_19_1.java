package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_5_Applications.exercises;

/**
 * @author yuzhang
 * @date 2020/10/30 11:29 上午
 * TODO
 */
public class Ex_2_5_19_1 {
    public static void main(String[] args) {
        int[] a = {0, 3, 1, 6, 2, 5, 4};
        int[] b = {1, 0, 3, 6, 4, 2, 5};
        System.out.println(solution(a, b));
    }
    private static int[] aux;

    public static int solution(int[] a,int[] b){
        // aniv[a[i]]=i
        int[] aniv=new int[a.length];
        for (int i = 0; i < a.length; i++) {
            aniv[a[i]]=i;
        }
        int[] bnew = new int[a.length];
        for (int i = 0; i < b.length; i++) {
            bnew[i]=aniv[b[i]];
        }
        aux=new int[b.length];
        return countInversion(bnew,0,b.length-1);
    }

    private static int countInversion(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return 0;
        }
        int inversion = 0, mid = lo + (hi - lo) / 2;
        inversion += countInversion(arr, lo, mid);
        inversion += countInversion(arr, mid + 1, hi);
        inversion += merge(arr, lo, mid, hi);
        return inversion;
    }

    private static int merge(int[] arr, int lo, int mid, int hi) {
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
        int i = lo, j = mid + 1, inversion = 0;
        for (int k = lo; k < hi + 1; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > hi) {
                arr[k] = aux[i++];
            } else if (arr[i] > arr[j]) {
                inversion += j - k;
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }
        return inversion;
    }
}
