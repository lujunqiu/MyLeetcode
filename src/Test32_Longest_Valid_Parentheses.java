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
                ifConnect= false;
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

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())(())()))(("));
    }
}
