/**
 * Created by lujunqiu on 17/3/2.
 * Description:
 *  Given a string s, find the longest palindromic(回文) substring in s. You may assume that the maximum length of s is 1000.
 *  Example:
 *  Input: "babad"
 *  Output: "bab"
 *  Note: "aba" is also a valid answer.
 *  Example:
 *  Input: "cbbd"
 *  Output: "bb"
 *
 */
public class Test5_Longest_Palindromic_Substring {
    /*
    方法一:
    暴力求解BF思路:我们会首先求出所有的子字符串集合,然后逐个判断子字符串是否回文.
    判断方法:如果子字符串是偶数个字符,那么就2边逐个比较是否相等;
            如果子字符串是奇数个字符,那么就忽略中心字符之后从2边逐个比较是否相等;
    时间复杂度太高O(n^3).Time Limit Exceeded
    实质:得到所有可能的解(得到的是所有子字符串)-----逐一判断是否是最优解
     */
    public static String BruteForce(String s) {
        String subString = null;
        int longestLength = 0;
        String BruteForce = null;

        if (s.isEmpty()) {
            return null;
        }
        //求解字符串的所有子字符串,然后遍历判断是否回文.
        char[] temp = s.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            for (int j = i; j < temp.length; j++) {
                subString = new String(temp,i,j-i+1);
                if (isPalindrome(subString) > longestLength) {
                    BruteForce = subString;
                    longestLength = subString.length();
                }
            }
        }
        return BruteForce;
    }
    /*
    判断字符串是否回文.
     */
    private static int isPalindrome(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return 0;
            }
        }
        return s.length();
    }

    /*
    方法二:
    回文字符串是关于中心轴对称的,我们可以分别找到以每个子母为中心的最长回文字符串.
    但是需要注意的是:中心对称的回文字符串有奇,偶之分.有"abcba"与"abba"2种类型,需要分别判断.
    时间复杂度:O(n^2).
    实质:利用回文串中心对称的特性得到所有可能的解(得到的是所有回文子字符串)------比较之后得到最优解
     */
    public static String longestPalindrome(String s) {
        if (s.isEmpty())
            return null;
        if (s.length() == 1)
            return s;
        String longest = s.substring(0, 1);//边界情况:单个字符的时候自身回文,长度为1
        for (int i = 0; i < s.length(); i++) {//对每个字符求2种类型的回文字符串.
            //求解abcba类型的最长回文串,一个子母为中心的回文串
            String tmp = helper(s, i, i);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }

            //求解abba类型的最长回文串,2个子母为中心的回文串
            tmp = helper(s, i, i + 1);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }
        }
        return longest;
    }
    //给定中心字符,无论是一个还是2个字符,求得以该字符为中心的最长回文字符串.从中心往字符串2侧逐步延伸.
    private static String helper(String s, int begin, int end) {//函数接口统一了1个中心和2个中心的情况!
        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);//易错!
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("aba"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("zxcvbbndfgababadewrvqwrqqrwqvdfsgaaaags"));

    }
}
