/**
 * Created by lujunqiu on 17/10/20.
 * Description:
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * Here are few examples.
  [1,3,5,6], 5 → 2
  [1,3,5,6], 2 → 1
  [1,3,5,6], 7 → 4
  [1,3,5,6], 0 → 0
 */
public class Test35_Search_Insert_Position {
    /**
     二分查找法的应用.
     二分查找法有很多种写法,最好记下一种写法,然后能写对!
     */
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while(low <= high){
            int mid = low + (high - low ) / 2;
            if(nums[mid] < target){
                low = mid + 1;
            }
            if(nums[mid] >= target){
                high = mid - 1;
            }
        }
        return low;

    }
}
