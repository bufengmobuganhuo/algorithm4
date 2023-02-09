package leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1797 {

    private final Map<String, Integer> map;

    private final int timeToLive;

    public Ex1797(int timeToLive) {
        this.map = new HashMap<>();
        this.timeToLive = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        this.map.put(tokenId, currentTime + timeToLive);
    }

    public void renew(String tokenId, int currentTime) {
        int time = map.getOrDefault(tokenId, -1);
        if (time > currentTime) {
            map.put(tokenId, currentTime + timeToLive);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        int count = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > currentTime) {
                count++;
            }
        }
        return count;
    }
}
