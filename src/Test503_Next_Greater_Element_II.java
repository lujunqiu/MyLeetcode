import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by lujunqiu on 2017/12/9.
 * Description:
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every
 * element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you
 * could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

 * Example 1:
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 */
public class Test503_Next_Greater_Element_II {
    /**
     * 类比 Leetcode 739题
     * 利用单调栈(保证栈中元素是单调的)，栈中保存数组索引(比保存数值要方便很多)。
     * 题目要求是可以循坏遍历，那么我们就按照题目的要求来寻找，遍历2次原数组即可。由于循坏遍历原数组2次，需要用到 i % nums.length 。
     */
    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();//在单线程中利用双端队列作为栈的实现方法，要比Vector(线程安全，会resizing)效率高很多。
        int[] res = new int[nums.length];
        //初始化
        for (int i = 0; i < res.length; i++) {
            res[i] = -1;
        }
        //遍历2次数组元素即可，利用栈操作来模拟算法执行的过程
        for (int i = 0; i < 2 * nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % nums.length]) {
                res[stack.pop()] = nums[i % nums.length];
            }
            stack.push(i % nums.length);
        }

        return res;
    }
}
