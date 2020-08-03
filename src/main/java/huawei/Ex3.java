package huawei;

import java.util.Scanner;

/**
 * @author yuzhang
 * @date 2020/7/29 2:40 下午
 * 将数字转换成人民币表示
 */
public class Ex3 {
    private final static String[] NUMS={"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    private final static String[] UNITS={"亿","仟","佰","仟","拾","万","仟","佰","拾","元","角","分"};
    private final static String SUFFIX="整";
    private static String getResult(int input,int unit){
        if (unit<0||input==0){
            return "";
        }
        int num = (int) (input%10);
        input=input/10;
        String numStr=NUMS[num];
        String res="";
        if (num!=0){
            res=UNITS[unit];
        }
        return getResult(input,unit-1)+numStr+res;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String input=scanner.nextLine();
            if (input.equals("0")){
                System.out.println("人民币零元");
                continue;
            }
            String[] nums=input.split("\\.");
            String nums1=nums[0];
            int count=0;
            while(nums1.length()>1&&nums1.charAt(nums1.length()-1)=='0'){
                count++;
                nums1=nums1.substring(0,nums1.length()-1);
            }
            String res=getResult(Integer.parseInt(nums1),9-count)+(count==0?"":"元");
            res= res.replace("零零","零");
            if (nums.length>1){
                String nums2=nums[1];
                count=0;
                while(nums2.length()>0&&nums2.charAt(nums2.length()-1)=='0'){
                    count++;
                    nums2=nums2.substring(0,nums2.length()-1);
                }
                String res2=getResult(Integer.parseInt(nums2),11-count);
                res2=res2.replace("零零","零");
                res+=res2;
            }

            System.out.println("人民币"+res);
        }
    }
}
