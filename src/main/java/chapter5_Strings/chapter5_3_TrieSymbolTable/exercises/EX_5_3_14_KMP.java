package chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

/**
 * @author zhangyu
 * 2020/6/12 17:04
 * TODO
 */
public class EX_5_3_14_KMP {
    public static void main(String[] args) {
        String txt="ababababca";
        String pattern="abababcah";
        EX_5_3_14_KMP kmp=new EX_5_3_14_KMP(pattern.toCharArray());
        System.out.println(kmp.search(txt.toCharArray()));
    }
    private char[] pattern;
    private int[] next;

    public EX_5_3_14_KMP(char[] pattern) {
        this.pattern = pattern;
        generateNext();
    }

    public int search(char[] txt){
        int i=0,j=0;
        while (i<txt.length&&j<pattern.length){
            if (j==-1||txt[i]==pattern[j]){
                i++;
                j++;
            }else{
                j=next[j];
            }
        }
        if (j==pattern.length){
            return i-j;
        }else{
            return txt.length;
        }
    }

    private void generateNext(){
        next=new int[pattern.length];
        next[0]=-1;
        int i=0,j=-1;
        while (i<pattern.length-1){
            if (j==-1||pattern[j]==pattern[i]){
                i++;
                j++;
                next[i]=j;
            }else{
                j=next[j];
            }
        }
    }
}
