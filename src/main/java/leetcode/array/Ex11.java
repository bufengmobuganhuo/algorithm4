package leetcode.array;

/**
 * @author yuzhang
 * @date 2020/7/15 3:35 下午
 * leetcode11:
 * 1. 取左右指针：i, j
 * 2. 面积S=min(height[i],height[j])*(j-i)
 * 3. 指针从较低指针处向对应位置移动，因为决定上述公式的是height[i]和height[j]中的较小值，如果这个较小值能变大，则得到的面积会更大
 */
public class Ex11 {
    public int maxArea(int[] height) {
        if (height==null||height.length==0){
            return 0;
        }
        int leftPtr=0,rightPtr=height.length-1;
        int res=0;
        while (leftPtr<rightPtr){
            res=height[leftPtr]<height[rightPtr]?
                    Math.max(res,(rightPtr-leftPtr)*height[leftPtr++]):
                    Math.max(res,(rightPtr-leftPtr)*height[rightPtr--]);
        }
        return res;
    }
}
