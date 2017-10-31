/**
 * Created by lujunqiu on 17/10/31.
 * Description:
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 */
public class Test33_Search_in_Rotated_Sorted_Array {
    /**
    变种的二分查找法:因为数组只旋转了一次,那么数组的结构是确定的(由2部分构成,2部分均有序,且后部的所有值小于前部),由此我们可以改进基本的二分查找!
     */
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (nums[mid] < nums[high]) {//说明原数组中mid到high这段是有序的
                if (nums[mid] < target && target <= nums[high]) {//说明目标值在这段有序的子数组中
                    low = mid + 1;
                } else {//说明目标值在另外那段无序的子数组中
                    high = mid - 1;
                }
            } else {//同样的道理,说明原数组中low到mid这段是有序的
                if (nums[low] <= target && target < nums[mid]) {//说明目标值在这段有序的子数组中
                    high = mid - 1;
                } else {//说明目标值在另外那段无序的子数组中
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Test33_Search_in_Rotated_Sorted_Array test = new Test33_Search_in_Rotated_Sorted_Array();
        System.out.println(test.search(new int[]{4, 5, 6, 7, 1, 2, 3}, 3));
    }
}
