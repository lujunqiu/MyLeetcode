import java.util.Stack;

/**
 * Created by lujunqiu on 17/4/17.
 * Description:
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 * For example,
 * Given heights = [2,1,5,6,2,3],
 * return 10.
 */
public class Test84_Largest_Rectangle_in_Histogram {
    /*
    状态定义:以height[i]的高度为最低高度的矩阵的面积记录为dp[i]
    时间复杂度:O(n^2)
    Leetcode提交后通过 95/96 case,最后一个case超时.
     */
    static public int largestRectangleArea(int[] heights) {
        int[] dp = new int[heights.length];
        int area = 0;

        for (int i = 0; i < heights.length; i++) {
            int left = i;
            int right = i;
            for (int j = i - 1; j >= 0 && heights[j] >= heights[i]; j--) {
                left = j;
            }
            for (int j = i + 1; j <= heights.length - 1 && heights[j] >= heights[i]; j++) {
                right = j;
            }
            dp[i] = (right - left + 1) * heights[i];
            area = area > dp[i] ? area : dp[i];
        }
        // System.out.println(Arrays.toString(dp));
        return area;
    }

    /*
    似乎Leetcode要求的是O(n)的算法才能AC,我们需要进一步改进最初的O(n^2)的算法,增加我们的搜索解的效率.
    我从google找到了一个该题的O(n)的算法,用到了栈.

    思路是:
    在遍历数组的时候维护一个栈,这个栈中的元素从底向上按照高度值递增.如果遇到当前bar高度比栈顶元素低，那么就出栈直到栈顶元素低于当前bar.

    每次元素出栈都要做如下运算:
    如果栈已经为空，矩阵面积就是高度乘以当前下标i。
    如果栈不为空，矩阵面积就是高度乘以当前下标i减栈顶元素再减1。

    在遍历完数组后,仍然要继续将栈中元素出栈,直到栈空.

    其实,我们可以发现,每次元素出栈的时候都计算了以该元素的高度为最低高度的矩阵面积,和上面的我们的dp[i]一样的思路,只是避免了一些不必要的计算.
    最后所有元素都会依次出栈,意味着计算完了所有可能的最大面积,就可以得到结果了.
     */
    static public int improved(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.empty() && heights[i] < heights[stack.peek()]) {
                int h = heights[stack.peek()];
                stack.pop();
                int area = h * (stack.empty() ? i : (i - stack.peek() - 1));

                max = max > area ? max : area;
            }
            stack.push(i);
        }
        while (!stack.empty()) {
            int h = heights[stack.peek()];
            stack.pop();
            int area = h * (stack.empty() ? heights.length : (heights.length - stack.peek() - 1));

            max = max > area ? max : area;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{1, 1}));
        System.out.println(improved(new int[]{1, 1}));

        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(improved(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
