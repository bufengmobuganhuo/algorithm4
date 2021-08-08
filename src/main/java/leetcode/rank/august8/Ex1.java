package leetcode.rank.august8;

/**
 * @author yuzhang
 * @date 2021/8/8 上午10:24
 * TODO
 */
public class Ex1 {
    public boolean isPrefixString(String s, String[] words) {
        StringBuilder source = new StringBuilder();
        for (int i = 0; i < words.length && source.length() < s.length(); i++) {
            source.append(words[i]);
        }
        return source.toString().equals(s);
    }
}
