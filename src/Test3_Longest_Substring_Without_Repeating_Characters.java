import java.util.HashMap;
import java.util.Map;
import java.util.Hashtable;

/**
 * Created by lujunqiu on 17/3/10.
 * Description:
 * Given a string, find the length of the longest substring without repeating characters.
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class Test3_Longest_Substring_Without_Repeating_Characters {
    /*
    暴力搜索出所有不含相同字符的子字符串,然后找到最长的子字符串
    tips:借助hashmap来存储子字符串,同时保证每次插入新字符的时候不含相同的字符,hashmap的containsKey()方法的复杂度为O(1)
    时间复杂度为O(n^2)
     */
    public static int BruteForce(String s) {
        char[] str = s.toCharArray();
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int count = 0;
            Hashtable<Character, Integer> hash = new Hashtable<Character, Integer>();
            for (int j = i; j < str.length; j++) {
                if (!hash.containsKey(str[j])) {
                    hash.put(str[j], j);
                } else {
                    count = hash.get(str[j]) - i + 1;
                    break;
                }
            }
            if (count > max)
                max = count;
        }
        return max;
    }

    /*
    我们其实不需要搜索所有的不含相同字符的子字符串,根据问题的要求我们可以少搜索一些子字符串
    假设:
        y...x...x是我们已经搜索到的不含相同字符的子字符串(字符y开头),并且遇到了一个相同字符x,这时我们按照之前的做法是重新从y后一个字符开始继续搜索.
        但是我们可以发现在上述字符串中以y到x中间的任意字符开头的不含相同字符的子字符串的长度是不可能超过以求得的y...x...x字符串的.
        所以我们可以跳过y到x中间的搜索,直接从x后一位的字符开始搜索.
        但是这只是优化了方法,时间复杂度严格意义上还是O(n^2).
    因为函数只需要输出最长的长度,我们可以只用一个hashmap来保存当前搜索的子字符串
    那么有没有O(n)的方法呢?
     */
    public static int OptimizedBruteForce(String s) {
        char[] arr = s.toCharArray();
        int len = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], i);
            } else {
                len = len > map.size() ? len : map.size();
                i = map.get(arr[i]);
                map.clear();
            }
        }
        return Math.max(len, map.size());
    }

    /*
    同样,按照上面的思路,但是我们换一个角度来考虑,就可以把时间复杂度降到O(n).上面2个方法中,index j是会回溯的,但是我们发现这个回溯其实是重复计算的,完全可以避免.
    这里引入一个在array/string 问题中很常见的模型---sliding window:一个可以滑动的窗口.
    考虑到OptimizedBruteForce方法中的优化,我们适用hashmap来表示滑动窗口(如果不考虑存储键值对可以用hashset).
    滑动过程:首先,i=j窗口大小为1,然后i不变,j向右滑动,新遇到的字符存入hashmap,直到map中有重复的字符,j停止滑动.
    这时,i直接滑动到与j重复的字符的下一个字符(需要hashmap来保存这一信息),然后继续滑动j.直到i或者j到达字符串的末尾,过程中保留最优解即可.
     */
    public static int SlidingWindow(String s) {
        int len = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int i = 0,j = 0 ;
        while (j < s.length() && i < s.length()) {
            if (!map.containsKey(s.charAt(j))) {
                map.put(s.charAt(j), j);

                len = len > (j - i + 1) ? len : j - i + 1;
                j++;
            } else {
                for (int k = i; k < map.get(s.charAt(j)); k++) {
                    map.remove(s.charAt(k));
                }
                i = map.get(s.charAt(j)) + 1;
                map.remove(s.charAt(j));
            }
        }
        return len;
    }
    
    /*
    最佳参考：Leetcode Editorial Solution。
    In fact, it could be optimized to require only n steps.
    Instead of using a set to tell if a character exists or not, we could define a mapping of the characters to its index.
    Then we can skip the characters immediately when we found a repeated character.
    The reason is that if s[j]s[j] have a duplicate in the range [i, j)[i,j) with index j'j′ , we don't need to increase ii little by little.
    We can skip all the elements in the range [i, j'][i,j′ ] and let ii to be j' + 1j′ +1 directly.
    */
     public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
    
    public static void main(String[] args) {
        System.out.println(SlidingWindow("abcabcbb"));
        System.out.println(SlidingWindow("bbbbbb"));
        System.out.println(SlidingWindow("pwwkew"));
    }
}
