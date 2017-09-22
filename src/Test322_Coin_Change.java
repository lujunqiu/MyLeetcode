/**
 * Created by lujunqiu on 17/9/22.
 * Description:
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of
 * coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 * You may assume that you have an infinite number of each kind of coin.
 */
public class Test322_Coin_Change {
    /*
    有点类似背包问题中的完全背包问题.
    动态规划:
    状态定义dp[i]:价值为i的时候,需要最少的硬币数量
    状态转移:dp[i] = min{dp[i-conis[k]]} + 1  0<=k<coins.length
    注意我们在初始化的时候,dp[0]=0,表明价值为0的最少数量为0,符合题意;dp数组的其他值初始化为最大值,是为了在状态转移的时候方便求最小值.
    在结束之后,dp数组中的数若还有为最大值的,则表明在该状态下无法满足题意,找不到解.
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i -coin] != Integer.MAX_VALUE) {//只在可行解中求最小值
                    dp[i] = dp[i] < dp[i - coin] + 1 ? dp[i] : dp[i - coin] + 1;
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
