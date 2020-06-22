package chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/6/15 9:31 下午
 * TODO
 */
public class EX_5_3_26_BM {
    public static void main(String[] args) {
        String txt1="example";
        String txt2="ampleex";
        EX_5_3_26_BM bm=new EX_5_3_26_BM();
        System.out.println(bm.solution(txt1,txt2));
    }
    private Map<Character,Integer> right;

    public EX_5_3_26_BM(){
        right=new HashMap<>();
    }

    public boolean solution(String txt1,String txt2){
        return txt1.length()==txt2.length()&&contains(txt1+txt1,txt2);
    }

    private boolean contains(String txt,String pattern){
        right.clear();
        for (int i = 0; i < txt.length(); i++) {
            right.put(txt.charAt(i),i);
        }
        int skipStep=0;
        for (int i = 0; i <= txt.length()-pattern.length(); i++) {
            skipStep=0;
            for (int j = pattern.length()-1; j >= 0; j--) {
                if (pattern.charAt(j)!=txt.charAt(i+j)){
                    skipStep=j-right.getOrDefault(txt.charAt(i+j),-1);
                    skipStep=Math.max(1,skipStep);
                    break;
                }
            }
            if (skipStep==0){
                return true;
            }
        }
        return false;
    }
}
