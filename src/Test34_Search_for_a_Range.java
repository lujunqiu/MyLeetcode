/**
 * Created by lujunqiu on 17/10/25.
 * Description:
 * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */
public class Test34_Search_for_a_Range {
    /**
     简单的理解,我们需要完成2个变种的二分查找方法:
     第一种二分查找:找到最小的i,使用nums[i]=key,若不存在返回-1
     第二种二分查找:找到最大的i,使用nums[i]=key,若不存在返回-1
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int[] result = new int[2];
        result[0] = binary_search1(nums, nums.length, target);
        result[1] = binary_search2(nums, nums.length, target);
        return result;
    }

    int binary_search1(int a[], int n, int key) {
        int mid, left = 0, right = n - 1;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (a[right] == key) {
            return right;
        }
        return -1;
    }

    int binary_search2(int a[], int n, int key) {
        int mid, left = 0, right = n - 1;
        while (left < right) {
            mid = left + ((right + 1 - left) >> 1);
            if (a[mid] <= key) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (a[left] == key) {
            return left;
        }
        return -1;
    }
}
