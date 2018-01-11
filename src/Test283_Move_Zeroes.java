/**
 * Created by lujunqiu on 2018/1/11.
 * Description:
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class Test283_Move_Zeroes {
    /*
    利用一个指针记录"新"数组中非0值的位置，然后遍历赋值即可。最后再将数组剩余的值用0填充。
     */
    static public void moveZeroes(int[] nums) {
        int count = 0;//记录有多少非0值
        int p = 0;//在遍历过程中，记录"新"数组中非0值的位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
            } else {
                count++;
                nums[p] = nums[i];
                p++;
            }
        }
        for (int i = count; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] a1 = new int[]{0, 1, 0, 3, 12};
        int[] a3 = new int[]{10, 1, 30, 3, 12};
        int[] a2 = new int[]{0,0,0,0,0,1,1,1,10,0,0,0,1,2,3,4,10, 1, 0, 3, 12,0,0,0};
        moveZeroes(a3);
        for (int i = 0; i < a3.length; i++) {
            System.out.println(a3[i]);
        }
    }
}
