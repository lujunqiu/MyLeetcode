import java.util.HashMap;

/**
 * Created by lujunqiu on 17/3/1.
 * Description:
 *  Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *  You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * Examples:
 *  Given nums = [2, 7, 11, 15], target = 9,
 *  Because nums[0] + nums[1] = 2 + 7 = 9,
 *  return [0, 1].
 */
public class test1_TwoSum {
    /*
     这是一个O(n^2)的算法,Brute Force求解;
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
    /*
    为了减少上述BF算法的时间复杂度,我们可以考虑牺牲空间复杂度来减少时间复杂度.将上述过程的问题分解并将查询操作复杂度降为O(1)
    这里我们可以看到在BF算法的第2层循环其实就是一个查询的数组是否含有某个元素的过程.而我们知道查询算法的时间效率最高的就是HashMap了,查询操作为O(1).
    在这里我们使用HashMap的key为数组的值,value为数组的索引.
     */
    public int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            //这里使用&&的逻辑短路,而且注意题目中描述的:不能重复使用同一个元素
            if (map.containsKey(temp) && map.get(temp) != i) {
                result[0] = i;
                result[1] = map.get(temp);
            }
        }
        return result;
    }
    public static void main(String[] args) {

    }

}
