/**
 * Created by lujunqiu on 17/7/23.
 * Description:
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that num[-1] = num[n] = -∞.
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */
public class Test162_Find_Peak_Element {

    /*
    判断每个索引是不是Peak Element
     */
    public int findPeakElement(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }
            for (int i = 0; i < nums.length; i++) {
                if (ifPeadElement(i, nums) == true) {
                    return i;
                }
            }
            return 0;
    }

    static private boolean ifPeadElement(int index, int[] nums) {

        if (index == 0) {//至少有２个元素
            return nums[index + 1] < nums[index];
        }
        if (index == nums.length - 1) {//至少有2个元素
            return nums[index] > nums[index - 1];
        }

        return nums[index] > nums[index - 1] && nums[index] > nums[index + 1];
    }
}
