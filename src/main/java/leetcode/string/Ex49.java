package leetcode.string;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/8/23 10:50 上午
 * TODO
 */
public class Ex49 {
    public static void main(String[] args) {
        String[] strs = {"","b"};
        Ex49 ex49 = new Ex49();
        System.out.println(ex49.groupAnagrams(strs));
        System.out.println("".equals(""));
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList();
        }
        Map<String, List> ans = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

}
