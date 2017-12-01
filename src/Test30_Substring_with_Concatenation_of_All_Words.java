import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by lujunqiu on 17/11/27.
 * Description:
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 * You should return the indices: [0,9].
 * (order does not matter).
 */
public class Test30_Substring_with_Concatenation_of_All_Words{
        /**
         解决这个问题,首先要解决76,438题. 参考76,438题的解法:https://lujunqiu.github.io/2017/11/28/关于SubString的解决方案/
         思路:利用滑动窗口和HashMap来解决.但是不同的是,搜索的起点有多个,才能保证搜索到所有可能的解.而且HashMap中保存的是String而不是char,但是思路是一样的,没有改变.
         */
        public List<Integer> findSubstring(String S, String[] L) {
            List<Integer> res = new LinkedList<>();
            if (L.length == 0 || S.length() < L.length * L[0].length()) return res;
            int N = S.length();
            int M = L.length;
            int wl = L[0].length();
            Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();
            for (String s : L) {//初始化原始的HashMap,统计其中字符串的个数(参考438题,在这我们可以将String理解成char)
                if (map.containsKey(s)) map.put(s, map.get(s) + 1);
                else map.put(s, 1);
            }
            String str, tmp;
            for (int i = 0; i < wl; i++) {
                int count = 0;
                int start = i;//搜索的起点有多个,从0到wl-1,才能保证滑动窗口遍历到所有可能的子字符串
                for (int r = i; r + wl <= N; r += wl) {//滑动窗口每次滑动的步长为wl
                    str = S.substring(r, r + wl);
                    if (map.containsKey(str)) {
                        if (curMap.containsKey(str)) curMap.put(str, curMap.get(str) + 1);
                        else curMap.put(str, 1);

                        if (curMap.get(str) <= map.get(str)) count++;
                        while (curMap.get(str) > map.get(str)) {//当前窗口不满足要求,缩小窗口
                            tmp = S.substring(start, start + wl);
                            curMap.put(tmp, curMap.get(tmp) - 1);
                            start += wl;

                            if (curMap.get(tmp) < map.get(tmp)) count--;

                        }
                        if (count == M) {//说明当前滑动窗口中的字符串满足题目的要求,可以缩小窗口大小,步长也是wl
                            res.add(start);
                            tmp = S.substring(start, start + wl);
                            curMap.put(tmp, curMap.get(tmp) - 1);
                            start += wl;
                            count--;
                        }
                    } else {//根据题目的要求,遇到不在Map中的字符直接中断当前窗口,重新搜索
                        curMap.clear();
                        count = 0;
                        start = r + wl;
                    }
                }
                curMap.clear();
            }
            return res;
        }
}
