import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by lujunqiu on 2017/12/8.
 * Description:
 * Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait until
 * a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 * For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class Test739_Daily_Temperatures {
    /**
     * 暴力求解的做法，时间复杂度O(n^2)
     */
    public int[] dailyTemperatures_BF(int[] temperatures) {
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            int j = i + 1;
            for (; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    res[i] = j - i;
                    break;
                }
            }
            if (j == temperatures.length) {
                res[i] = 0;
            }

        }
        return res;
    }

    /**
     * 类比503题，利用栈。在栈中我们保存的是数组的索引，而不是数组中的数值。原因很简单：通过数组的索引可以得知对应的数组，而通过数值是无法得知索引的，并且此题我们是同时需要这２个信息的。
     * 每次压入一个元素，并且在压入元素的时候，调整栈中元素，保证栈从底往上是降序的，也就是所谓的单调栈(decreasing stack)，时间复杂度O(n)


     * 利用不同栈实现的数据结构，运行的效率也不同：
     * Stack(54 ms)：Vector实现的栈,效率很低，因为Stack继承自Vector(线程安全)，而Vector会resizing，所以推荐使用ArrayDeque来实现栈！源码中也有说明。
     * ArrayDeque(33ms):双端队列
     * Array(10ms)：数组，直接操作数组效率是最高的
     */
    public int[] dailyTemperatures_Stack(int[] temperatures) {
//        Stack<Integer> stack = new Stack<>();
        Deque<Integer> stack = new ArrayDeque<>();//使用双端队列实现栈，效率要好过Stack，在Stack的源码中有说明！
        int[] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int temp = stack.pop();
                res[temp] = i - temp;
            }
            stack.push(i);
        }
        return res;
    }

    /**
    用一个数组模拟栈操作，效率最高.
     */
    public int[] dailyTemperatures_Array(int[] temperatures) {
        int[] stack = new int[temperatures.length];
        int top = -1;
        int[] ret = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (top > -1 && temperatures[i] > temperatures[stack[top]]) {
                int idx = stack[top--];
                ret[idx] = i - idx;
            }
            stack[++top] = i;
        }
        return ret;
    }
}
