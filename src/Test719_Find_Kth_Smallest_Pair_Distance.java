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
     * 上述brute force方法,过程原理简单,但是遍历了2次数组,耗时太久.能不能在O(n)的复杂度下就能解决找个问题呢?
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int WIDTH = 2 * nums[nums.length - 1];

        //multiplicity[i] = number of nums[j] == nums[i] (j < i)
        int[] multiplicity = new int[nums.length];
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1]) {
                multiplicity[i] = 1 + multiplicity[i - 1];
            }
        }

        //prefix[v] = number of values <= v
        int[] prefix = new int[WIDTH];
        int left = 0;
        for (int i = 0; i < WIDTH; ++i) {
            while (left < nums.length && nums[left] == i) left++;
            prefix[i] = left;
        }

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; ++i) {
                count += prefix[nums[i] + mi] - prefix[nums[i]] + multiplicity[i];
            }
            //count = number of pairs with distance <= mi
            if (count >= k) hi = mi;
            else lo = mi + 1;
        }
        return lo;
    }
}
}
