package leetcode.recursive;

/**
 * @author yuzhang
 * @date 2021/3/22 下午2:11
 * TODO
 */
public class Ex794_2 {
    public static void main(String[] args) {
        Ex794_2 ex794_2 = new Ex794_2();
        String[] board = {"XXO", "XOX", "OXO"};
        System.out.println(ex794_2.validTicTacToe(board));
    }

    public boolean validTicTacToe(String[] board) {
        int xcount = 0;
        int ocount = 0;
        for (String s : board) {
            for (int j = 0; j < 3; j++) {
                if (s.charAt(j) == 'X') {
                    xcount++;
                } else if (s.charAt(j) == 'O') {
                    ocount++;
                }
            }
        }
        if (ocount > xcount) {
            return false;
        }
        if (Math.abs(xcount - ocount) > 1) {
            return false;
        }
        if (win(board, 'X') && xcount - ocount != 1) {
            return false;
        }
        if (win(board, 'O') && xcount - ocount != 0) {
            return false;
        }
        return true;
    }

    private boolean win(String[] board, char chr) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == chr && board[i].charAt(1) == chr && board[i].charAt(2) == chr) {
                return true;
            }
            if (board[0].charAt(i) == chr && board[1].charAt(i) == chr && board[2].charAt(i) == chr) {
                return true;
            }
        }
        if (board[0].charAt(0) == chr && board[1].charAt(1) == chr && board[2].charAt(2) == chr) {
            return true;
        }
        if (board[0].charAt(2) == chr && board[1].charAt(1) == chr && board[2].charAt(0) == chr) {
            return true;
        }
        return false;
    }
}
