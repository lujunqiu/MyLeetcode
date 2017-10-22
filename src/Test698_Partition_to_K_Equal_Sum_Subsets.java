import java.util.Arrays;

/**
 * Created by lujunqiu on 17/10/20.
 * Description:
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose
 * sums are all equal.
 * Example 1:
   Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
   Output: True
   Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 */
public class Test698_Partition_to_K_Equal_Sum_Subsets {
    /**
     首先,根据题目输入计算出子集合的和(目标值),然后给数组排序,从尾部开始如果数值大于目标值,则输出false.如果等于就相当于找到了一个只有一个元素的子集合.
     在预处理之后,还是在排序之后的数组从后往前遍历,初始化k个子集合(题目所给的数值减去预处理时候得到的只有一个元素的子集合个数),尝试将数组中的每个数放入k个子集合中,只要这k个子集合的和不超过目标值即可.
     最后,如果数组中的所有元素都能放入这k个集合中,且子集合的和不超过目标值(根据数学推导,这时肯定等于目标值),那么就能够满足题意.否则输出false.
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        int subSetSum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % k == 0) {
            subSetSum = sum / k;
        } else {
            return false;
        }
        int iterator = nums.length - 1;
        Arrays.sort(nums);
        if (nums[iterator] > subSetSum) {
            return false;
        }
        while (iterator >= 0 && nums[iterator] == subSetSum) {
            iterator--;
            k--;
        }

        return ifFind(new int[k], iterator, nums, subSetSum);


    }

    /**
     * 深度优先搜索,尝试将数组中的元素放入k个子集合中
     * @param subSet
     * @param iterator
     * @param nums
     * @param subSetSum
     * @return
     */
    private boolean ifFind(int[] subSet, int iterator, int[] nums, int subSetSum) {
        if (iterator < 0) {
            return true;
        }
        int t = nums[iterator];
        iterator--;
        for (int i = 0; i < subSet.length; i++) {
            if (subSet[i] + t <= subSetSum) {
                subSet[i] = subSet[i] + t;
                if (ifFind(subSet, iterator, nums, subSetSum)) {
                    return true;
                }
                subSet[i] = subSet[i] - t;//深度搜索回退一步,在上一步之后继续搜索
            }
        }
        return false;
    }



    public static void main(String[] args) {
        Test698_Partition_to_K_Equal_Sum_Subsets test698_partition_to_k_equal_sum_subsets = new Test698_Partition_to_K_Equal_Sum_Subsets();
        System.out.println(test698_partition_to_k_equal_sum_subsets.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        System.out.println(test698_partition_to_k_equal_sum_subsets.canPartitionKSubsets(new int[]{5,2,5,5,5,5,5,5,5,5,5,5,5,5,5,3}, 15));
    }
}
