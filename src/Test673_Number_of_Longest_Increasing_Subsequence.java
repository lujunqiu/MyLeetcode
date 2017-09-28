/**
 * Created by lujunqiu on 17/9/27.
 * Description:
 * Given an unsorted array of integers, find the number of longest increasing subsequence.
 * Example 1:
   Input: [1,3,5,4,7]
   Output: 2
   Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 */
public class Test673_Number_of_Longest_Increasing_Subsequence {
    /*
    状态定义:
        dp[i]表示已nums[i]结尾的最长升序子序列的长度;
        c[i]表示已nums[i]结尾的最长升序子序列的个数;
    length记录最长的升序子序列的长度,最后遍历dp[]数组找到所有最长的升序列子序列对应的c[i],求和即可.
     */
    static public int findNumberOfLIS(int[] nums) {
        int length = 1;
        int[] c = new int[nums.length];
        int[] dp = new int[nums.length];
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            c[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {//注意:以下2个if条件语句顺序不能改变
                    if (dp[i] == dp[j] + 1) {
                        c[i] += c[j];
                    }
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        c[i] = c[j];
                    }
                    length = length > dp[i] ? length : dp[i];
                }
            }
        }
        //统计结果
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == length) {
                result += c[i];
            }
        }
        return result;
    }

    /*
    补充说明一个技巧:如何只遍历一次数组的情况下,找到数组中最大值出现次数
     */
    static private int trick(int[] nums) {
        int count = 0;
        int temp = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (temp == nums[i]) {//先判断是否符合累加条件
                count++;
            }
            if (temp < nums[i]) {//再赋值
                temp = nums[i];
                count = 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println(findNumberOfLIS(new int[]{2, 2, 2, 2, 2}));
    }
}
