import java.util.Stack;

/**
 * Created by lujunqiu on 17/4/12.
 * Description:
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */
public class Test32_Longest_Valid_Parentheses {
    /*
    我的解法是:看到括号匹配的问题,首先想到的肯定是用栈来解决.
    使用了2个栈,一个保持字符,一个保持字符所在的位置信息.
    遍历字符串,遇到'('则入栈,同时将对应的位置i入栈.我们要注意的是如果'('前面是一个已经匹配了的')'那么这个'('的位置应该是前一个'('的位置,这样才能求出最长的匹配的括号序列.
    遇到')',弹出顶部的'('并且通过位置的信息来计算长度,同时保存对应的弹出的'('的位置以及记录这次匹配成功的信息.

    但是,我提交了这个代码,通过了所有229个test case,在最后一个case的时候出现了Time Limited Exceed,就很尴尬.....从数学意义来讲是O(n)的复杂度,可能过程写的比较繁琐吧.或许不应该用2个栈?
     */
    static public int longestValidParentheses(String s) {
        Stack<Character> characterStackStack = new Stack<>();
        Stack<Integer> indexStack = new Stack<>();
        boolean ifConnect = false;
        int longest = 0;
        int temp = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                characterStackStack.push(c);
                if (i > 0 && s.charAt(i - 1) == ')' && ifConnect) {
                    indexStack.push(temp);
                } else {
                    indexStack.push(i);
                }
                ifConnect = false;
            } else {
                if (characterStackStack.empty()) {
                    ifConnect = false;
                    continue;
                } else {
                    characterStackStack.pop();
                    temp = indexStack.pop();
                    int sublength = i - temp + 1;
                    if (longest < sublength) {
                        longest = sublength;
                    }
                    ifConnect = true;
                }

            }
        }
        return longest;
    }

    /*
    这里我们改进一下上面的方法,我们只用一个栈来保持位置信息,整体思路一样.
    注意,我们用index来记录如果上次是匹配成功的')'对应的'('的位置信息,而且这个参数只保存一轮.

    但是很遗憾,这个方法还是不能AC,最后一个用例还是超时,于是我用C++ 一模一样的思路写了一遍,C++的版本就AC了.有点坑- -
     */
    static public int improved(String s) {
        Stack<Integer> stack = new Stack<>();
        int longest = 0;
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                if (index > 0) {
                    stack.push(index);
                    index = -1;
                } else {
                    stack.push(i);
                }
            } else {
                if (stack.empty()) {
                    index = -1;
                    continue;
                } else {
                    index = stack.pop();
                    longest = i - index + 1 > longest ? i - index + 1 : longest;
                }
            }
        }
        return longest;
    }

    /*
    这里给出Leetcode的Editorial Solution,思路是使用动态规划来求解.
    状态定义:
    We make use of a dp array where ith element of dp represents the length of the longest valid substring ending at ith index.
    也就是说,以ith位置字符结尾的最长的匹配的子字符串长度.
    根据状态的定义,我们知道如果s[i] =='(',那么dp[i] = 0;

    状态转移:
    case 1 : s[i] = ')' && s[i - 1] = '(' ; i.e. string looks like ".......()"
    dp[i] = dp[i - 2] + 2;

    case 2 : s[i] = ')' && s[i - 1] = ')' ; i.e string looks like "...((...))"
    这个时候,如果与s[i]的右括号对应的位置,也就是当前位置减去s[i - 1]右括号对应的匹配子字符串的长度再减1的位置 是左括号的话,最长的长度为 dp[i -1] 加 2 还要加上dp[i - dp[i - 1] - 2] .
    dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
      */
    static public int DP(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    public static void main(String[] args) {
        System.out.println(improved("()"));
        System.out.println(longestValidParentheses(")()())(())()))(("));
        System.out.println(DP("()()()()(()((((())"));
    }


}
