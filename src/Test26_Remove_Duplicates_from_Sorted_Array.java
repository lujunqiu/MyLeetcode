/**
 * Created by lujunqiu on 17/3/22.
 * Description:
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * For example,
 * Given input array nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 */
public class Test26_Remove_Duplicates_from_Sorted_Array {
    /*
    这个问题属于很基础的数组处理问题,思路也很清晰.时间复杂度O(n)
    由于是已经排好序的数组,我们使用2个指针,一个记录不同元素的个数,一个遍历数组遇到相同元素就跳过.(tricks:遍历的时候灵活的运用指针(s)来记录信息.
    另外,如果是无序的数组,我们可以使用hashset来记录数组找到不同元素.如果有空间复杂度的限制,那么就先排序(推荐快排),然后做相同的处理即可.
     */
    static public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                continue;
            } else {
                nums[result] = nums[i];
                result++;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[] test = {1, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 9, 9, 9, 9, 9, 10};
        int[] test1 = {1, 1, 2};

        System.out.println(removeDuplicates(test));
    }
}
