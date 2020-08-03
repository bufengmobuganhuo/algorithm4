package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/7/31 2:26 下午
 * TODO
 */
public class Ex131 {
    public static void main(String[] args) {
        String s="aab";
        Ex131 ex131=new Ex131();
        ex131.partition(s);
    }
    private List<List<String>> ans;
    public List<List<String>> partition(String s) {
        if (s==null||s.length()==0){
            return new ArrayList<>();
        }
        ans=new ArrayList<>();
        backtracking(s,0,new LinkedList<>());
        return ans;
    }

    private void backtracking(String s, int start, LinkedList<String> track){
        if (start==s.length()){
            List<String> partitionList=new ArrayList<>(track);
            ans.add(partitionList);
            return;
        }
        for (int i = 1; i <= s.length()-start; i++) {
            String partitionStr=s.substring(start,start+i);
            // 做选择
            if (!isPalindromeStr(partitionStr)){
                continue;
            }
            track.offer(partitionStr);
            backtracking(s,start+i,track);
            track.removeLast();
        }
    }

    private boolean isPalindromeStr(String str){
        StringBuilder stringBuilder=new StringBuilder(str);
        stringBuilder.append(str);
        return stringBuilder.toString().equals(stringBuilder.reverse().toString());
    }
}
