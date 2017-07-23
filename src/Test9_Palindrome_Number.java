/**
 * Created by lujunqiu on 17/7/21.
 * Description:
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
public class Test9_Palindrome_Number {
    /*
    最简单的做法,将整形数x转换成字符,然后判断字符是否回文,在判断字符是否回文的过程中有2种回文形式分别判断即可.
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String s = new Integer(x).toString();
        char[] c = s.toCharArray();

        if (c.length % 2 == 0) {//处理字符串为偶数的情况
            for (int i = 0; i < c.length / 2; i++) {
                if (c[i] == c[c.length - i - 1]) {
                    continue;
                } else {
                    return false;
                }
            }
        } else {//处理字符串为奇数的情况
            int i = 0;
            int j = c.length - 1;
            while (c[i] == c[j]) {
                i++;
                j--;
                if (i == j) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }

    /*
     利用整数的特性,从个位数开始往高位提取每位数字,取到一半即中间位数的时候,开始比较2个数即可.
     */
    public static boolean discuss(int x) {
        if (x<0 || (x!=0 && x%10==0)) return false;
        int rev = 0;
        while (x>rev){
            rev = rev*10 + x%10;
            x = x/10;
        }
        return (x==rev || x==rev/10);//包含了偶数和奇数的情况
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(1221));
        System.out.println(isPalindrome(12211));
        System.out.println((discuss(121)));
        System.out.println(discuss(1211));
        System.out.println(discuss(112211));
    }
}
