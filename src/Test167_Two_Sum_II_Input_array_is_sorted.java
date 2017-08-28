/**
 * Created by lujunqiu on 17/8/27.
 * Description:
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than
 * index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 */
public class Test167_Two_Sum_II_Input_array_is_sorted {

    public static void main(String[] args) {
        int[] test = new int[]{2, 7, 11, 15};
        for (int i : twoSum(test, 9)) {
            System.out.println(i);
        }
    }

    /*
    如果这不是一个有序数组的话,我们可以使用hashmap来存储进行搜索.
    但是题目给的数组是有序的,我们就可以从数组的2边开始寻找结果.因为是有序的,所以如果两数之和小的话就左指针前移,如果两数之和大的话就右指针移动,直到两数之和等于目标值为止.
    时间复杂度为线性复杂度.
     */
    static public int[] twoSum(int[] numbers, int target) {
        int[] result = {-1, -1};//初始化数组
        for (int i = 0, j = numbers.length - 1; i < j;) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                result[0] = i + 1;
                result[1] = j + 1;
                return result;
            } else if (sum < target) {
                int n = numbers[i];
                while (numbers[i] == n)//值相同的话也跳过
                    i++;
            } else {
                int n = numbers[j];
                while (numbers[j] == n)//值相同的话也跳过
                    j--;
            }
        }

        return result;
    }
}
