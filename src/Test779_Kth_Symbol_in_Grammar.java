/**
 * Created by lujunqiu on 2018/2/24.
 * Description:
 * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
 * Examples:
 * Input: N = 1, K = 1
 * Output: 0
 * Input: N = 2, K = 1
 * Output: 0
 * Input: N = 2, K = 2
 * Output: 1
 * Input: N = 4, K = 5
 * Output: 1
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 * row 4: 01101001
 */
public class Test779_Kth_Symbol_in_Grammar {
    /*
    找到每一个行的规律即可：每一行前半部分与后半部分对称相反，并且每一行的前半部分与上一行相同。
    直接递归求解就OK了。至于如何将0变成1，1变成0。我是直接将变量加1然后对2取余做到的，也可以使用其他的方法
     */
    static public int kthGrammar(int N, int K) {
        return fun(N, K);
    }

    static private int fun(int n , double k) {
        if (n == 1) {
            return 0;
        } else {
            if (k <= Math.pow(2, n - 2)) {
                return fun(n - 1, k);
            } else {
                return (fun(n - 1, k - Math.pow(2, n - 2)) + 1) % 2;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(kthGrammar(5,16));
    }
}
