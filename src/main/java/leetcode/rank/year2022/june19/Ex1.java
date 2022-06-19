package leetcode.rank.year2022.june19;

/**
 * @author yuzhang
 * @date 2022/6/19 10:16
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        System.out.println(new Ex1().greatestLetter("lEeTcOdE"));
    }
    public String greatestLetter(String s) {
        boolean[] marked = new boolean[52];
        String ans = "";
        for (char chr : s.toCharArray()) {
            if (chr - 'a' >= 0) {
                marked[chr - 'a'] = true;
                if (marked[Character.toUpperCase(chr) - 'A' + 26] && ans.compareTo(String.valueOf(Character.toUpperCase(chr))) < 0) {
                    ans = String.valueOf(Character.toUpperCase(chr));
                }
            } else {
                marked[chr - 'A' + 26] = true;
                if (marked[Character.toLowerCase(chr) - 'a'] && ans.compareTo(String.valueOf(chr)) < 0) {
                    ans = String.valueOf(chr);
                }
            }
        }
        return ans;
    }
}
