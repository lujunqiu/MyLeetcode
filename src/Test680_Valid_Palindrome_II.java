/**
 * Created by lujunqiu on 17/9/17.
 * Description:
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * Example 1:
 * Input: "aba"
 * Output: True
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 */
public class Test680_Valid_Palindrome_II {
    /*
    思路:设置两个指针,从字符2侧开始比较,如果不相等,则尝试删除节点,此时不相同的2个节点均有可能被删除,然后在判断删除节点之后的子字符串是否回文即可.
     */
    public boolean validPalindrome(String s) {
        int l = -1, r = s.length();
        while (++l < --r)
            if (s.charAt(l) != s.charAt(r)) return isPalindromic(s, l, r + 1) || isPalindromic(s, l - 1, r);
        return true;
    }

    /**
     * 判断给定字符串的子字符串是否回文
     * @param s
     * @param l
     * @param r
     * @return
     */
    public boolean isPalindromic(String s, int l, int r) {
        while (++l < --r)
            if (s.charAt(l) != s.charAt(r)) return false;
        return true;
    }

}
