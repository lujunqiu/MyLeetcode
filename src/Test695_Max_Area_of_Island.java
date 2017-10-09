/**
 * Created by lujunqiu on 17/10/9.
 * Description:
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or
 * vertical.) You may assume all four edges of the grid are surrounded by water.
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 * Example 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
   [0,0,0,0,0,0,0,1,1,1,0,0,0],
   [0,1,1,0,1,0,0,0,0,0,0,0,0],
   [0,1,0,0,1,1,0,0,1,0,1,0,0],
   [0,1,0,0,1,1,0,0,1,1,1,0,0],
   [0,0,0,0,0,0,0,0,0,0,1,0,0],
   [0,0,0,0,0,0,0,1,1,1,0,0,0],
   [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 */
public class Test695_Max_Area_of_Island {
    /*
    深度优先搜索算法的简单应用.
     */
    static public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int col = grid.length;
        int row = grid[0].length;
        boolean[][] ifVisited = new boolean[col][row];
        int maxArea = 0;

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (grid[i][j] == 1 && ifVisited[i][j] == false) {
                    int temp = searchArea(i, j, grid, ifVisited);
                    maxArea = maxArea > temp ? maxArea : temp;
                }
            }
        }
        return maxArea;
    }

    /**
     * 递归搜索函数,搜索指定节点附近是否有其他有效的未访问节点,并且统计面积
     * @param i
     * @param j
     * @param grid
     * @param ifVisited
     * @return
     */
    static private int searchArea(int i, int j, int[][] grid, boolean[][] ifVisited) {
        int area = 1;
        ifVisited[i][j] = true;
        if (i + 1 < grid.length && grid[i + 1][j] == 1 && ifVisited[i + 1][j] == false) {
            area += searchArea(i + 1, j, grid, ifVisited);
        }
        if (i - 1 >= 0 && grid[i - 1][j] == 1 && ifVisited[i - 1][j] == false) {
            area += searchArea(i - 1, j, grid, ifVisited);
        }
        if (j + 1 < grid[0].length && grid[i][j + 1] == 1 && ifVisited[i][j + 1] == false) {
            area += searchArea(i, j + 1, grid, ifVisited);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == 1 && ifVisited[i][j - 1] == false) {
            area += searchArea(i, j - 1, grid, ifVisited);
        }
        return area;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        System.out.println(maxAreaOfIsland(a));
    }
}
