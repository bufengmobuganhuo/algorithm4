package com.mengyu.algs4.exercise.leetcode.string;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/8/23 8:59 上午
 * 解题方法：
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/javashi-xian-cong-bao-li-fa-dao-hua-dong-chuang-ko/
 */
public class Ex30 {
    public static void main(String[] args) {
        String[] words = {"foo", "bar"};
        String s = "foobarfoobar";
        Ex30 ex30 = new Ex30();
        System.out.println(Arrays.toString(ex30.findSubstring(s, words).toArray()));
    }
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res=new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return res;
        }
        // 数组中所有单词的出现次数
        Map<String, Integer> allWords = collect(words);
        int wordLen=words[0].length(),wordNum=words.length;
        /**
         * 将所有移动分成wordLen种情况，分别为:
         * 从words[x]的第0位向右移动，从words[x]的第1位向右移动。。。
         */
        for (int i = 0; i < wordLen; i++) {
            // 存放当前子串种匹配的单词个数
            Map<String,Integer> hasWords=new HashMap<>();
            // 匹配了多少个单词
            int count=0;
            // 有wordLen种情况，每次移动的个数都是一个单词的长度
            for (int j = i; j < s.length()-wordLen*wordNum+1; j+=wordLen) {
                // 情况3是否出现过
                boolean hasRemoved=false;
                while (count<wordNum){
                    // 当前要判断的单词
                    String curWord=s.substring(j+count*wordLen,j+(count+1)*wordLen);
                    // 如果匹配，则放入已匹配的map中
                    if (allWords.containsKey(curWord)){
                        int hasValue=hasWords.getOrDefault(curWord,0);
                        hasWords.put(curWord,++hasValue);
                        count++;
                        /**
                         * 情况3：words={bar,foo,foo},s=f o o b a r b a r f o o b a r m a n
                         * 有匹配，但是匹配的单词数量超出了words中数量（j=0开始:f o o ｜b a r｜ b a r）
                         * 此时可以删除掉前面多出来的单词(从j=0开始移除)，从j=6开始匹配
                         */
                        int allValue=allWords.get(curWord);
                        if (hasValue>allValue) {
                            hasRemoved = true;
                            // 移除了几个
                            int removeCount = 0;
                            while (allWords.get(curWord) < hasWords.get(curWord)) {
                                // 从j=0开始移除
                                String removedWord = s.substring(j + removeCount * wordLen, j + (removeCount + 1) * wordLen);
                                hasWords.put(removedWord, hasWords.get(removedWord) - 1);
                                removeCount++;
                            }
                            // 移除后，就不算到匹配次数中
                            count -= removeCount;
                            // 更新j，j在循环时还要+wordLen
                            j += (removeCount - 1) * wordLen;
                            break;
                        }
                        /**
                         * 情况2：当前单词不匹配
                         * s:b a r t h e f o o
                         * 在第二个单词时不匹配，则直接移动到下一个单词开始匹配:
                         * 此时j=0,count=1,前面已经匹配过一个了
                         */
                    } else{
                        hasWords.clear();
                        // 令j=6开始匹配，外面还要+wordLen
                        j+=count*wordLen;
                        count=0;
                        break;
                    }
                }
                if (count==wordNum){
                    res.add(j);
                }
                /**
                 * 第一种情况：匹配成功,并且情况3没有出现
                 * s : b a r |f o o f o o| f o o
                 * j=0时，匹配成功，此时||范围内的已经校验过，j可以直接从9开始
                 */
                if (count>0&&!hasRemoved){
                    // 移除匹配成功的第一个单词
                    String firstWord=s.substring(j,j+wordLen);
                    hasWords.put(firstWord,hasWords.get(firstWord)-1);
                    count--;
                }

            }
        }
        return res;
    }

    private Map<String, Integer> collect(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        return map;
    }
}
