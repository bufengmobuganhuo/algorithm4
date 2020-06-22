package chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

/**
 * @author zhangyu
 * 2020/6/11 11:15
 * TODO
 */
public class EX_5_3_4 {
    public static void main(String[] args) {
        String txt="xsaf      wterg";
        EX_5_3_4 ex_5_3_4=new EX_5_3_4();
        System.out.println(ex_5_3_4.solution(txt,5));
    }
    private int[] next;
    private String pattern;

    public int solution(String txt,int M){
        if (txt==null||txt.length()==0||M<=0){
            return -1;
        }
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < M; i++) {
            stringBuilder.append(" ");
        }
        pattern=stringBuilder.toString();
        return search(txt);
    }

    private int search(String txt){
        generateNext(pattern);
        int i=0,j=0;
        while (i<txt.length()&&j<pattern.length()){
            if (j==-1||txt.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
                //第一次出现的位置
                if (j==pattern.length()){
                    return i-j;
                }
            }else{
                j=next[j];
            }
        }
        return txt.length();
    }

    private void generateNext(String pattern){
        next=new int[pattern.length()];
        next[0]=-1;
        int i=0,j=-1;
        while (i<pattern.length()-1){
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
