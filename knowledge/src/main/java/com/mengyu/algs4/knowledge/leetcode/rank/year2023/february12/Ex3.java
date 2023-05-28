package leetcode.rank.year2023.february12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2023/2/12 10:04
 * TODO
 */
public class Ex3 {

    public static void main(String[] args) {
        int[][] queries = {
                {1,1000000000}, {1, 2}
        };
        Arrays.deepToString(new Ex3().substringXorQueries("111011100110101100101000000001", queries));
    }

    public int[][] substringXorQueries(String s, int[][] queries) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (!map.containsKey(0)) {
                    map.put(0, new int[]{i, i});
                }
                continue;
            }
            int num = 1;
            if (!map.containsKey(1)) {
                map.put(num, new int[]{i, i});
            }
            for (int j = i + 1; j < Math.min(s.length(), i + 30); j++) {
                num = num * 2 + (s.charAt(j) - '0');
                if (!map.containsKey(num)) {
                    map.put(num, new int[]{i, j});
                }

            }
        }
        int[][] ans = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int target = query[0] ^ query[1];
            ans[i] = map.getOrDefault(target, new int[]{-1, -1});
        }
        return ans;
    }
}
