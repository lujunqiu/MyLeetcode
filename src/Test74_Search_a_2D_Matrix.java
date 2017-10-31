/**
 * Created by lujunqiu on 17/10/31.
 * Description:
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 * Consider the following matrix:
 * [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 */
public class Test74_Search_a_2D_Matrix {
    public static void main(String[] args) {
        Test74_Search_a_2D_Matrix test = new Test74_Search_a_2D_Matrix();
        int[][] t1 = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        int[][] t2 = new int[][]{{1}};
        int[][] t3 = new int[][]{{1, 3}};
        System.out.println(test.searchMatrix(t1, 3));
        System.out.println(test.searchMatrix(t2, 2));
        System.out.println(test.searchMatrix(t3, 3));
    }

    /**
     * 分为2步来查找目标值,首先确定目标值可能所在的行,然后对应该行查找目标值
     * 第一步二分查找确定目标值可能在的二维数组的哪一行,利用Leetcode35的方法,如果查找成功返回下标,如果没找到返回应该插入的位置,最后判断即可.
     * 第二步二分查找确定目标值可能所在的行之后,利用最简单的二分查找法,查找对应行即可,找到返回true,未找到返回false.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int low = 0;
        int high = matrix.length - 1;
        //边界条件判断
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (matrix[mid][0] < target) {
                low = mid + 1;
            }
            if (matrix[mid][0] >= target) {
                high = mid - 1;
            }
        }
        //利用low的值,来判断目标值可能所在的行
        if (low == matrix.length) {
            return binarySearch(matrix[low - 1], target);
        }
        if (matrix[low][0] == target) {
            return true;
        }
        if (low == 0) {
            return false;
        } else {
            return binarySearch(matrix[low - 1], target);
        }
    }

    /**
     * 经典的二分查找法,找到返回true,没找到返回false
     */
    private boolean binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return false;
    }
}
