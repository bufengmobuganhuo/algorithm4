package chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

/**
 * @author yuzhang
 * @date 2020/6/15 9:18 下午
 * TODO
 */
public class EX_5_3_26_KMP {
    public static void main(String[] args) {
        String txt1="example";
        String txt2="ampleew";
        EX_5_3_26_KMP kmp=new EX_5_3_26_KMP();
        System.out.println(kmp.solution(txt1,txt2));
    }

    private int[] next;

    public boolean solution(String txt1,String txt2){
        return txt1.length()==txt2.length()&&contains(txt1+txt1,txt2);
    }

    private boolean contains(String txt,String pattern){
        generateNext(pattern);
        int i=0,j=0;
        while(i<txt.length()&&j<pattern.length()){
            if (j==-1||txt.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
            }else{
                j=next[j];
            }
        }
        return j==pattern.length();
    }

    private void generateNext(String pattern){
        next=new int[pattern.length()];
        next[0]=-1;
        int i=0,j=-1;
        while(i<pattern.length()-1){
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
