package leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex838_2 {
    public String pushDominoes(String dominoes) {
        if (dominoes == null || dominoes.length() == 0) {
            return null;
        }
        int len = dominoes.length();
        // 记录symbol出现的位置
        int[] symbolIdx = new int[len + 2];
        // 记录出现的symbol
        char[] symbols = new char[len + 2];
        // 统计symbol出现的位置，将其分段，哨兵
        symbols[0] = 'L';
        symbolIdx[0] = -1;
        int idx = 1;
        for (int i = 0; i < len; i++) {
            if (dominoes.charAt(i) != '.') {
                symbols[idx] = dominoes.charAt(i);
                symbolIdx[idx++] = i;
            }
        }
        // 哨兵
        symbols[idx] = 'R';
        symbolIdx[idx] = len;
        char[] ans = dominoes.toCharArray();
        for (int i = 0; i < idx - 1; i++) {
            int leftPtr = symbolIdx[i], rightPtr = symbolIdx[i + 1];
            char leftSymbol = symbols[i], rightSymbol = symbols[i + 1];
            // 如果是LLLLL或RRRRR
            if (leftSymbol == rightSymbol) {
                for (int j = leftPtr + 1; j < rightPtr; j++) {
                    ans[j] = leftSymbol;
                }
                // 如果是R...L
            }else if (leftSymbol < rightSymbol) {
                for (int j = leftPtr + 1; j < rightPtr; j++) {
                    // 第j个位置距离两边都相等
                    if (j - leftPtr == j - rightPtr){
                        ans[j] = '.';
                        // 第j个位置靠近左边
                    }else if (j - leftPtr < j - rightPtr){
                        ans[j] = 'R';
                    }else {
                        ans[j] = 'L';
                    }
                }
            }
        }
        return String.valueOf(ans);
    }
}
