/**
 * Created by lujunqiu on 17/10/31.
 * Description:
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 */
public class Test69_SqrtX {
    /**
    二分查找法,查找满足条件的根号x的值,初始的查找范围是从1到x/2+1
    这里需要注意的是:题目要求Implement int sqrt(int x).所以在二分判断的时候,判断条件也应该向下取整
     */
    public int mySqrt1(int x) {
        int left = 1;
        int right = x / 2 + 1;//二分查找法的右边界,因为(x/2+1)肯定大于根号x
        if (x == 0)
            return 0;
        while (true) {
            int mid = left + (right - left) / 2;
            if (mid > x / mid) {//思考:这里能不能写成 mid * mid > x ?答案是不能的.因为我们查找的根号x的向下取整的值,不是根号x的精确值
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }


    /**
    同样的思路,我们换一种更加像二分查找法的写法
     */
    public int mySqrt2(int x) {
        int left = 1;
        int right = x / 2 + 1;
        if (x == 0) {
            return 0;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid * mid == x) {
                return mid;
            }
            if (mid > x / mid) {
                right = mid - 1;
            }
            if (mid <= x / mid) {
                if (mid + 1 > x / (mid + 1)) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;//因为题目输入大于0的数,总可以找到对应的解,这句话不会被执行到.
    }
}
