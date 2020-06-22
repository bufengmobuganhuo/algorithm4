package chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/6/15 9:00 下午
 * TODO
 */
public class EX_5_3_24_BM {
    public static void main(String[] args) {
        String target="ababababca";
        String pattern="abababca";
        EX_5_3_24_BM bm=new EX_5_3_24_BM(pattern);
        System.out.println(Arrays.toString(bm.findAll(target).toArray()));
    }
    private Map<Character, Integer> right;
    private String pattern;

    public EX_5_3_24_BM(String pattern){
        this.pattern=pattern;
        right=new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            right.put(pattern.charAt(i),i);
        }
    }

    public List<Integer> findAll(String txt){
        List<Integer> res=new ArrayList<>();
        int skipStep=0;
        for (int i = 0; i <= txt.length() - pattern.length(); i+=skipStep) {
            skipStep=0;
            for (int j = pattern.length()-1; j >= 0; j--) {
                if (pattern.charAt(j)!=txt.charAt(i+j)){
                    skipStep=j-right.getOrDefault(txt.charAt(i+j),-1);
                    skipStep= Math.max(skipStep, 1);
                    break;
                }
            }
            if (skipStep==0){
                res.add(i);
                skipStep=1;
            }
        }
        return res;
    }
}
