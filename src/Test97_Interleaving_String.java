/**
 * Created by lujunqiu on 17/4/30.
 * Description:
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 */
public class Test97_Interleaving_String {
    public static void main(String[] args) {
        String string1 = "aabcc";
        String string2 = "dbbca";
        String string3 = "aadbbbaccc";
        String string4 = "aadbbcbcac";
        System.out.println(isInterleave(string1, string2, string3));
        System.out.println(isInterleave(string1, string2, string4));
        System.out.println(EditorialSolution(string1, string2, string3));
        System.out.println(EditorialSolution(string1, string2, string4));
    }

    /*
    由于现在对动态规划的状态定义不是那么熟练，我在网上看了一下解答的一部分，把他们的状态定义借鉴了之后，自己完成了该题目的动态规划的过程。
    动态规划的难点不是状态的转移或者DP表的完善，难就难在如何定义后无效性的状态（最优子结构）!

    动态规划来求解问题：
    我们定义状态：table[i][j]表示s1的前缀到索引为i的子串与s2的前缀到索引为j的子串 是不是 s3的前缀索引到i+j的子串的Interleave；
    定义状态转移：如果s3的索引为i+j的字符，与s1的索引为i的字符以及s2的索引为j的字符都不相等的话，那么table[i][j] = false
    如果有s1的索引为i的字符匹配了s3索引为i+j的字符，那么table[i][j]取决与table[i - 1][j]
    如果有s2的索引为j的字符匹配了s3索引为i+j的字符，那么table[i][j]取决与table[i][j - 1]
    由于题目是要我们找一个一种匹配的interleave的形式，我们用“或"逻辑即可，即任意一种匹配的方式都可以。
    我们仍然要事先初始化table表的第一行与第一列的值。
 */
    static boolean isInterleave(String s1, String s2, String s3) {
        int i = s1.length();
        int j = s2.length();
        int ij = s3.length();
        // ==是引用一致判断，Integer和Long是有缓冲机制才保证引用一致。equals是内容一致判断（由用户编写相等的逻辑），引用一致是特例
        // 所以我们在比较字符串内容的时候，要用equals来判断
        if (i == 0 || s1.equals(" ")) {
            return s2.equals(s3);
        }
        if (j == 0 || s2.equals(" ")) {
            return s1.equals(s3);
        }
        if (i + j != ij) {
            return false;
        }
        boolean[][] table = new boolean[i + 1][j + 1];
        table[0][0] = true;
        //初始化DP表的第一行
        for (int k = 1; k < j + 1; k++) {
            table[0][k] = table[0][k - 1] && (s2.charAt(k - 1) == s3.charAt(k - 1));
        }
        //初始化DP表的第一列
        for (int k = 1; k < i + 1; k++) {
            table[k][0] = table[k - 1][0] && (s1.charAt(k - 1) == s3.charAt(k - 1));
        }
        //完善DP表得到最终的结果
        for (int m = 1; m < i + 1; m++) {
            for (int n = 1; n < j + 1; n++) {
                table[m][n] = (table[m - 1][n] && (s3.charAt(m + n - 1) == s1.charAt(m - 1))) || (table[m][n - 1] && (s3.charAt(m + n - 1) == s2.charAt(n - 1)));
            }
        }
        return table[i][j];
    }
    /*
    上述的算法很幸运的AC了（状态定义的准确！），这里介绍一个Editorial Solution，也是用的动态规划，但是table表是一维的。
    思路是完全一样的，用了个trick，减少了空间复杂度，这里不推荐使用,代码不一定好懂。
    */
    static public boolean EditorialSolution(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[] = new boolean[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s2.length()];
    }
}

