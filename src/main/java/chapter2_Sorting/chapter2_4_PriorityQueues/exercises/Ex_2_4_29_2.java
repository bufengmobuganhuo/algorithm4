package chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import utils.ArrayUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yuzhang
 * @date 2020/10/30 9:00 上午
 * TODO
 */
public class Ex_2_4_29_2 {
    public static void main(String[] args) {
        Integer[] arr = ArrayUtil.createInt(8, 10);
        System.out.println(Arrays.toString(arr));
        Ex_2_4_29_2 ex_2_4_29_2 = new Ex_2_4_29_2();
        for (int i = 0; i < arr.length; i++) {
            ex_2_4_29_2.insert(arr[i]);
        }
        System.out.println(ex_2_4_29_2.delMax());
        System.out.println(ex_2_4_29_2.delMax());
        System.out.println(ex_2_4_29_2.delMin());
        System.out.println(ex_2_4_29_2.delMin());
    }

    private PriorityQue minQue;
    private PriorityQue maxQue;
    private int count;

    public Ex_2_4_29_2() {
        minQue = new PriorityQue(Comparator.comparingInt(o -> o.val), 16);
        maxQue = new PriorityQue((o1, o2) -> o2.val - o1.val, 16);
    }

    public int delMin() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node minNode = minQue.del();
        maxQue.del(minNode.id);
        count--;
        return minNode.val;
    }

    public int delMax() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node maxNode = maxQue.del();
        minQue.del(maxNode.id);
        count--;
        return maxNode.val;
    }

    public void insert(int item) {
        Node minNode = new Node(item);
        minQue.insert(minNode);
        Node maxNode = new Node(item);
        maxQue.insert(maxNode);
        minNode.id = maxQue.N;
        maxNode.id = minQue.N;
        count++;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    static class Node {
        int val;
        int id;

        public Node(int val) {
            this.val = val;
        }
    }

    static class PriorityQue {
        Comparator<Node> comparator;
        Node[] nums;
        int N;

        public PriorityQue(Comparator<Node> comparator, int size) {
            this.comparator = comparator;
            nums = new Node[size + 1];
        }

        public Node del(int id) {
            if (isEmpty() || id > nums.length - 1) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int k = 1;
            while (nums[k].id != id) {
                k++;
            }
            Node res = nums[k];
            exch(k, N--);
            sink(k);
            if (N < nums.length / 4) {
                resize(nums.length / 2);
            }
            return res;
        }

        public Node del() {
            if (isEmpty()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Node res = nums[1];
            exch(1, N--);
            sink(1);
            if (N < nums.length / 4) {
                resize(nums.length / 2);
            }
            return res;
        }

        public void insert(Node item) {
            if (N == nums.length - 1) {
                resize(nums.length * 2);
            }
            nums[++N] = item;
            swim(N);
        }

        public boolean isEmpty() {
            return N == 0;
        }

        private void resize(int cap) {
            Node[] tmp = new Node[cap];
            System.arraycopy(nums, 1, tmp, 1, N);
            nums = tmp;
        }

        private void sink(int k) {
            while (2 * k <= N) {
                int j = 2 * k;
                if (j < N - 1 && comparator.compare(nums[j], nums[j + 1]) > 0) {
                    j++;
                }
                if (comparator.compare(nums[k], nums[j]) < 0) {
                    break;
                }
                exch(k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && comparator.compare(nums[k], nums[k / 2]) < 0) {
                exch(k, k / 2);
                k /= 2;
            }
        }

        private void exch(int i, int j) {
            Node tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}
