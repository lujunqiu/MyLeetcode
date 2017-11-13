/**
 * Created by lujunqiu on 17/11/13.
 * Description:
 * Given an array of integers nums, write a method that returns the "pivot" index of this array.
 * We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the
 * right of the index.
 * If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.

 * Example 1:
 * Input:
 * nums = [1, 7, 3, 6, 5, 6]
 * Output: 3
 * Explanation:
 * The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
 * Also, 3 is the first index where this occurs.
 * Example 2:
 * Input:
 * nums = [1, 2, 3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 */
public class Test724_Find_Pivot_Index {
    /**
     首先得到数组的和,然后遍历数组,判断每个元素是否能成为pivot
     */
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }

        int temp = 0;

        if (sum - nums[0] == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            temp = temp + nums[i];
            if (temp == sum - nums[i + 1] - temp) {
                return i + 1;
            }
        }

        if (sum - nums[nums.length - 1] == 0) {
            return nums.length - 1;
        }
        return -1;
    }
}
