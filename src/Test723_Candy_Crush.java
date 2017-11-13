import java.util.HashSet;
import java.util.Set;

/**
 * Created by lujunqiu on 17/11/10.
 * Description:
 * This question is about implementing a basic elimination algorithm for Candy Crush.
 * Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of
 * candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the
 * game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following
 * rules:

 * 1.If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time -
 * these positions become empty.
 * 2.After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop
 * until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
 * 3.After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
 * 4.If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.

 * You need to perform the above rules until the board becomes stable, then return the current board.
 */
public class Test723_Candy_Crush {
    /**
    题目所要完成的工作,就是我们经常玩的手机游戏"消消乐"的基本原理:可以分为3个步骤.第一步,遍历当前状态(二维数组表示),找到哪些点是可以消的,并设置为0
     第二步,将第一步得到状态(那些需要被消去的点已经设置为0了的二维数组)crush 成"稳定"状态.
     第三步,重复1,2步,直到当前状态没有点需要被消除,退出
     */
    public int[][] candyCrush(int[][] board) {
        Set<Coordinates> set = new HashSet<>();
        //遍历board数组,判断那些点是需要被crush的点,统计起来,一起设置为0,方便后面的crush操作
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int cur = board[i][j];

                if (cur == 0) continue;
                if ((i - 2 >= 0 && board[i - 1][j] == cur && board[i - 2][j] == cur) ||
                        (i + 2 <= board.length - 1 && board[i + 1][j] == cur && board[i + 2][j] == cur) ||
                        (j - 2 >= 0 && board[i][j - 1] == cur && board[i][j - 2] == cur) ||
                        (j + 2 <= board[i].length - 1 && board[i][j + 1] == cur && board[i][j + 2] == cur) ||
                        (i - 1 >= 0 && i + 1 <= board.length - 1 && board[i - 1][j] == cur && board[i + 1][j] == cur) ||
                        (j - 1 >= 0 && j + 1 <= board[i].length - 1 && board[i][j - 1] == cur && board[i][j + 1] == cur)) {
                    set.add(new Coordinates(i, j));
                }
            }
        }
        if (set.isEmpty()) return board;//没有需要消除的点,返回结果
        for (Coordinates c : set) {//将那些需要被crush的点,设置为0
            int x = c.i;
            int y = c.j;
            board[x][y] = 0;
        }
        crush(board);
        return candyCrush(board);
    }

    /**
    用2个指针,将每一列从下往上遍历,同时完成crush操作.
     相当于一个数组的压缩操作:数组中有散落的0和其他数值,把数组中0值去掉,非0值顺次往前靠(相对于顺序不变),然后数组后半部分用0填充.O(n)复杂度完成这个操作.
     */
    private void crush(int[][] board) {
        for (int j = 0; j < board[0].length; j++) {
            int bot = board.length - 1;//指向每一列中从下往上看的最后一个0
            int top = board.length - 1;//指向每一列中从下往上看的当前遍历到的最上的那个0
            while (top >= 0) {
                if (board[top][j] == 0) {
                    top--;
                } else {
                    board[bot--][j] = board[top--][j];
                }
            }
            while (bot >= 0) {//将其余的点,设置为0
                board[bot--][j] = 0;
            }
        }
    }
}

class Coordinates {
    int i;
    int j;

    Coordinates(int x, int y) {
        i = x;
        j = y;
    }
}
