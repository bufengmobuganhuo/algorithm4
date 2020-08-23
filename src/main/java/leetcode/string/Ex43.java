package leetcode.string;

/**
 * @author yuzhang
 * @date 2020/8/21 9:18 上午
 * TODO
 */
public class Ex43 {
    public static void main(String[] args) {
        String num1="9236";
        String num2="635";
        Ex43 ex43=new Ex43();
        System.out.println(ex43.multiply(num1,num2));
    }

    public String multiply(String num1, String num2) {
        if (num1==null||num1.length()==0||num2==null||num2.length()==0){
            return null;
        }
        if ("0".equals(num1)||"0".equals(num2)){
            return "0";
        }
        int len1=num1.length(),len2=num2.length();
        // 结果的最长为len1+len2
        int[] ansArr=new int[len1+len2];
        for (int i = len1-1; i >= 0; i--) {
            int x=num1.charAt(i)-'0';
            for (int j = len2-1; j >= 0; j--) {
                int y=num2.charAt(j)-'0';
                ansArr[i+j+1]+=x*y;
            }
        }
        // 处理进位，保证ansArr中的元素<10
        for (int i = len1+len2-1; i > 0; i--) {
            ansArr[i-1]+=ansArr[i]/10;
            ansArr[i]=ansArr[i]%10;
        }
        // 拼接结果
        int index=ansArr[0]==0?1:0;
        StringBuilder res=new StringBuilder();
        while (index<len1+len2){
            res.append(ansArr[index++]);
        }
        return res.toString();
    }

}
