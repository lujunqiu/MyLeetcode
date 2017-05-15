import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lujunqiu on 17/5/15.
 * Description :Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * You may assume the dictionary does not contain duplicate words.
 * For example, given
 * s = "leetcode",dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 */
public class Test139_Word_Break {
    public static void main(String[] args) {
        String s = "ab";
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        System.out.println(wordBreak(s, arrayList));
    }

    /*
    说实话第一次做这个题目，没有想到用DP求解，参考了网上的对状态的定义之后自己完成了代码,顺利ac。
    由于题目给出的wordDict是List数据结构，查询复杂度为O(n)。
    我们做一个预处理，把待查字符在wordDict里面的子字符放入一个hashset中，方便后续的查询操作O(1);

    思路如下：
        状态定义：cache[i]表示前i个字符能不能被wordDict划分
        状态转义：cache[i]取决与两点：
            第一:如果wordDict内存在该字符，直接返回true，跳过第二步的判断；
            第二:如果wordDict内不存在该字符，则遍历从０到i-1，找到满足条件的j：cache[j] == true &&　s.substring(j+1,i+1)存在于wordDict之中；
            若找到一个满足上述条件的j，则cache[i] = true;否则为false;
     */
    static public boolean wordBreak(String s, ArrayList<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String s1 : wordDict) {
            if (s.contains(s1)) {
                set.add(s1);
            }
        }

        boolean[] cache = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.substring(0, i + 1))) {
                cache[i] = true;
                continue;
            }
            for (int k = 0; k <= i - 1; k++) {
                if (cache[k] == true && set.contains(s.substring(k + 1, i + 1))) {
                    cache[i] = true;
                }
            }
        }
        return cache[s.length() - 1];
    }
}
