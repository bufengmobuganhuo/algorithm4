package leetcode.greedy;

/**
 * @author yuzhang
 * @date 2021/1/12 下午5:02
 * TODO
 */
public class Ex948 {
    public static void main(String[] args) {
        Ex948 ex948 = new Ex948();
        int[] tokens = {100, 200};
        System.out.println(ex948.bagOfTokensScore(tokens, 150));
    }

    private int[] aux;

    public int bagOfTokensScore(int[] tokens, int P) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        aux = new int[tokens.length];
        sort(tokens, 0, tokens.length - 1);
        int leftPtr = 0, rightPtr = tokens.length - 1, point = 0;
        while (leftPtr <= rightPtr) {
            // 如果能量足够，则优先换成分数，并且优先使用小能量换分数（前面已排序）
            if (P >= tokens[leftPtr]) {
                point++;
                P -= tokens[leftPtr++];
                // 如果能够不够，则用分数换能量,同时优先换大的能量（前面已排序）
            } else if (P < tokens[leftPtr] && point > 0 && rightPtr - leftPtr > 2) {
                point--;
                P += tokens[rightPtr--];
            } else {
                break;
            }
        }
        return point;
    }

    private void sort(int[] tokens, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(tokens, start, mid);
        sort(tokens, mid + 1, end);
        merge(tokens, aux, start, mid, end);
    }

    private void merge(int[] tokens, int[] aux, int start, int mid, int end) {
        int i = start, j = mid + 1;
        System.arraycopy(tokens, start, aux, start, end - start + 1);
        for (int k = start; k < end + 1; k++) {
            if (j > end) {
                tokens[k] = aux[i++];
            } else if (i > mid) {
                tokens[k] = aux[j++];
            } else if (aux[i] < aux[j]) {
                tokens[k] = aux[i++];
            } else {
                tokens[k] = aux[j++];
            }
        }
    }

}
