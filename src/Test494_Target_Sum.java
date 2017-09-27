/**
 * Created by lujunqiu on 17/9/25.
 * Description:
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you
 * should choose one from + and - as its new symbol.
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
   Output: 5
   Explanation:

   -1+1+1+1+1 = 3
   +1-1+1+1+1 = 3
   +1+1-1+1+1 = 3
   +1+1+1-1+1 = 3
   +1+1+1+1-1 = 3
 */
public class Test494_Target_Sum {

    int count = 0;
    /*
    暴力搜索:不做任何解空间的优化,直接搜索全部可能的解,找到并且统计可行解的数量.
    我们利用广度搜索思想实现暴力搜索,以此设计递归函数,每个数值前的符号只有2个可能+或者-,分别计算这2种可能情况然后进入下一步递归即可,递归的退出条件直接判定和是否可行,然后统计数量.
     */
    public int BruteForce(int[] nums, int S) {
        calculate(nums, 0, 0, S);
        return count;
    }
    public void calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {
            if (sum == S)
                count++;
        } else {
            calculate(nums, i + 1, sum + nums[i], S);
            calculate(nums, i + 1, sum - nums[i], S);
        }
    }

    /*
    上述的暴力求解时间复杂度是指数级别的,我们需要优化.通过数学上的简单推导,我们可以将问题转化成另一个问题:给定的非负数组nums,选数组中数使他们的和等于目标值,这样的选法有多少种.
    数学推导:假设P为加正好的数之和,N为加负号的数之和,那么P+N=S;所有的数绝对值之和sum=P-N;所以P=(S+sum)/2.这样我们就将原问题转换了.
    转换之后的问题就是我们熟悉的背包问题了,01背包问题的变种:求装满背包或者装至某一指定体积的方案总数.
     */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (S > sum || (sum + S) % 2 == 1) {
            return 0;
        } else {
            return dp(nums, (sum + S) / 2);
        }
    }

    /**
     *  01背包问题的变种:求装满背包或者装至某一指定体积的方案总数
     *  状态:f[N][V]就是用N件物品装满体积为V背包的方案数.
     *  状态转移方程:f[i][v]=sum(f[i-1][v],f[i-i][v-c[i]]);
        前i件物品装满体积为v的背包方案数，分为2种，一种不包含第i件物品f[i-1][v]，还一种包含第i件物品f[i-i][v-c[i]]，相加即可
        初始化问题：f[0][0]=1;
     但是我们实现的时候用1维数组实现,具体可以参考"背包九讲"
     * @param ints
     * @param sum
     * @return
     */
    private int dp(int[] ints, int sum) {
        int[] dp = new int[sum + 1];
        dp[0] = 1;//将第0件物品装入体积为0的背包,只有一种方案,放入nothing
        for (int i = 0; i < ints.length; i++) {
            for (int j = sum; j - ints[i] >= 0; j--) {
                dp[j] = dp[j] + dp[j - ints[i]];
            }
        }
        return dp[sum];
    }

}
