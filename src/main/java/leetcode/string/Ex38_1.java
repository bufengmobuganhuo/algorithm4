package leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex38_1 {
    public static void main(String[] args) {
        Ex38_1 ex38_1 = new Ex38_1();
        System.out.println(ex38_1.countAndSay(10));
        System.out.println(ex38_1.countAndSay(3));
    }

    private Map<Integer, String> cache;

    public Ex38_1() {
        cache = new HashMap<>();
    }

    public String countAndSay(int n) {
        String res = "1";
        for (int i = 1; i < n; i++) {
            if (cache.containsKey(i)) {
                res = cache.get(i);
            } else {
                res = description(res);
                cache.put(i, res);
            }
        }
        return res;
    }

    private String description(String str) {
        int count = 0;
        char chr = '0';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (i == 0) {
                chr = ch;
                count = 1;
            } else if (ch == chr) {
                count++;
            } else {
                sb.append(count);
                sb.append(chr);
                chr = ch;
                count = 1;
            }
        }
        sb.append(count);
        sb.append(chr);
        return sb.toString();
    }
}
