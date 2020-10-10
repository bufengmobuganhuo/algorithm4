package leetcode.recursive;

/**
 * @author yuzhang
 * @date 2020/10/10 10:20 上午
 * leetcode794：第一次尝试
 */
public class Ex794_1 {
    public boolean validTicTacToe(String[] board) {
        int xcount = 0, ocount = 0;
        char x = 'X', o = 'O';
        // 计算x和o出现的次数
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == x) {
                    xcount++;
                } else if (board[i].charAt(j) == o) {
                    ocount++;
                }
            }
        }
        // 如果二者不相同，则一定是x获胜了，那么二者一定相差1
        if (xcount != ocount && xcount - ocount != 1) {
            return false;
        }
        // 如果是x获胜，则x一定比o的个数多1
        if (win(board, x) && xcount - ocount != 1) {
            return false;
        }
        // 如果是o获胜，则x和o一定相等
        if (win(board,o)&&xcount!=ocount){
            return false;
        }
        return true;
    }

    private boolean win(String[] board, char chr) {
        for (int i = 0; i < 3; i++) {
            // 校验行
            if (board[i].charAt(0)==chr&&board[i].charAt(1)==chr&&board[i].charAt(2)==chr){
                return true;
            }
            // 校验列
            if (board[0].charAt(i)==chr&&board[1].charAt(i)==chr&&board[2].charAt(i)==chr){
                return true;
            }
        }
        // 校验对角线
        if (board[0].charAt(0)==chr&&board[1].charAt(1)==chr&&board[2].charAt(2)==chr){
            return true;
        }
        if (board[2].charAt(0)==chr&&board[1].charAt(1)==chr&&board[0].charAt(2)==chr){
            return true;
        }
        return false;
    }
}
