package leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex838_3 {
    public static void main(String[] args) {
        System.out.println(new Ex838_3().pushDominoes("RR.L"));
    }
    public String pushDominoes(String dominoes) {
        int len = dominoes.length();
        int[] symbolIdx = new int[len + 2];
        char[] symbol = new char[len + 2];
        symbolIdx[0] = -1;
        symbol[0] = 'L';
        int idx= 1;
        for (int i = 0; i < len; i++) {
            if (dominoes.charAt(i) != '.') {
                symbol[idx] = dominoes.charAt(i);
                symbolIdx[idx++] = i;
            }
        }
        symbol[idx] = 'R';
        symbolIdx[idx] = len;
        char[] ans = dominoes.toCharArray();
        for (int i = 0; i < idx; i++) {
            int leftPtr = symbolIdx[i], rightPtr = symbolIdx[i+1];
            char leftSymbol = symbol[i], rightSymbol = symbol[i+1];
            if (leftSymbol == rightSymbol) {
                for (int j = leftPtr + 1; j < rightPtr; j++) {
                    ans[j] = leftSymbol;
                }
            } else if (leftSymbol > rightSymbol) {
                for (int j = leftPtr + 1; j < rightPtr; j++) {
                    if (j - leftPtr == rightPtr - j) {
                        ans[j] = '.';
                    } else if (j - leftPtr > rightPtr - j) {
                        ans[j] = 'L';
                    } else {
                        ans[j] = 'R';
                    }
                }
            }
        }
        return new String(ans);
    }
}
