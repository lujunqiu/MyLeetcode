/**
 * Created by lujunqiu on 17/9/24.
 * Description:
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each
 * step you can delete one character in either string.
 * Example 1:
 * Input: "sea", "eat"
   Output: 2
   Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 */
public class Test583_Delete_Operation_for_Two_Strings {
    /*
    这个问题,乍一看有点像另一个问题"将一个字符串变成另一个字符串需要的最少操作次数(每次操作允许增加,删除,修改一个字符)",也是用dp来解决,状态定义:dp[i][j]表示word1[0..i-1]转换为word2[0..j-1]的最小操作数
    状态转移为:如果word1[i-1] == word2[j-1]，则dp[i][j] = dp[i-1][j-1]，因为不需要进行操作，即操作数为0.如果word[i-1] != word2[j-1]，则需考虑三种情况，取最小值：
    Replace word1[i-1] by word2[j-1]： (dp[i][j] = dp[i-1][j-1] + 1 (for replacement));
    Delete word1[i-1]：                  (dp[i][j] = dp[i-1][j] + 1 (for deletion));
    Insert word2[j-1] to word1[0..i-1]：(dp[i][j] = dp[i][j-1] + 1 (for insertion)).

    然而这道题还是有区别的,因为上题我们是知道最终的操作结果的,但是这题最后的结果字符串不能提前得知.但是很容易观察得知,最终的结果就是2个字符串的最长公共子序列,于是我们求得这个最长公共子序列长度即可.
    求最长公共子序列,典型的动态规划问题.
    状态定义:dp[i][j]表示word1[0..i-1]与word2[0..j-1]的最长公共子序列长度
    状态转移:如果word1[i-1] == word2[j-1]，则dp[i][j] = dp[i-1][j-1] + 1
    如果如果word[i-1] != word2[j-1]，则dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])

    同样,我们再次引申一个问题,如何求2个字符串的最长公共子串,相比于子序列,子串要求连续,如何利用dp求解呢?
    状态定义:dp[i][j]表示word1[0..i-1]与word2[0..j-1]的最长公共子串长度,该子串以word1[i-1]和word2[j-1]结尾
    状态转移:如果word1[i-1] == word2[j-1]，则dp[i][j] = dp[i-1][j-1] + 1
    如果如果word[i-1] != word2[j-1]，则dp[i][j] = 0 (区别就在这里:最长公共子串长度是在遍历过程中取最大值得到的,不是最终的结果dp[word1.length()][word2.length()])
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length() ; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return word1.length() + word2.length() - 2 * dp[word1.length()][word2.length()];
    }
}
