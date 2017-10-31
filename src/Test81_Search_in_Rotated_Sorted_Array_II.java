/**
 * Created by lujunqiu on 17/10/31.
 * Description:
 * Suppose an array sorted in ascending  order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Write a function to determine if a given target is in the array.
 * The array may contain duplicates.
 */
public class Test81_Search_in_Rotated_Sorted_Array_II {
    /**
     * 此题是leetcode33题的扩展题,区别在于数组中存在重复的元素
     * 对应解法思路没有改变,考虑到重复元素的存在,我们判断的时候多了可能性.
     */
    public boolean search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //考虑到重复元素的存在,我们在判断哪一边子数组是有序的时候,比没有重复元素的时候多了一种情况,满足其中任何一个条件则表明mid到high这部分子数组有序
            if (nums[mid] < nums[high] || nums[mid] < nums[low]) {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
                //同上,满足此条件表明,low到mid这段子数组有序
            } else if (nums[mid] > nums[low] || nums[mid] > nums[high]) {
                if (target < nums[mid] && target >= nums[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
                //与没有重复元素不同的在于,有可能出现这种情况nums[low] == nums[mid] == nums[high].这时候,low到mid到high这短子数组都是同样的元素,我们一步步将重复元素剔除考虑范围之外即可,通过high--或者low++均可.
            } else {
                high--;
            }
        }
        return false;
    }
}
