import java.util.HashMap;
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
    public int BruteForce(String s) {
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
    public int OptimizedBruteForce(String s) {
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

    public int Accepted(String s) {
        int[] occ = new int[256];
        int max = 0, counter = 0, start = 1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (occ[ch] >= start) {
                counter -= occ[ch] - start + 1;
                start = occ[ch] + 1;
            }
            occ[ch] = i + 1;
            max = Math.max(max, ++counter);
        }
        return max;
    }
}
