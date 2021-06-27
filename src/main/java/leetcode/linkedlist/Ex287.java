package leetcode.linkedlist;

/**
 * @author yu zhang
 */
public class Ex287 {

    /**
     * 1. 对于如下的数组：｜1｜3｜4｜2｜，可以实现一个映射：i -> nums[i]，最后形成一个链表，由于每个元素不重复，则形成的链表是无环的
     *  0 -> 1,
     *  1 -> 3,
     *  3 -> 2,
     *  2 -> 4
     *  0 -> 1 -> 3 -> 2 -> 4
     * 2. 对于如下的数组：｜1｜3｜4｜2｜2｜,其形成的映射如下：
     *  0 -> 1,
     *  1 -> 3,
     *  3 -> 2,
     *  2 -> 4,
     *  4 -> 2
     *  0 -> 1 -> 3 -> 2 -> 4
     *                ｜ —— ｜
     * 是一个环，并且环的入口就是重复元素
     * 3. 快慢指针，假设从起点到环的入口长度为a，环的长度L，从环的入口到相遇点长度为b，从相遇点到环的入口长度为c
     * 因为快慢指针的缘故，所以当两个指针相遇时:a+b = 2(a+b)，同时相遇时可能快指针绕了很多圈，2(a+b)=a+b+L
     * 化简后：a=kL-b=(k-1)L+L-b=(k-1)L+c，
     * 表明当慢指针从头开始走，走a步可以到达环的入口；快指针从相遇点开始走k-1圈又到达相遇点，然后再走c步到达环的入口
     */
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int slow = 0, fast = 0;
        do {
            slow=nums[slow];
            fast=nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

}
