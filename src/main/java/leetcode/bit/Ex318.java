package leetcode.bit;

/**
 * @author yuzhang
 * @date 2021/11/28 9:45 上午
 * TODO
 */
public class Ex318 {
    public int maxProduct(String[] words) {
        long[] arr = new long[words.length];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            long num = 0;
            for (int j = 0; j < word.length(); j++) {
                num = num | (1L << (word.charAt(j) - 'a'));
            }
            arr[i] = num;
        }
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((arr[i] & arr[j]) == 0) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }
        return res;
    }
}
