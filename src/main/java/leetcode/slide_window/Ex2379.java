package leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex2379 {
    public static void main(String[] args) {
        System.out.println(new Ex2379().minimumRecolors("WBBWWBBWBW", 7));
    }

    public int minimumRecolors(String blocks, int k) {
        int writeCount = 0;
        int leftPtr = 0, rightPtr = 0;
        while (rightPtr < k) {
            if (blocks.charAt(rightPtr) == 'W') {
                writeCount++;
            }
            rightPtr++;
        }
        if (writeCount == 0) {
            return 0;
        }
        int ans = writeCount;
        while (rightPtr < blocks.length()) {
            if (blocks.charAt(rightPtr) == 'W') {
                writeCount++;
            }
            if (blocks.charAt(leftPtr) == 'W') {
                writeCount--;
            }
            rightPtr++;
            leftPtr++;
            ans = Math.min(ans, writeCount);
        }
        return ans;
    }
}
