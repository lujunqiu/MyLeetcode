/**
 * Created by lujunqiu on 17/4/15.
 * Description:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the
 * maximum profit.
 * <p>
 * Example 1:
 * Input: [7, 1, 5, 3, 6, 4]
 * Output: 5
 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * <p>
 * Example 2:
 * Input: [7, 6, 4, 3, 1]
 * Output: 0
 * In this case, no transaction is done, i.e. max profit = 0.
 */
public class Test121_Best_Time_to_Buy_and_Sell_Stock {
    /*
    看到这个题的第一思路就是遍历2次数组,就可以找到问题的解,时间复杂度为O(n^2)
    但是我的做法将时间复杂度降为了O(n):
    首先,预处理数组,得到一个新的数组里面的值是原来数组的相邻2项的差值.
    问题等价于找在新数组中找到连续的元素的和的最大值.这个问题我们可以用动态规划来解决了.
     */
    static public int maxProfit(int[] prices) {
        int[] subtraction = new int[prices.length];
        int[] result = new int[prices.length];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            subtraction[i] = prices[i] - prices[i - 1];
        }
        //System.out.println(Arrays.toString(subtraction));
        for (int i = 1; i < subtraction.length; i++) {
            result[i] = subtraction[i] + result[i - 1] > subtraction[i] ? subtraction[i] + result[i - 1] : subtraction[i];
            if (result[i] > maxProfit) {
                maxProfit = result[i];
            }
        }
        // System.out.println(Arrays.toString(result));
        return maxProfit;

    }

    /*
    Leetcode也给出了O(n)的解法.思路是找到每个数组元素与该元素之前元素的最小值的差值,然后在这些差值中找到最大值.思路很简单粗暴,可惜我怎么没想到- -
     */
    public int EditorialSolution(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        int[] test1 = {7, 1, 5, 3, 6, 4};
        int[] test2 = {7, 6, 4, 3, 1};
        int[] test3 = {7, 6, 4, 3, 1, 3, 3, 4, 5, 6, 7, 9, 2, 1, 2, 3, 10};
        System.out.println(maxProfit(test3));
        System.out.println(maxProfit(test2));
        System.out.println(maxProfit(test1));
    }
}
