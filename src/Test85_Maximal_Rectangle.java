import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by lujunqiu on 2018/1/7.
 * Description:
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 * For example, given the following matrix:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 6.
 */
public class Test85_Maximal_Rectangle {
    /*
    类似的题目：221.Maximal Square，问题类似，但是解法有所区别。

    可以先看看84.Largest Rectangle in Histogram，这道题解决的问题是输入一个数组，数组每个元素代表的是宽为1高度为元素值的长方形，要求输出最大的长方形面积。

    那么，我们可以将85题转换为84题解决的问题上去。
    本题输入的是一个二维数组，我们单看这个二维数组每一行row，以该行row往上，计算该行row每个元素的高度，得到一个高度数组，有了这个高度数组就可以利用84题的结论，得到以该行的元素为底的最大长方形面积。
    如此往复，遍历这个二维数组的所有行，就可以得到最大面积了。
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int result = 0;
        int[][] heights = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    if (i - 1 >= 0) {
                        heights[i][j] = heights[i - 1][j] + 1;
                    } else {
                        heights[i][j] = 1;
                    }
                }
            }
            result = Math.max(result, largestRectangle(heights[i]));
        }
        return result;
    }

    /*
    84.Largest Rectangle in Histogram
     */
    public int largestRectangle(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int h = heights[stack.peek()];
                stack.pop();
                int area = h * (stack.isEmpty() ? i : (i - stack.peek() - 1));

                max = max > area ? max : area;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int h = heights[stack.peek()];
            stack.pop();
            int area = h * (stack.isEmpty() ? heights.length : (heights.length - stack.peek() - 1));

            max = max > area ? max : area;
        }
        return max;
    }
}

