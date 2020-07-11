package leetcode.stack.monotonic;

import com.sun.org.apache.xalan.internal.lib.ExsltBase;
import org.omg.CORBA.INTERNAL;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/7/5 6:39 下午
 * leetcode739: 每日温度
 * 单调栈
 */
public class Ex739 {
    public static void main(String[] args) {
        int[] T={89,62,70,58,47,47,46,76,100,70};
        Ex739 ex739=new Ex739();
        System.out.println(Arrays.toString(ex739.dailyTemperatures(T)));
    }
    public int[] dailyTemperatures(int[] T) {
        if (T==null){
            return null;
        }
        int[] res=new int[T.length];
        // 栈底 -> 栈顶：递减
        Stack<TemperatureInfo> stack=new Stack<>();
        for (int i = 0; i < T.length; i++) {
            // 后面的要比栈顶元素大，说明就是更高的温度
            while(!stack.isEmpty()&&T[i]>=stack.peek().temperature){
                TemperatureInfo temperatureInfo = stack.pop();
                res[temperatureInfo.index]=i-temperatureInfo.index;
            }
            stack.push(new TemperatureInfo(T[i],i));
        }
        return res;
    }
    static class TemperatureInfo{
        int temperature;
        int index;

        public TemperatureInfo(int temperature, int index) {
            this.temperature = temperature;
            this.index = index;
        }
    }
}
