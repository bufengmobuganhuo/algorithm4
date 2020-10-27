package leetcode.stack.monotonic;

/**
 * @author yuzhang
 * @date 2020/10/18 10:27 上午
 * TODO
 */
public class Ex556 {
    public int nextGreaterElement(int n) {
        char[] arr = ("" + n).toCharArray();
        int i = arr.length - 1;
        while (i >= 0 && arr[i + 1] <= arr[i]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = arr.length - 1;
        while (j >= 0 && arr[i] >= arr[j]) {
            j--;
        }
        swap(arr, i, j);
        reverse(arr,i+1);
        return Integer.parseInt(new String(arr));
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void reverse(char[] arr, int start) {
        int i = start, j = arr.length - 1;
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }
}
