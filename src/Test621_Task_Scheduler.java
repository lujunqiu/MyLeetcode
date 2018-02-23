import java.util.Arrays;

/**
 * Created by lujunqiu on 2018/2/23.
 * Description:
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 * Example 1:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * Note:
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 */
public class Test621_Task_Scheduler {
    /*
    观察可知，本题最少的任务时间是大于等于总任务数的，何时等于呢？只有在所有idles都被填充的时候，也就是CPU一直在执行任务，没有空闲的时候。
    那么，我们要做的就是找到一个最长的执行任务链(比如{'A', 'A', 'A', 'B', 'B', 'B'}的4个'A')，然后去填充这个最长的任务链中的idles即可。
    我们只要找到了最长的任务执行链，确定了idles空闲的总数，后续其他的比这个最长任务链短的任务链总能填充到这些idles中去(画个图比划一下就很好理解)，所以直接遍历任务数组，做减法即可。
    本题核心思路就是找到最少的CPU空闲时间，跟着这个思路就很好理解。
     */
    static public int leastInterval(char[] tasks, int n) {
        int task = 0;//记录所有需要完成的任务总数
        int[] temp = new int[26];//记录每个tasks出现的次数,利用数组的值与索引做一个简单的map的映射
        for (char c : tasks) {
            temp[c - 'A']++;
            task++;
        }
        Arrays.sort(temp);
        int internals = temp[25] - 1;
        int idles = internals * n;
        for (int i = 24; i >= 0 && temp[i] > 0 ; i--) {
            idles -= Math.min(internals, temp[i]);
        }
        return idles > 0 ? task + idles : task;
    }


    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    }
}
