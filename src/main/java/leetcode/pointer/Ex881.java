package leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex881 {
    public static void main(String[] args) {
        Ex881 ex881 = new Ex881();
        int[] people = {2, 3, 2, 1};
        System.out.println(ex881.numRescueBoats(people, 3));
    }

    public int numRescueBoats(int[] people, int limit) {
        int len = people.length;
        sort(people, 0, len - 1);
        int leftPtr = 0, rightPtr = len - 1;
        int count = 0;
        while (leftPtr < rightPtr) {
            if (people[leftPtr] + people[rightPtr] > limit) {
                rightPtr--;
                count++;
            } else {
                leftPtr++;
                rightPtr--;
                count++;
            }
        }
        if (leftPtr == rightPtr) {
            count++;
        }
        return count;
    }

    private void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int partitionIdx = partition(arr, start, end);
        sort(arr, start, partitionIdx - 1);
        sort(arr, partitionIdx + 1, end);
    }

    private int partition(int[] arr, int start, int end) {
        int left = start, right = end + 1;
        int partitionEle = arr[left];
        while (true) {
            while (left < end && partitionEle > arr[++left]) {
                if (left >= end) {
                    break;
                }
            }
            while (right > start && partitionEle < arr[--right]) {
                if (right <= start) {
                    break;
                }
            }
            if (left >= right) {
                break;
            }
            exch(arr, left, right);
        }
        exch(arr, start, right);
        return right;
    }

    private void exch(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
