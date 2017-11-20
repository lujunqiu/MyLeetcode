import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by lujunqiu on 17/11/11.
 * Description:
 * Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute
 * difference between A and B.
 * Example 1:
 * Input:
 * nums = [1,3,1]
 * k = 1
 * Output: 0
 * Explanation:
 * Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 */
public class Test719_Find_Kth_Smallest_Pair_Distance {
    public static void main(String[] args) {
        Test719_Find_Kth_Smallest_Pair_Distance test = new Test719_Find_Kth_Smallest_Pair_Distance();
        System.out.println(test.smallestDistancePair(new int[]{1, 1, 3}, 1));
//        System.out.println(test.smallestDistancePair(new int[]{1, 2, 3, 4, 2, 7}, 8));
//        System.out.println(test.smallestDistancePairBruteForce(new int[]{1, 2, 3, 4, 2, 7}, 8));
//        System.out.println(test.smallestDistancePair(new int[]{4, 2, 3, 10, 1, 2, 3}, 21));
//        System.out.println(test.smallestDistancePairBruteForce(new int[]{4, 2, 3, 10, 1, 2, 3}, 21));
    }

    /**
     * 最直接的暴力搜索的方法:遍历所有的pairs,统计出所有的The distance of a pair (A, B).最后排序然后输出结果.
     * 时间复杂度O(n^2).
     * 显然这样子是会超时的.
     */
    public int smallestDistancePairBruteForce(int[] nums, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                list.add(Math.abs(nums[i] - nums[j]));
            }
        }
        Collections.sort(list);
        return list.get(k - 1);
    }

    /**
     * 上述brute force方法,过程原理简单,但是遍历了2次数组,耗时太久.能不能在小于O(n^2)的复杂度下就能解决找个问题呢?
     * 看到第k小、第k大这种问题，首先想到二分法。把求值问题转化为：小于这个值的元素有多少个。
     * 思路:
     * 寻找第k小的pair的差值,变成寻找一个差值,并且小于改差值的个数为k-1个
     * 预处理原数组:将原数组排序,方便后续统计差值的操作
     * 然后对于所有可能的差值进行二分查找,查找的判断条件为小于等于该差值的pair有多少个
     * 如何证明上述方法找到的差值正是题目的答案呢?
     * 首先,二分法的查询候选集会包含在0到最大差值的所有值,同时二分法返回的是一个唯一的数值,就是满足条件(比该差值小的pair个数为k-1)个的最小的那个(数轴上最左边的)
     * 也就是循环中统计个数的时候 在判断nums[j] - nums[i] <= mid 对应 nums[j] - nums[i] == mid的数值,这也确保了我们返回的差值是原数组中存在的,并且是满足条件的.
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, low = 0, hi = nums[n - 1] - nums[0];
        //利用二分法在所有可能的差值中找到一个满足条件的差值
        while (low < hi) {
            int cnt = 0, j = 0, mid = (low + hi) / 2;
            for (int i = 0; i < n; ++i) {
                while (j < n && nums[j] - nums[i] <= mid) {//这个等于号确保寻找到的差值是原数值中存在的
                    ++j;
                }
                //循环退出的时候,j指向第一个大于mid差值的下标
                cnt += j - i - 1;//统计个数的时候,统计j到i之间的个数,同时不能包括i和j
            }
            if (cnt >= k) {
                hi = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;//此时low==high
    }
}

