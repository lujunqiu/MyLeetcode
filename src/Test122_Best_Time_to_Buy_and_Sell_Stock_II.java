/**
 * Created by lujunqiu on 17/4/15.
 * Description:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the
 * stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy
 * again).
 */
public class Test122_Best_Time_to_Buy_and_Sell_Stock_II {
    public static void main(String[] args) {
        int test[] = {1, 2, 3, 1, 4, 5, 6, 7};
        System.out.println(maxProfit(test));
    }

    /*
    与Test121一样,我们预处理数组,得到一个新的数组里面的值是原来数组的相邻2项的差值.
    接下来,我们只需要找到新的数组里面所有大于0的整数的和即可.
    时间复杂度为:O(n).思路很简单,官方给出的Editorial Solution也差不多,区别在于它没有预处理数组,直接遍历求解.
     */
    static public int maxProfit(int[] prices) {
        int[] subtraction = new int[prices.length];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            subtraction[i] = prices[i] - prices[i - 1];
        }
        for (int i = 0; i < subtraction.length; i++) {
            if (subtraction[i] > 0) {
                maxProfit += subtraction[i];
            }
        }
        return maxProfit;
    }
}
