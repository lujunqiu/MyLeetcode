/**
 * Created by lujunqiu on 17/8/20.
 * Description:
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s.
 * If there isn't one, return 0 instead.
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 */
public class Test209_Minimum_Size_Subarray_Sum {
    /*
    我们要找的是连续的子数组之和大于某个值,首先想到的就是滑动窗口概念.
    滑动窗口内的数值之和如果小于特定值的话,就往窗口内加数字,扩大窗口大小.如果窗口内数值之和大于特定值之后每次添加进新的数值都要尝试缩小窗口大小.
    也就是说,我们在遍历一边数组的过程中,我们找到了以数组内的每个数字为结尾的最小的滑动窗口的大小,最后我们找到最小的窗口大小即可.
     */
    static public int minSubArrayLen(int s, int[] nums) {
        int start = 0;
        int end = 0;
        int length = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] < s) {
                sum = sum + nums[i];
                end++;
                continue;
            }

            sum = sum + nums[i];
            end++;
            while (sum - nums[start] >= s) {
                sum = sum - nums[start];
                start++;
            }

            if (length > end - start) {
                length = end - start;
            }
        }
        if (sum < s) {
            return 0;
        }
        return length;
    }

    public static void main(String[] args) {
//        int[] a = {2, 1, 3, 4, 5};
        int[] a = {2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(7, a));
    }
}
