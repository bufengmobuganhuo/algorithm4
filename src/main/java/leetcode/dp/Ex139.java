package leetcode.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2020/9/1 8:24 下午
 * 1. 提取状态：令F(0,i)=字符串从0～i是否可以被拆分
 * 2. 状态转移方程：F(0,i)=F(0,j) && s(j+1,i)是否包含在字典中 ,其中j取[0,i),只要在j中有一次为true，则F(0,i)=true
 */
public class Ex139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDic = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0]=true;
        for (int i = 1; i < s.length()+1; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDic.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
