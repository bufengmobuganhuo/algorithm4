package leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1487 {
    public static void main(String[] args) {
        String[] names = {"kaido", "kaido(1)", "kaido", "kaido(1)", "kaido(2)"};
        System.out.println(Arrays.toString(new Ex1487().getFolderNames(names)));
    }

    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        String[] ans = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            if (!map.containsKey(name)) {
                map.put(name, 0);
                ans[i] = name;
            } else {
                int intentionIdx = map.get(name) + 1;
                String intentionName = name + "(" + intentionIdx + ")";
                while (map.containsKey(intentionName)) {
                    intentionIdx++;
                    intentionName = name + "(" + intentionIdx + ")";
                }
                ans[i] = intentionName;
                map.put(intentionName, 0);
                map.put(name, intentionIdx);
            }
        }
        return ans;
    }
}
