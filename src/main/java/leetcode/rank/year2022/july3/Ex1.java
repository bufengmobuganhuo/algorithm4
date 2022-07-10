package leetcode.rank.year2022.july3;

/**
 * @author yuzhang
 * @date 2022/7/3 10:23
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        System.out.println(new Ex1().decodeMessage("the quick brown fox jumps over the lazy dog", "vkbs bs t suepuv"));
    }
    public String decodeMessage(String key, String message) {
        boolean[] visited = new boolean[26];
        char[] mapped = new char[26];
        int idx = 0;
        for (int i = 0; i < key.length(); i++) {
            char chr = key.charAt(i);
            if (chr == ' ') {
                continue;
            }
            if (visited[chr - 'a']) {
                continue;
            }
            visited[chr - 'a'] = true;
            mapped[chr - 'a'] = (char) (idx + 'a');
            idx++;
        }
        StringBuilder ans = new StringBuilder();
        for (char chr : message.toCharArray()) {
            if (chr == ' ') {
                ans.append(chr);
            } else {
                ans.append(mapped[chr - 'a']);

            }
        }
        return ans.toString();
    }
}
