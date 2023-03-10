package leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1487_1 {
    public static void main(String[] args) {
        String[] names = {"kaido","kaido(1)","kaido","kaido(1)","kaido(2)"};
        System.out.println(Arrays.toString(new Ex1487_1().getFolderNames(names)));
    }
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        String[] ans = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            Integer count = map.getOrDefault(name, 0);
            if (count == 0) {
                ans[i] = name;
                map.put(name, count + 1);
            } else {
                String xName = name + "(" + count + ")";
                while (map.containsKey(xName)) {
                    count++;
                    xName = name + "(" + count + ")";
                }
                ans[i] = xName;
                map.put(xName, 1);
            }
        }
        return ans;
    }
}
