/**
 * Created by lujunqiu on 2017/12/21.
 * Description:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

 * Example:
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 */
public class Test309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {
    /**
     参考我写的博客：https://lujunqiu.github.io/2017/12/16/股票中的动态规划/
     */
    public int maxProfit(int[] prices) {
        int T_ik0 = 0;
        int T_ik1 = Integer.MIN_VALUE;
        int pre_T_ik0 = 0;

        for (int price : prices) {
            int temp = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, pre_T_ik0 - price);
            pre_T_ik0 = temp;
        }
        return T_ik0;
    }
}
