package leetcode.array;

/**
 * @author yuzhang
 * @date 2020/7/15 8:37 上午
 * leetcode27：移除元素
 */
public class Ex27 {
    public static void main(String[] args) {
        Ex27 ex27=new Ex27();
        int[] nums={3,2,2,3};
        System.out.println(ex27.removeElement(nums,3));
    }
    public int removeElement(int[] nums, int val) {
        if (nums==null||nums.length==0){
            return 0;
        }
        int leftPtr=0,rightPtr=nums.length-1;
        int length=0;
        while(leftPtr<=rightPtr){
            // rightPtr从右边开始，指向遇到的第一个val
            while(rightPtr>0&&nums[rightPtr]==val){
                rightPtr--;
            }
            // leftPtr从左边开始，指向遇到的第一个非val
            while(leftPtr<nums.length&&nums[leftPtr]!=val){
                leftPtr++;
                length++;
            }
            if (leftPtr>=rightPtr){
                break;
            }
            int tmp=nums[leftPtr];
            nums[leftPtr]=nums[rightPtr];
            nums[rightPtr]=tmp;
            length++;
            leftPtr++;
            rightPtr--;
        }
        return length;
    }
}
