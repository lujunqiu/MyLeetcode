import java.util.HashMap;
import java.util.Map;

/**
 * Created by lujunqiu on 17/4/7.
 * Description:
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 */
public class Test55_Jump_Game {
    /*
    我的解法是,利用一个map来标记某个节点是否可达;从第一个节点开始往后标记可达节点,遍历完数组之后判断最后的节点是否可达;
    但是这个解法面对Leetcode的最后一个case的时候出现了Status: Time Limit Exceeded;
    该算法的时间复杂度为O(n^2);接下来我们来改进这个算法!
     */
    static public boolean canJump(int[] nums) {
        if (nums == null) {
            return false;
        }
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(0, true);
        for (int i = 1; i < nums.length; i++) {//初始化map
            map.put(new Integer(i), false);
        }
        for (int i = 0; i < nums.length; i++) {//逐个标记可达的节点
            if (map.get(i)) {
                for (int j = 1; j <= nums[i]; j++) {
                    map.put(i + j, true);
                }
            }
        }
        return map.get(nums.length - 1);
    }

    /*
     我们其实可以观察到一个特点,这个题目中不需要保持所有可达的点,只需要保持一个最右的可达的点即可.因为,如果m可达,那么n(n<=m)的节点都是可达的;
     所以在遍历数组的时候保存可达的最右边界,一旦可达的最右边界超过数组末元素即可返回true;如果遍历完所有可达节点都无法到达数组末尾返回false;
     时间复杂度:O(n),Accepted;
     */
    static public boolean canJump2(int[] nums) {
        if (nums == null) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        int maxIndex = 0;
        for (int i = 0; i <= maxIndex; i++) {
            if (i + nums[i] > maxIndex) {
                maxIndex = i + nums[i];
                if (maxIndex >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    Leetcode Editorial Solution:
     */
    static public boolean canJump3(int[] nums) {

    }

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 0, 4};
        System.out.println(canJump2(a));
    }
}
