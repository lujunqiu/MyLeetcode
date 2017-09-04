/**
 * Created by lujunqiu on 17/9/2.
 * Description:
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * For example, given the following matrix:
 * 1 0 1 0 0
   1 0 1 1 1
   1 1 1 1 1
   1 0 0 1 0
 * Return 4.
 */
public class Test221_Maximal_Square {
    /*
    这种求最优类型的问题,第一思路除了暴力搜索之外应该很快到想到用动态规划来解决.
    动态规划求解的关键在于状态的定义以及状态转移方程
    状态定义:dp(i,j)表示原矩阵中以点(i,j)为正方形右下顶点的最大正方形的边长
    状态转移定义:dp(i, j)=min(dp(i−1, j), dp(i−1, j−1), dp(i, j−1))+1
    在具体编程的时候,使用到了2个tricks:
    在定义dp表的时候比原表要大一点,这样就不用单独初始化第一行第一列dp表了;
    我们在循环体外计算面积,而不是循环体内计算,这样代码会高效一点.
     */
    public int maximalSquare(char[][] matrix) {
        int result = 0;
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;

        int[][] dp = new int[rows + 1][cols + 1];//dp表比原表大,这样的话我们就可以不用单独初始化dp表的第一行和第一列数据了,就可以像后面代码一样统一处理

        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[i - 1].length; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                    result = Math.max(result, dp[i][j]);//循环内只比较正方形边长的值
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return result * result ;//在循环外做面积的计算,更加高效
    }
}

