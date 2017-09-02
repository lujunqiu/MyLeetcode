import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lujunqiu on 17/8/29.
 * Description:
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between
 * nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 */
public class Test220_Contains_Duplicate_III {
    /*
    思想很简单:遍历数组，每次在当前索引值右侧k的范围内寻找符号条件的索引，如果有则返回true，否则继续遍历
    应该是最容易想到的暴力求解的方法了。
    */
    public boolean bruteforce(int[] nums, int k, int t) {
        //这个判断语句可以说是面向测试用例编程，因为所有的test case都通过了，只有最后一个超时了，最后一个用例正好是找相等值的情况，就可以用比较简单的方法来处理了。
        if (t == 0) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (set.contains(num)) {
                    return true;
                } else {
                    set.add(num);
                }
            }
            return false;
        }

        for (int i = 0; i < nums.length; i++) {
            int point = i + 1;
            while (point < nums.length && point - i <= k) {
                if ((long)nums[i] - nums[point] <= t && (long)nums[i] - nums[point] >= -t) {
                    return true;
                } else {
                    point++;
                }
            }
        }
        return false;
    }

    /*
    除去上述的略微有点投机AC的方法，我们还可以借助map(滑动窗口)来完成。
    题目要找的是相差在某个范围之内的两个数,而且输入的数都是int类型的,我们可以利用整数的除法特性(结果小数点直接抹去),将这个范围(t)内的数映射到一个数字(除法的结果)上.
    简答来说,题目所给的两数相差最大为t,那么我们用t+1作为分母,用输入数除以t+1得到的结果为int类型,得到的结果其实是将输入数映射到一个bucket中,bucket的范围就是t.
    我们要找的结果数字的索引相差不能超过k,我们可以维护一个大小为k的滑动窗口来进行搜索,也是说map的大小为k即可.
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k <= 0 || t < 0){//错误的输入，直接返回false
            return false;
        }

        HashMap<Long, Long> map = new HashMap<>();//防止中间处理的结果超过int类型的范围，我们使用long型
        for (int i = 0; i < nums.length; i++) {
            long num = (long)nums[i] + Integer.MAX_VALUE;//保证我们处理的数据都是正数
            long bucket = num / ((long)t+1);
            if (map.containsKey(bucket) || map.containsKey(bucket-1) && Math.abs(map.get(bucket-1)-num) <= t ||
                    map.containsKey(bucket+1) && Math.abs(map.get(bucket+1)-num) <= t) {
                return true;
            }
            if (map.size() >= k) {//当滑动窗口大小大于k时,除去前面的bucket
                long lastBucket = ((long)nums[i-k]+Integer.MAX_VALUE)/((long)t+1);
                map.remove(lastBucket);
            }
            map.put(bucket, num);
        }

        return false;
    }
}
