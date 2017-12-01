/**
 * Created by lujunqiu on 17/11/30.
 * Description:
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the
 * boundary (i.e. wrap-around is not allowed).
 * Example 1:

 * nums = [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].

 * Example 2:

 * nums = [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * Return 4
 * The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class Test329_Longest_Increasing_Path_in_a_Matrix {
    /**
     最直观的做法是穷举每一个数字作为起点，dfs寻找最长的上升路径，其实如果真要这么做的话，用bfs会提高效率。
     上述还不是最好的解法。我们观察得到，这里的最长上升路径满足后无效性与最优子结构，可以用动态规划的思想来求解。
     我们把到每个位置的最长路径的长度存储下来，那么每个点只会计算一次(如果用dfs的话会重复计算多次)，并且当前位置的最长上升路径可以通过周围四个点的最长上升路径得到。
     这里就有一个问题，动态规划的顺序，从哪个节点开始动态规划，也就是动态规划的初始值的问题。我们可以为每个位置设置一个boolean变量，标识该位置的最长上升路径是否搜索完毕，如果搜索完毕就直接返回值，如果没有搜索完毕就继续搜索。
     如果某个位置的值比周围的值都要小，那么该位置只能成为起点，最长上升路径长度为1。
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];
        boolean[][] state = new boolean[n][m];

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = search(matrix, dp, state, i, j);
                result = result > dp[i][j] ? result : dp[i][j];
            }
        }
        return result;
    }

    private int search(int[][] matrix, int[][] dp, boolean[][] state, int i, int j) {
        if (state[i][j] == true) {
            return dp[i][j];
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int ans = 1, x, y;
        for (int k = 0; k < 4; k++) {
            x = i + dx[k];
            y = j + dy[k];

            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] < matrix[i][j]) {
                ans = Math.max(ans, search(matrix, dp, state, x, y) + 1);
            }
        }
        dp[i][j] = ans;
        state[i][j] = true;
        return ans;
    }
}
