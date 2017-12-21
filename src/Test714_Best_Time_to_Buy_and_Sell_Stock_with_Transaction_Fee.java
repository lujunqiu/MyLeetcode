/**
 * Created by lujunqiu on 17/11/3.
 * Description:
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative
 * integer fee representing a transaction fee.
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy
 * more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 * Return the maximum profit you can make.
 * Example 1:
     Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
     Output: 8
     Explanation: The maximum profit can be achieved by:
     Buying at prices[0] = 1
     Selling at prices[3] = 8
     Buying at prices[4] = 4
     Selling at prices[5] = 9
  The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 */
public class Test714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee {
    /**
     * 关于动态规划状态的特性:每个阶段最优的状态可以从之前的某个或某几个状态得到(最优子结构),而不管这些状态是如何得到的(后无效性)
    动态规划:
     对于每一天来说,我们设定一个状态:持有股票(Hold)可以获取的最大利润,不持有股票(notHold)可以获取的最大利润
     我们定义2个数组分别来统计每天的2的操作可以获取的最大利润.
     状态转移:从第i天到第i+1天,notHold[i+1]=max(notHold[i],Hold[i] + prices[i+1] - fee),对于第i+1天,不持有股票可以获取的最大利润有2种情况:可以第i天也不持有或者第i天持有股票第i+1天卖掉
            同理,从第i天到第i+1天,Hold[i+1]=max(notHold[i] - prices[i+1],Hold[i]),对于第i+1天,持有股票可以获取的最大利润也有2种情况:可以第i天不持有,第i+1天买入或者第i天持有,第i+1天继续持有
     我们通过对每天定义这样的状态,并列出了状态转移方程,最终的结果是最后一天不持有股票的最大利润值.
     */
    public int maxProfit_DP1(int[] prices, int fee) {
        int[] notHold = new int[prices.length];
        int[] Hold = new int[prices.length];

        notHold[0] = 0;//第一天不持有股票最大利润为0
        Hold[0] = -prices[0];//第一天持有股票最大利润为负,相当于负债购入股票

        for (int i = 1; i < prices.length; i++) {
            notHold[i] = Math.max(notHold[i - 1], Hold[i - 1] + prices[i] - fee);
            Hold[i] = Math.max(notHold[i - 1] - prices[i], Hold[i - 1]);
            //观察上面2行代码,我们可以有一个优化,不用计算完整的Hold[]数组,只计算前一天的Hold值即可,代码的修改也很简单(不再使用到Hold[]数组,用一个Hold值来代替即可),不再列举出来
        }

        return notHold[prices.length - 1];
    }

    /**
     参考我写的博客：https://lujunqiu.github.io/2017/12/16/股票中的动态规划/
     */
    public int maxProfit_DP2(int[] prices, int fee) {
        long T_ik0 = 0;
        long T_ik1 = Integer.MIN_VALUE;

        for (int price : prices) {
            long temp = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + price - fee);
            T_ik1 = Math.max(T_ik1, temp - price);
        }
        return (int)T_ik0;
    }

}
