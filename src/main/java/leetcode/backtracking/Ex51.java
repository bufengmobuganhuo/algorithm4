package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/7/27 6:28 下午
 * TODO
 */
public class Ex51 {
    public static void main(String[] args) {
        Ex51 ex51 =new Ex51();
        List<List<String>> res= ex51.solveNQueens(4);
        for (List<String> list :
                res) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
    private List<List<String>> ans;
    private int N;
    public List<List<String>> solveNQueens(int n) {
        N=n;
        ans=new ArrayList<>();
        List<String> board=new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            StringBuilder stringBuilder=new StringBuilder();
            for (int j = 0; j < n; j++) {
                stringBuilder.append(".");
            }
            board.add(stringBuilder.toString());
        }
        backtracking(0,board);
        return ans;
    }

    private void backtracking(int row,List<String> board){
        if (row==board.size()){
            List<String> boardRes=new ArrayList<>(board);
            ans.add(boardRes);
            return;
        }
        for (int i = 0; i < N; i++) {
            // 做选择：只有满足皇后条件的才可以选择
            if (!isValid(board,i,row)){
                continue;
            }
            StringBuilder stringBuilder=new StringBuilder(board.get(row));
            stringBuilder.setCharAt(i,'Q');
            board.set(row,stringBuilder.toString());
            backtracking(row+1,board);
            stringBuilder.setCharAt(i,'.');
            board.set(row,stringBuilder.toString());
        }
    }

    private boolean isValid(List<String> board,int col,int row){
        // 检查列
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col)=='Q'){
                return false;
            }
        }
        // 检查行
        for (int i = 0; i < N; i++) {
            if (board.get(row).charAt(i)=='Q'){
                return false;
            }
        }
        // 检查左上方斜线
        for (int i = row-1,j=col-1; i >=0 &&j>=0 ; i--,j--) {
            if (board.get(i).charAt(j)=='Q'){
                return false;
            }
        }
        // 检查右下方
        for (int i = row+1,j=col+1; i < N&&j<N; i++,j++) {
            if (board.get(i).charAt(j)=='Q'){
                return false;
            }
        }
        // 检查左下方
        for (int i = row+1,j=col-1; i < N&&j>=0; i++,j--) {
            if (board.get(i).charAt(j)=='Q'){
                return false;
            }
        }
        // 检查右上方
        for (int i = row-1,j=col+1; i >=0&&j<N ; i--,j++) {
            if (board.get(i).charAt(j)=='Q'){
                return false;
            }
        }
        return true;
    }
}
