import java.util.Arrays;

/**
 * Created by lujunqiu on 17/4/5.
 * Description:
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class Test31_Next_Permutation {
    /*
    主要考察的是数学里面的全排列问题,在算法叫字典序排序.
    C++的stl算法实现了next_permutation函数,这里不在数学上做太多的探讨,直接给出结论:

    对当前排列从后向前扫描，找到一对为升序的相邻元素，记为i和j（i < j）。如果不存在这样一对为升序的相邻元素，则所有排列均已找到，回到升序序列；
    否则，重新对当前排列从后向前扫描，找到第一个大于i的元素k，交换i和k，然后对从j开始到结束的子序列反转，则此时得到的新排列就为下一个字典序排列。
    这种方式实现得到的所有排列是按字典序有序的.
     */
    static public void nextPermutation(int[] nums) {
        int index = nums.length - 1;
        while (index > 0 && nums[index] <= nums[index - 1]) {//从后向前扫描，找到一对为升序的相邻元素
            --index;
        }
        if (index == 0) {//如果序列全部降序排列,那么完全升序序列就是下一个排列
            Arrays.sort(nums);
            return;
        }
        int second = Integer.MAX_VALUE, secondIndex = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= index - 1; --i) {//当前排列从后向前扫描，找到第一个大于i的元素k
            if (nums[i] > nums[index - 1]) {
                second = nums[i];
                secondIndex = i;
                break;
            }
        }
        int tmp = nums[index - 1];//交换i和k
        nums[index - 1] = nums[secondIndex];
        nums[secondIndex] = tmp;
        Arrays.sort(nums, index, nums.length);//从j开始到结束的子序列反转

    }

    public static void main(String[] args) {
        int[] nums = {6, 5, 4, 8, 7, 5, 1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
