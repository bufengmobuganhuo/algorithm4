package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/8/20 10:59 上午
 * TODO
 */
public class Ex17 {
    public static void main(String[] args) {
        String digits="23";
        Ex17 ex17=new Ex17();
        System.out.println(Arrays.toString(ex17.letterCombinations(digits).toArray()));
    }
    private List<String> ans;
    private List<Character>[] numToCharMap;

    public Ex17() {
        numToCharMap=buildArray();
    }

    public List<String> letterCombinations(String digits) {
        if (digits==null||digits.length()==0){
            return new ArrayList<>();
        }
        ans=new ArrayList<>();
        backtracking(digits,0,new StringBuilder());
        return ans;
    }

    private void backtracking(String digits,int index, StringBuilder track){
        if (track.length()==digits.length()){
            ans.add(track.toString());
            return;
        }
        int chrIdx=digits.charAt(index)-'0';
        for (Character chr : numToCharMap[chrIdx]) {
            // 做选择
            track.append(chr);
            backtracking(digits,index+1,track);
            // 撤销选择
            track.deleteCharAt(track.length()-1);
        }
    }

    private List <Character>[] buildArray(){
        List<Character>[] arr=new List[10];
        char chr='a';
        for (int i = 2; i < 10; i++) {
            arr[i]= new LinkedList<>();
            int chrNum=i==7||i==9?4:3;
            for (int j = 0; j < chrNum; j++) {
                arr[i].add(chr++);
            }
        }
        return arr;
    }
}
