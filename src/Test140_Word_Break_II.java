import java.util.*;


/**
 * Created by lujunqiu on 17/6/30.
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * You may assume the dictionary does not contain duplicate words.
 * Return all such possible sentences.
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 */
public class Test140_Word_Break_II {
    /*
           第140题是139题的扩展，我们知道139解决的问题是判断一个字符串能否被切分，而这个题要求我们在之前的基础上找到如何切分方法。
           当然，我们可以使用139题的思路，通过动态规划找到可切分点(DP表中为true的点)，然后通过这个数据找到具体切分的方法。
           这里我们使用深度优先搜索来解决这个问题，因为我们可以参考经典的全排列问题是如何通过深度优先搜索解决，来解决这个相似的问题。
           深度优先搜索的思路很简单，但是要写出bug free的代码，还是要花一点时间来调试。
           在使用深度优先搜索的过程中，可以从字符前往后寻找切分点，也可以从后往前。我一开始是从前往后搜索的，但是调试不通，最后用从后往前来搜索切分点，并且在开头设定一个终止标识。
            */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> stringList = new ArrayList<>();//保存最后的结果
        List<Integer> list = new ArrayList<>();//保存所有可能的切分点下标
        //以下是139题动态规划的过程
        Set<String> set = new HashSet<>();
        boolean[] cache = new boolean[s.length()];
        for (String s1 : wordDict) {
            if (s.contains(s1)) {
                set.add(s1);
            }
        }
        list.add(-1);
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.substring(0, i + 1))) {
                cache[i] = true;
                list.add(i);
                continue;
            }
            for (int k = 0; k <= i - 1; k++) {
                if (cache[k] == true && set.contains(s.substring(k + 1, i + 1))) {
                    cache[i] = true;
                    if (!list.contains(i)) {
                        list.add(i);
                    }
                }
            }
        }
        if(cache[s.length() - 1] == false){
            return stringList;
        }
        //以上，139题动态规划得到一个List<Integer>保持所有可能的切分点下标，后面就是深度优先搜索来寻找解。
        Stack<String> stack = new Stack<>();//栈来保存深度优先搜索过程的中间结果
        dfs(list.size() - 1, s, wordDict, list, stack,stringList);
        return stringList;
    }

    /*
    深度优先搜索的递归函数参数设计的时候，需要考虑到中间结果的保存，递归的深度等等，只能通过自己不断的调试来完善！
     */
    static void dfs(int n, String s, List<String> wordDict, List<Integer> list, Stack<String> stack,List<String> stringList) {
        for (int i = n; i >= 1 ; i--) {
            if (wordDict.contains(s.substring(list.get(i - 1) + 1, list.get(n) + 1))) {
                stack.push(s.substring(list.get(i - 1) + 1, list.get(n) + 1));
                dfs(i - 1, s, wordDict, list, stack,stringList);
                stack.pop();//这一步很关键！理解递归调用的过程！
            }
        }
        if (n == 0) {//边界条件，输出搜索的结果
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < stack.size(); i++) {
                if (i != stack.size() - 1) {
                    stringBuffer.insert(0, " " + stack.get(i));
                } else {
                    stringBuffer.insert(0, stack.get(i));
                }
            }
            stringList.add(stringBuffer.toString());
        }
        return;
    }
}
