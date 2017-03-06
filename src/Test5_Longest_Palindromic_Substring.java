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
    public static String expandAroundCenter(String s) {
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

    /*
    方法三:
    动态规划DP求解:
    避免重复的计算相同的子问题的解,我们设计了一个2维数组来保存子问题的解,逐步完善这个数组最终求得原始问题的解.
    假设table[ i ][ j ]的值为true，表示字符串s中下标从 i 到 j 的字符组成的子串是回文串。那么可以推出：
    table[ i ][ j ] = table[ i + 1][ j - 1] && s[ i ] == s[ j ]
    通过上面这个公式,我们可以根据子问题一步步的搜索最优解,并且最后肯定问题能收敛.
    我们只需要一开始初始化table表即可.在初始化的时候,我们要分2个情况考虑,"aba"型和"abba"型的回文字符串,表现在table表上就是table矩阵的对角线以及对角线右上移动一单位的值.
    然后根据初始化的值,逐步向2侧比较扩展字符串,扩大子问题的规模.体现在table表上就是我们在初始化的2条对角线上遍历所有的点,对于每个点我们往右上45度角的方向求出table表里的其他的值.注意:在我们定义的问题中,table表对角线下方的值是无意义的.
    时间复杂度:O(n^2).运用DP通过子问题一步一步的扩大求最优解.table表的设计以及回文串的特性很关键,是我们能运用DP求解的基础.
     */
    public static String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return null;
        }
        if (s.length() <= 1) {
            return s;
        }
        
        int length = s.length();
        //table[i][j]的值表示字符串的i到j的子字符串是否是回文子串,若是值为长度,若不是值为0
        int[][] table = new int[s.length()][s.length()];
        int maxLength = 0;
        String longestString = null;
        
        /*
        初始化"aba"型的回文串最初解.
         */
        for (int i = 0; i < length; i++) {
            table[i][i] = 1;
            maxLength = 1;
            longestString = s.substring(i, i + 1);
        }
        /*
        初始化"abba"型回文串的最初解
         */
        for (int i = 0; i <= length - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                table[i][i + 1] = 2;
                maxLength = 2;
                longestString = s.substring(i, i + 2);
            }
        }
//        printTable(table);

        /*
        根据初始化的值,扩大子问题的规模,把子字符串向2侧比较扩展,求解所有"aba"回文字符串的解
         */
        for (int i = 0; i < length; i++) {
            int x = i;
            int y = i;
            while ((x - 1) >= 0 && (y + 1) <= length - 1 && s.charAt(x - 1) == s.charAt(y + 1)) {
                table[x - 1][y + 1] = table[x][y] + 2;
                if (table[x - 1][y + 1] > maxLength) {
                    maxLength = table[x - 1][y + 1];
                    longestString = s.substring(x - 1, y + 2);
                }
                x = x - 1;
                y = y + 1;
            }
            
        }

        /*
        根据初始化的值,扩大子问题的规模,把子字符串向2侧比较扩展,求解所有"abba"回文字符串的解
         */
        for (int i = 0; i < length; i++) {
            int x = i;
            int y = i + 1;
            while ((x - 1) >= 0 && (y + 1) <= length - 1 && table[x][y] != 0 && s.charAt(x - 1) == s.charAt(y + 1)) {
                table[ x - 1][y + 1] = table[x][y] + 2;
                if (table[x - 1][y + 1] > maxLength) {
                    maxLength = table[x - 1][y + 1];
                    longestString = s.substring(x - 1, y + 2);
                }
                x = x - 1;
                y = y + 1;
            }
            
        }
//        printTable(table);
        return longestString;
    }

    /*
    打印table表
     */
    private static void printTable(int[][] table) {
        for (int[] x: table) {
            for (int y : x) {
                System.out.print( y + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcdasdfghjkldcba"));
        System.out.println(longestPalindrome("aba"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("babab"));
        System.out.println(longestPalindrome("zxcvbbndfgababadewrvqwrqqrwqvdfsgaaaags"));
        System.out.println(longestPalindrome("aaaaaaaaaaac"));

    }
}
