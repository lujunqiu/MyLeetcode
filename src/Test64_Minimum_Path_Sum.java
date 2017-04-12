/**
 * Created by lujunqiu on 17/4/9.
 * Description:
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along
 * its path.
 * Note: You can only move either down or right at any point in time.
 */
public class Test64_Minimum_Path_Sum {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 1, 1}, {2, 21, 1, 1}, {1, 1, 1, 1}};
        System.out.println(minPathSum(a));
    }
    /*
    显然这个是一个典型的DP问题>>>满足:每个阶段的最优状态可以从之前某个阶段的某个或某些状态直接得到而不管之前这个状态是如何得到的。
    状态定义:(针对每个节点定义状态)从起始节点开始到每个节点的最短权值和的值.
    我们知道,Note: You can only move either down or right at any point in time.
    那么,每个节点的前一个节点只有可能是它的左节点或者上节点,那么其最短权值和的也只有可能是从这2个节点中选中(与其他状态无关),从而可以完成DP表.

    这个问题是著名的DTW语言识别算法的benchmark problem,似乎李开复的毕业论文就是基于这个算法来做的语音识别.
     */
    static public int minPathSum(int[][] grid) {
        int[][] table;//DP表
        int m = grid.length;//行数
        int n = grid[0].length;//列数
        table = new int[m][n];
        //System.out.println(m + "" + n);
        table[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {//初始化第一列
            table[i][0] = table[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < n; i++) {//初始化第一行
            table[0][i] = table[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                table[j][i] = table[j - 1][i] > table[j][i - 1] ? table[j][i - 1] + grid[j][i] : table[j - 1][i] + grid[j][i];
            }
        }
        return table[m - 1][n - 1];
    }
}
