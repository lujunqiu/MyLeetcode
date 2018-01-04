/**
 * Created by lujunqiu on 2017/12/24.
 * Description:
 * Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.
 * Note: 1 ≤ k ≤ n ≤ 109.

 * Example:
 * Input:
 * n: 13   k: 2
 * Output:
 * 10
 * Explanation:
 * The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 */
public class Test440_Kth_Smallest_in_Lexicographical_Order {
    /*
    十叉树(denary tree)的先序遍历。
    本题对应的Discuss区有详细的图文解释，也可以自己在纸上画出这个十叉树模拟算法运行。
     */
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k = k - 1;
        while (k > 0) {
            //在同一个层级中，当前节点到其右边邻居节点需要的步数
            int steps = calSteps(n, curr, curr + 1);
            if (steps <= k) {//当前节点到其右边邻居节点需要的步数小于k，则可以直接跳到其右邻居节点
                curr += 1;
                k -= steps;
            } else {//否则，需要进入当前节点的最小子节点
                curr *= 10;
                k -= 1;
            }
        }
        return curr;
    }
    //计算在十叉树中当前节点到其右边邻居节点的距离
    public int calSteps(int n, long n1, long n2) {
        int steps = 0;
        while (n1 <= n) {
            steps += Math.min(n + 1, n2) - n1;
            n1 *= 10;
            n2 *= 10;
        }
        return steps;
    }
}
