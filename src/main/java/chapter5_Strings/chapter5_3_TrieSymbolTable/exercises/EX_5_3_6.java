package chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyu
 * 2020/6/11 11:32
 * TODO
 */
public class EX_5_3_6 {
    public static void main(String[] args) {
        EX_5_3_6 ex_5_3_6=new EX_5_3_6("ACA");
        String txt="ABRACADABRA";
        System.out.println(ex_5_3_6.search(txt));
    }
    private Map<Character,Integer> right;
    private String pattern;

    public EX_5_3_6(String pattern) {
        this.pattern = pattern;
        right=new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            right.put(pattern.charAt(i),i);
        }
    }

    public int search(String txt){
        int skipStep=0;
        for (int i = 0; i < txt.length() - pattern.length(); i+=skipStep) {
            skipStep=0;
            for (int j = pattern.length()-1; j >= 0; j--) {
                if (txt.charAt(i+j)!=pattern.charAt(j)){
                    skipStep=j-right.getOrDefault(txt.charAt(i+j),-1);
                    skipStep=skipStep<1?1:skipStep;
                    break;
                }
            }
            if (skipStep==0){
                return i;
            }
        }
        return -1;
    }
}
