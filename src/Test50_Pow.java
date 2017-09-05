/**
 * Created by lujunqiu on 17/9/5.
 * Description:
 * Implement pow(x, n).
 */
public class Test50_Pow {
    /*
    思路很简单,分而治之.根据幂的奇偶性来分别递归求解.
    但是需要注意一个问题:32位int型数的范围是:-2147483648到2147483647,在我们处理-2147483648取相反数的时候超过int的范围,需要单独处理.
     */
    public double myPow(double x, int n) {
        if (n == 0) {//特殊的输入值单独处理
            return 1;
        }
        if (n == -2147483648) {//特殊的输入值单独处理
            x = 1 / x;
            n = 2147483647;
            return (n % 2 == 0) ? x * myPow(x * x, n / 2) : x * x * myPow(x * x, n / 2);
        }

        if (n < 0) {//分而治之
            n = -n;
            x = 1 / x;
        }
        return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }
}
