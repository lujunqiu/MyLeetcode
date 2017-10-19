import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lujunqiu on 17/10/17.
 * Description:
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 * Example 1:
   Input: [1, 2, 2, 3, 1]
   Output: 2
   Explanation:
   The input array has a degree of 2 because both elements 1 and 2 appear twice.
   Of the subarrays that have the same degree:
   [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
   The shortest length is 2. So return 2.
   Example 2:
   Input: [1,2,2,3,1,4,2]
   Output: 6
 */
public class Test697_Degree_of_an_Array {
    /**
     *遍历输入数组,记录每个数字出现的次数,同时统计每个数字出现第一次与最后一次的index.
     *这里我们可以用到java8新给hashmap添加的getorDefault方法:
     *getOrDefault(Object,V)允许调用者在代码语句中规定获得在map中符合提供的键的值，否则在没有找到提供的键的匹配项的时候返回一个“默认值”。
     */
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer[]> map2 = new HashMap<>();
        int degree = 0;
        int size = nums.length;

        for (int i = 0; i < nums.length; i++) {
            map1.put(nums[i], map1.getOrDefault(nums[i], 0) + 1);
            degree = Math.max(degree, map1.get(nums[i]));

            if (map2.get(nums[i]) == null) {
                map2.put(nums[i], new Integer[2]);
            }
            Integer[] integers = map2.get(nums[i]);
            if (integers[0] == null) {
                integers[0] = i;
            }
            integers[1] = i;
        }


        Iterator iterator = map1.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if ((int)value == degree) {
                Integer[] integers = map2.get(key);
                size = Math.min(size, integers[1] - integers[0] + 1);
            }

        }
        return size;
    }

    public static void main(String[] args) {
        Test697_Degree_of_an_Array degree_of_an_array = new Test697_Degree_of_an_Array();
        int a = degree_of_an_array.findShortestSubArray(new int[]{1, 2, 2, 3, 1, 4, 2});
        System.out.println(a);
    }
}
