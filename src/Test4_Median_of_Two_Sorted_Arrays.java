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
    这里,我们需要用到中位数另外一种解释:
     将集合内的数字分为两部分,每部分的数字个数相同,并且其中一个部分的任意数字都大于另外部分的数字.对于一个长度为m的数组,总共有m+1种切分方法.
    对于2个排好序的数组,我们也按照上述的定义,将数组切分.我们分别切分两个数组,然后将切分之后的2个数组中数值较小的部分合并,数值较大的部分合并.
    对于数组A(长度为m)切分点为i,对于数组B(长度为n)切分点在j,为了保证切分之后2个部分的集合长度相等,i,j需要满足i+j=(m + n + 1) / 2
    满足了长度要求之后,我们需要满足数值大小的关系,需要左边集合的数值都小于右边集合,由于2个数组直接是排好序的,只需要比较2个切分点周围的值即可,如果满足条件则输出.如果不满足条件,根据大小关系调整切分点的位置,相当于二分查找切分点的位置.
    在找满足条件的切分点的时候,2个切分点是联动的,我们只需要确定一个即可,可以从较短的数组开始切分.
    时间复杂度:log(min(m,n))
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
//                iMin = iMin + 1;
                iMin = i + 1;
            }
            //i太大了,iMax界限左移
            else if (i > iMin && A[i-1] > B[j]) {
//                iMax = iMax - 1;
                iMax = i;
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
