package leetcode.string;

/**
 * @author yuzhang
 * @date 2020/8/26 8:27 上午
 * TODO
 */
public class Ex165 {
    public static void main(String[] args) {
        System.out.println(new Ex165().compareVersion("1.05", "1.1"));
    }

    public int compareVersion(String version1, String version2) {
        String[] versions1 = version1.split("\\."), versions2 = version2.split("\\.");
        int len1 = versions1.length, len2 = versions2.length;
        for (int i = 0; i < len1 || i < len2; i++) {
            int num1 = 0, num2 = 0;
            if (i < len1) {
                num1 = Integer.parseInt(versions1[i]);
            }
            if (i < len2) {
                num2 = Integer.parseInt(versions2[i]);
            }
            if (num1 > num2) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            }
        }
        return 0;
    }
}
