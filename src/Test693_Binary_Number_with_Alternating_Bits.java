/**
 * Created by lujunqiu on 17/10/9.
 * Description:
 * Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
 * Example 1:
 * Input: 5
   Output: True
   Explanation:
   The binary representation of 5 is: 101
 */
public class Test693_Binary_Number_with_Alternating_Bits {
    /*
    除2取余法将一个十进制数转换二进制数,在转换的过程之中判断是否为01交替即可
     */
    static public boolean hasAlternatingBits(int n) {
        int temp = 2;//初始值为非0非1的任何值即可
        while (n != 0) {
            if (temp == n % 2) {
                return false;
            }
            temp = n % 2;
            n = n / 2;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(hasAlternatingBits(10));
    }
}
