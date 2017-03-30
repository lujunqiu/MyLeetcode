/**
 * Created by lujunqiu on 17/3/29.
 * Description:
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * Example:
 * Given input array nums = [3,2,2,3], val = 3
 * Your function should return length = 2, with the first two elements of nums being 2.
 */
public class Test27_Remove_Element {
    /*
    我们要实现的是数组元素就地删除操作.使用2个指针,一个快指针一个慢指针,快指针遍历数组,慢指针在遇到要删除的元素会停顿下来后,然后把要删除元素的后序元素逐一前移覆盖.
    我们在删除数组元素的过程中,仍然保持了数组元素的相对顺序.题目中说到"The order of elements can be changed.",说明还是可以优化的.接下来介绍一下Leetcode的Editorial Solution.
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    /*
    这里利用了题目中说到"The order of elements can be changed." 我们在遇到需要删除的元素的时候将其与当前数组最后一个元素替换,然后减少数组长度.
    这样的话,删除的元素都会移动到数组的最后,但是其他元素的相对位置会有所改变.
     */
    public int EditorialSolution(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                n--;
            } else {
                i++;
            }
        }
        return n;
    }
}
