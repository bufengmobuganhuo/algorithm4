package leetcode.rank.sep12nd;

/**
 * @author yuzhang
 * @date 2021/9/12 上午10:32
 * TODO
 */
public class Ex1 {
    public String reversePrefix(String word, char ch) {
        StringBuilder sb = new StringBuilder(word);
        int idx = 0;
        for (; idx < sb.length(); idx++) {
            if (sb.charAt(idx) == ch) {
                break;
            }
        }
        if (idx == sb.length()) {
            return sb.toString();
        }
        reverse(sb, 0, idx);
        return sb.toString();
    }

    private void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char chr = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, chr);
            start++;
            end--;
        }
    }

}
