import java.util.Arrays;

/**
 * Created by lujunqiu on 17/8/17.
 * Description:
 * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class Test66_Plus_One {
    public static void main(String[] args) {
//        int[] a = {9,8,7,6,5,4,3,2,1,0};
//        int[] a = {9,8,7,6,5,9,9,9,9,9};
        int[] a = {9,9,9,9,9};
        a = plusOne(a);
        System.out.println(Arrays.toString(a));
    }
    /*
    看到题目之后,想都没想就直接把整形数组里面的数变成一个int,然后加１,再变回整形数组.写完之后出现了问题,发现自己还是太年轻了.
    int的最大值2147483647,long的最大值9223372036854775807,超过这个值就无法这么做了.
    于是换个思路,我们不做中间的转换工作,直接在原来的数组上模拟加法即可.
     */
    static public int[] plusOne(int[] digits) {
        int l = digits.length;
        while (digits[l - 1] == 9) {//需要进位的情况
            digits[l - 1] = 0;
            l = l - 1;
            if (l == 0) {
                break;
            }
        }
        if (l == 0) {//最高位进位的情况,这时候需要新建一个数组.
            int[] r = new int[digits.length + 1];
            r[0] = 1;
            System.arraycopy(digits, 0, r, 1, digits.length);//2个数组之间的复制可以去掉，因为在创建ｒ数组的时候，其他位置初始值默认为０
            return r;
        }
        digits[l - 1] = digits[l - 1] + 1;
        return digits;
    }
}
