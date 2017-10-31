/**
 * Created by lujunqiu on 17/10/23.
 * Description:
 * Your are given an array of positive integers nums.
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 */
public class Test713_Subarray_Product_Less_Than_K {
    /**
     滑动窗口法:对于数组中的每一个元素,我们找到它对应的最小的left边界,保证从left位置到该元素的窗口内所有元素乘积小于k.
     在遍历数组的过程中,我们可以按遍历顺序找到所有元素对应的left边界,一边遍历一边统计满足条件的子数组的个数(利用left边界确定的滑动窗口)
     确定了数组中所有位置的对应的left之后(确定了所有位置对应的滑动窗口大小),这时可以开始统计满足条件的子数组的个数:对于一个滑动窗口来说,
     我们可以唯一确定的是,以滑动窗口的最后一个元素结尾的满足条件的子数组个数为该元素对于的滑动窗口内元素的个数,也就是代码中的right-left+1
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) prod /= nums[left++];//找到每个right对应的滑动窗口的最小的左边界位置
            ans += right - left + 1;//统计以right元素结尾的满足乘积限制条件的子数组的个数,有点类似动态规划解题的思路
        }
        return ans;
    }
}
