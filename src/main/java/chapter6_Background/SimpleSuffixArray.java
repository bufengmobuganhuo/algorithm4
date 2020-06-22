package chapter6_Background;

/**
 * @author yuzhang
 * @date 2020/6/19 11:52 上午
 * 有序后缀数组的简单实现
 */
public class SimpleSuffixArray implements SuffixArray {
    /**
     * 后缀数组
     */
    private String[] suffixes;
    /**
     * 文本字符串长度，同时也是后缀字符的长度
     */
    private int len;

    public SimpleSuffixArray(String text) {
        this.len = text.length();
        suffixes = new String[text.length()];
        /**
         * 倒着来：suffixes[len-1]:text最后一个字符
         */
        for (int i = 0; i < len; i++) {
            suffixes[i] = text.substring(i);
        }
        quickSort(suffixes);
    }

    @Override
    public int length() {
        return this.len;
    }

    @Override
    public String select(int i) {
        return suffixes[i];
    }

    @Override
    public int index(int i) {
        return len - suffixes[i].length();
    }

    @Override
    public int lcp(int i) {
        return lcp(select(i), select(i - 1));
    }

    private int lcp(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        for (int i = 0; i < len; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return i;
            }
        }
        return len;
    }


    @Override
    public int rank(String key) {
        int lo = 0, hi = key.length() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmpRes = key.compareTo(suffixes[mid]);
            if (cmpRes > 0) {
                lo = mid + 1;
            } else if (cmpRes < 0) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    private void quickSort(String[] arr) {
        quickSort(arr, 0, arr.length-1);
    }

    private void quickSort(String[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int partitionIdx = partition(arr, lo, hi);
        quickSort(arr, lo, partitionIdx - 1);
        quickSort(arr, partitionIdx + 1, hi);
    }


    private int partition(String[] arr, int lo, int hi) {
        int left = lo, right = hi + 1;
        String partitionKey = arr[lo];
        while (true) {
            while (partitionKey.compareTo(arr[++left]) > 0) {
                if (left == hi) {
                    break;
                }
            }
            while (partitionKey.compareTo(arr[--right]) < 0) {
                if (right == lo) {
                    break;
                }
            }
            if (left >= right) {
                break;
            }
            exch(arr, left, right);
        }
        exch(arr, lo, right);
        return right;
    }

    private void exch(String[] arr, int i, int j) {
        String tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
