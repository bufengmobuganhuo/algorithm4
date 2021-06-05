package leetcode.rank.may30;

/**
 * @author yuzhang
 * @date 2021/5/30 上午10:30
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {

    }
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int first = convert2Num(firstWord);
        int second = convert2Num(secondWord);
        int target = convert2Num(targetWord);
        return first + second == target;
    }

    private int convert2Num(String str) {
        StringBuilder sb = new StringBuilder();
        for (char chr : str.toCharArray()) {
            sb.append(chr - 'a');
        }
        return Integer.parseInt(sb.toString());
    }
}
