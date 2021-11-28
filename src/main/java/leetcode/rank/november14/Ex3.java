package leetcode.rank.november14;

/**
 * @author yuzhang
 * @date 2021/11/14 11:28 上午
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        String encodedText = "a  b";
        Ex3 ex3 = new Ex3();
        System.out.println(ex3.decodeCiphertext(encodedText, 2));
    }
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) {
            return encodedText;
        }
        int cols = encodedText.length() / rows;
        int idx = 0;
        String[] matrix = new String[rows];
        int len = 0;
        while (idx < rows) {
            matrix[idx] = encodedText.substring(len, len + cols);
            idx++;
            len += cols;
        }
        StringBuilder res = new StringBuilder();
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows && i + j < cols; i++) {
                res.append(matrix[i].charAt(i + j));
            }
        }
        while (res.length() > 0 && res.charAt(res.length() - 1) == ' ') {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }
}
