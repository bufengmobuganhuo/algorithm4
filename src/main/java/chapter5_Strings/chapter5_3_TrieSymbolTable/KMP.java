package chapter5_Strings.chapter5_3_TrieSymbolTable;

/**
 * @author zhangyu
 * 2020/6/9 10:45
 *
 */
public class KMP {
    public static void main(String[] args) {
        String target="ababababca";
        String pattern="abababca";
        KMP kmp=new KMP();
        System.out.println(kmp.search(target,pattern));
    }
    private int[] next;

    public int search(String target, String pattern){
        generateNext(pattern);
        int i=0,j=0;
        while (i<target.length()&&j<pattern.length()){
            if (j==-1||target.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
                /**
                 * 如果不匹配，则将j回退，回退就是因为txt[i-j+1~i-1]和pat[0~j-1]相同
                 * 然后根据next[j]的含义：长度为j的字符串的最长前后缀长度为next[j]，
                 * 用pat[0~j-1]的前缀和txt[i-j+1~i-1](这个字符串=pat[0~j-1])的后缀匹配
                 * */
            }else{
                j=next[j];
            }
        }
        if (j==pattern.length()){
            return i-j;
        }else{
            return -1;
        }
    }

    private void generateNext(String pat){
        next=new int[pat.length()];
        next[0]=-1;
        int i=0,j=-1;
        while (i<pat.length()-1){
            //next[i]表示长度为i的最长前后缀重合长度
            if (j==-1||pat.charAt(i)==pat.charAt(j)){
                i++;
                j++;
                //当pat[i]=pat[j]时，next[i+1]=j+1，i+1是长度
                next[i]=j;
            }else{
                /**
                 * 如果不匹配，则将j回退，回退就是因为txt[i-j+1~i-1]和pat[0~j-1]相同
                 * 然后根据next[j]的含义：长度为j的字符串的最长前后缀长度为next[j]，
                 * 用pat[0~j-1]的前缀和txt[i-j+1~i-1](这个字符串=pat[0~j-1])的后缀匹配
                 * */
                j=next[j];
            }
        }
    }
}
