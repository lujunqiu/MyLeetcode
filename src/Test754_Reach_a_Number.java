/**
 * Created by lujunqiu on 2018/1/4.
 * Description:
 * You are standing at position 0 on an infinite number line. There is a goal at position target.
 * On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.
 * Return the minimum number of steps required to reach the destination.

 * Example 1:
 * Input: target = 3
 * Output: 2
 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second step we step from 1 to 3.

 * Example 2:
 * Input: target = 2
 * Output: 3

 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second move we step  from 1 to -1.
 * On the third move we step from -1 to 2.
 */
public class Test754_Reach_a_Number {

    /*
    该问题的数学语言表述为：在 1,2,3,...k 这k个整数前添加+或者-,使得k个数的和等于target，要求k的值尽量小。那么这样一来，target和-target的解法就是对称的，最终的结果是相同的，我们只用讨论正数的情况即可。

    首先，找到最小的k，使得1+2+..+k的和大于等于target值，体现在一维坐标轴上就是目标点从原点出发，向右移动1+2+..+k，恰好经过或者到达target。
    如果是恰好到达target，答案很简单，直接输出k即可。
    但是如果是经过target，说明我们需要回退才能到target，回退多少呢？回退1+2+..+k-target这么多即可。如何回退呢？将1+2+..+k中选择某些正号变成负号即可，比如我选择+2变成-2，那么就回退了4步。选择+k变成-k，就回退了2k步。
    所以，如果1+2+..+k-target是偶数，那么我们一定可以回退到target，因为(1+2+..+k-target)/2，一定是1+2+..+k中某几个整数的和，证明略。
    如果1+2+..+k-target是奇数，那么(1+2+..+k-target)是不能被2整除的，说明此时我们是无法回退到target的，既然如此就再向前一步，移动k+1步后再来判断，依次循环，直到找到满足条件的解。
    事实上，我们根据奇偶行很容易证明，如果k不满足条件，那么答案一定是k+1或者k+2中的某一个。
     */
    public int reachNumber(int target) {

        int absTarget = Math.abs(target);
        int k = 0;
        int s = 0;

        while (s < absTarget) {
            k++;
            s += k;
        }

        int temp = s - absTarget;

        if (temp == 0) {
            return k;
        }

        while (temp % 2 != 0) {
            k++;
            s += k;
            temp += k;
        }

        return k;
    }
}
