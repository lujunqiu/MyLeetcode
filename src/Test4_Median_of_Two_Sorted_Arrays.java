/**
 * Created by lujunqiu on 17/10/14.
 * Description:
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * Example 1:
   nums1 = [1, 3]
   nums2 = [2]
   The median is 2.0

   Example 2:
   nums1 = [1, 2]
   nums2 = [3, 4]
   The median is (2 + 3)/2 = 2.5
 */
public class Test4_Median_of_Two_Sorted_Arrays {
    /**

     */
    static public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        //保证m<n,后续二分查找的时候是查找的短的数组
        if (m > n) {
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            //i太小了,iMin界限右移
            if (i < iMax && B[j-1] > A[i]){
                iMin = iMin + 1;
            }
            //i太大了,iMax界限左移
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = iMax - 1;
            }
            else { //找到了合适的切分点,计算中位数即可
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }

                if ( (m + n) % 2 == 1 ) { return maxLeft; }//如果是奇数,那么直接返回左界限即可

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,2,3,4,5};
        int[] B = new int[]{2,3,5,8,10};
        System.out.println(findMedianSortedArrays(A, B));
    }
}
