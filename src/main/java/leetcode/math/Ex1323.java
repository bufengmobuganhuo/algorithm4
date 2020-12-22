package leetcode.math;

/**
 * @author yuzhang
 * @date 2020/12/21 上午9:00
 * TODO
 */
public class Ex1323 {
    public int maximum69Number (int num) {
        char[] chrs = String.valueOf(num).toCharArray();
        for (int i = 0; i < chrs.length; i++) {
            if (chrs[i]=='6'){
                chrs[i]='9';
                break;
            }
        }
        return Integer.parseInt(String.valueOf(chrs));
    }
}
