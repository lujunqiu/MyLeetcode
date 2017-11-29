/**
 * Created by lujunqiu on 17/11/22.
 * Description:
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".

 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".

 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class Test76_Minimum_Window_Substring {
    /**
    博客中有解析:https://lujunqiu.github.io/2017/11/28/关于SubString的解决方案/
     */
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        int[] hash = new int[256];

        for (char c : t.toCharArray()) {
            hash[c]++;
        }

        int l = 0, r = 0;
        int minWindowHead = 0;
        int count = t.length();
        int d = Integer.MAX_VALUE;

        while (r < s.length()) {
            if (hash[s.charAt(r)] >= 1) {
                count--;
            }
            hash[s.charAt(r)]--;
            r++;

            while (count == 0) {
                if (hash[s.charAt(l)] == 0) {
                    count++;
                }
                if (r - l < d) {
                    d = r - l;
                    minWindowHead = l;
                }
                hash[s.charAt(l)]++;
                l++;
            }

        }
        return d == Integer.MAX_VALUE ? "" : s.substring(minWindowHead, d + minWindowHead);
    }
}
