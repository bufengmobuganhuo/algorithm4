package leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex722 {
    public List<String> removeComments(String[] source) {
        List<String> ans = new ArrayList<>();
        boolean inBlock = false;
        StringBuilder line = null;
        for (String src : source) {
            int i = 0;
            if (!inBlock) {
                line = new StringBuilder();
            }
            while (i < src.length()) {
                if (!inBlock && src.charAt(i) == '/' && i + 1 < src.length() && src.charAt(i + 1) == '*') {
                    inBlock = true;
                    i++;
                } else if (inBlock && src.charAt(i) == '*' && i + 1 < src.length() && src.charAt(i + 1) == '/') {
                    inBlock = false;
                    i++;
                } else if (!inBlock && src.charAt(i) == '/' && i + 1 < src.length() && src.charAt(i + 1) == '/') {
                    break;
                } else if (!inBlock) {
                    line.append(src.charAt(i));
                }
                i++;
            }
            if (!inBlock && line.length() > 0) {
                ans.add(line.toString());
            }
        }
        return ans;
    }
}
