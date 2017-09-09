/**
 * Created by lujunqiu on 17/9/8.
 * Description:
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by
 * connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * Example 1:
 * 11000
   11000
   00100
   00011
   Answer: 3
 */
public class Test200_Number_of_Islands {
    /*
    思路:新建一个二维布尔数组来记录原数组中每个点是否被访问过.我们遍历原数组,如果该点为1,且未被访问就认为是发现了新岛屿
    然后,我们搜索新岛屿的边界,搜索方式为广度优先搜索,可以利用递归来实现搜索整个岛屿所占的点,并且标记为已访问
    改进:可以不新建布尔类型的数组,直接在原数组上修改,比如访问过的点设置为非'0',非'1'的任何char数值
     */
    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int result = 0;
        boolean[][] ifVisited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && ifVisited[i][j] == false) {
                    result++;
                    search(grid, ifVisited, i, j);
                }
            }
        }

        return result;
    }

    /**
     * 从岛屿的一个点开始访问整个岛屿,并标记所有访问过的点,广度优先搜索的递归实现.
     * @param grid
     * @param ifVisited
     * @param i
     * @param j
     */
    public void search(char[][] grid, boolean[][] ifVisited, int i, int j) {
        ifVisited[i][j] = true;
        if (j - 1 >= 0 && grid[i][j - 1] == '1' && ifVisited[i][j - 1] == false) {//利用逻辑短路,所以判断的顺序不能换
            search(grid, ifVisited, i, j - 1);
        }
        if (i + 1 < grid.length && grid[i + 1][j] == '1' && ifVisited[i+1][j] == false) {
            search(grid, ifVisited, i + 1, j);
        }
        if (j + 1 < grid[0].length && grid[i][j + 1] == '1' && ifVisited[i][j + 1] == false) {
            search(grid, ifVisited, i, j + 1);
        }
        if (i - 1 >= 0 && grid[i - 1][j] == '1' && ifVisited[i - 1][j] == false) {
            search(grid, ifVisited, i - 1, j);
        }
    }

    public static void main(String[] args) {
        Test200_Number_of_Islands test200_number_of_islands = new Test200_Number_of_Islands();
        char[][] grid = {{'1', '1', '1'}, {'0', '1', '0'}, {'1', '1', '1'}};
        System.out.println(test200_number_of_islands.numIslands(grid));

    }
}
