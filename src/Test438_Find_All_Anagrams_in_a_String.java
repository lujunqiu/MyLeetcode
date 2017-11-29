import java.util.ArrayList;
import java.util.List;

/**
 * Created by lujunqiu on 17/11/22.
 * Description:
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 * Example 1:

 * Input:
 * s: "cbaebabacd" p: "abc"

 * Output:
 * [0, 6]

 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 */
public class Test438_Find_All_Anagrams_in_a_String {
    /**
    博客中有解析:https://lujunqiu.github.io/2017/11/28/关于SubString的解决方案/
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return result;
        }
        int[] hash = new int[256];
        int l = 0, r = 0;
        int count = p.length();

        for (char c : p.toCharArray()) {
            hash[c]++;
        }

        while (r < s.length()) {
            if (hash[s.charAt(r)] >= 1) {
                count--;
            }
            hash[s.charAt(r)]--;
            r++;

            if (count == 0) {
                result.add(l);
            }

            if (r - l == p.length()) {
                if (hash[s.charAt(l)] >= 0) {
                    count++;
                }
                hash[s.charAt(l)]++;
                l++;
            }
        }
        return result;
    }
}
