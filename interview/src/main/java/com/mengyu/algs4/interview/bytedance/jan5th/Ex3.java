package com.mengyu.algs4.interview.bytedance.jan5th;

/**
 * @author yuzhang
 * @date 2021/1/5 上午9:32
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[] people = {1, 2};
        Ex3 ex3 = new Ex3();
        System.out.println(ex3.numRescueBoats(people, 6));
    }

    public int numRescueBoats(int[] people, int limit) {
        if (people == null || people.length == 0) {
            return 0;
        }
        sort(people, 0, people.length - 1);
        int count = 0;
        int left = 0, right = people.length - 1;
        while (left < right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            count++;
        }
        return left == right ? count + 1 : count;
    }

    private void sort(int[] people, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        int partitionIdx = partition(people, start, mid, end);
        sort(people, partitionIdx + 1, end);
        sort(people, start, partitionIdx - 1);
    }

    private int partition(int[] people, int start, int mid, int end) {
        int left = start, right = end + 1, partitionEle = people[start];
        while (true) {
            while (people[--right] > partitionEle) {
                if (right <= start) {
                    break;
                }
            }
            while (people[++left] < partitionEle) {
                if (left >= end) {
                    break;
                }
            }
            if (left >= right) {
                break;
            }
            exch(people, left, right);
        }
        exch(people, start, right);
        return right;
    }

    private void exch(int[] people, int i, int j) {
        int tmp = people[i];
        people[i] = people[j];
        people[j] = tmp;
    }
}
