package chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class EX_5_3_24_KMP {
    public static void main(String[] args) {
        String target="ababababca";
        String pattern="abababca";
        EX_5_3_24_KMP kmp=new EX_5_3_24_KMP(pattern);
        System.out.println(Arrays.toString(kmp.findAll(target).toArray()));
    }
    private int[] next;
    private String pattern;

    public EX_5_3_24_KMP(String pattern){
        this.pattern=pattern;
        next=new int[pattern.length()+1];
        generateNext();
    }

    public List<Integer> findAll(String txt){
        List<Integer> res=new ArrayList<>();
        int i=0,j=0;
        while(i<txt.length()&&j<pattern.length()){
            if (j==-1||txt.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
                if (j==pattern.length()){
                    res.add(i-j);
                    j=next[j];
                }
            }else{
                j=next[j];
            }
        }
        return res;
    }

    private void generateNext(){
        next[0]=-1;
        int i=0,j=-1;
        while (i<pattern.length()){
            if (j==-1||pattern.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
                next[i]=j;
            }else{
                j=next[j];
            }
        }
    }
}
