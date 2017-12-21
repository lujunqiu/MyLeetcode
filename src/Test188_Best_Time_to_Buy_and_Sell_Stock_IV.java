import java.util.Arrays;

/**
 * Created by lujunqiu on 2017/12/21.
 * Description:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.

 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class Test188_Best_Time_to_Buy_and_Sell_Stock_IV {
    /**
     参考我写的博客：https://lujunqiu.github.io/2017/12/16/股票中的动态规划/
     */
    public int maxProfit(int k1, int[] prices) {
        if (k1 == 0) {
            return 0;
        }

        if (k1 >= prices.length >>> 1) {//相当于k等于无穷大的情况，就是leetcode_122题的情况
            int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;

            for (int price : prices) {
                int T_ik0_old = T_ik0;
                T_ik0 = Math.max(T_ik0, T_ik1 + price);
                T_ik1 = Math.max(T_ik1, T_ik0_old - price);
            }

            return T_ik0;
        }

        int[] T_ik0 =new int[k1+1];
        int[] T_ik1 =new int[k1+1];
        Arrays.fill(T_ik1, Integer.MIN_VALUE);

        for (int price : prices) {
            //类似背包问题的简化写法，从后往前遍历！
            for (int k = 1 ; k <= k1; k++) {
                T_ik0[k] = T_ik0[k] > T_ik1[k] + price ? T_ik0[k] : T_ik1[k] + price;
                T_ik1[k] = T_ik1[k] > T_ik0[k - 1] - price ? T_ik1[k] : T_ik0[k - 1] - price;

            }
        }
        return T_ik0[k1];
    }
}
