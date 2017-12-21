/**
 * Created by lujunqiu on 2017/12/20.
 * Description:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.

 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class Test123_Best_Time_to_Buy_and_Sell_Stock_III {
    /**
     * 参考我写的博客：https://lujunqiu.github.io/2017/12/16/股票中的动态规划/
     * 动态规划
     * k==2
     */
    public int maxProfit_123(int[] prices) {
        int T_i20 = 0;
        int T_i10 = 0;
        int T_i21 = Integer.MIN_VALUE;
        int T_i11 = Integer.MIN_VALUE;

        for (int price : prices) {
            T_i20 = Math.max(T_i20, T_i21 + price);
            T_i21 = Math.max(T_i21, T_i10 - price);
            T_i10 = Math.max(T_i10, T_i11 + price);
            T_i11 = Math.max(T_i11, -price);
        }

        return T_i20;
    }
}
