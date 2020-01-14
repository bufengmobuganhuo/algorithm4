package chapter1_Fundamentals.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ArrayUtil {
    /**
     * @return 生成不重复的数组(有正有负)
     */
    public static int[] create(int length,int max){
        if (length>max){
            return null;
        }
        Set<Integer> set=new HashSet<Integer>(length);
        int size=0;
        Random random=new Random();
        int[] res=new int[length];
        while (size<length){
            int item=(Math.random()>0.5?-1:1)*random.nextInt(max);
            if (!set.contains(item)){
                set.add(item);
                res[size]=item;
                size++;
            }
        }
        return res;
    }
}
