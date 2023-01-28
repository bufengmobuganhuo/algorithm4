package leetcode.rank.year2023.january28;

/**
 * @author yu zhang
 */
public class Ex3 {
    /**
     * 对于某个字符串，如果全为0，则 0 | 0 = 0， 0 ^ 0 = 0，也就是说永远变不出来1，那么如果另一个字符串包含1的话，就没法变换成功
     * 所以需要2个字符串要么都不包含1（一开始就相等），要么都包含1才可以
     */
    public boolean makeStringsEqual(String s, String target) {
        return s.contains("1") == target.contains("1");
    }
}
