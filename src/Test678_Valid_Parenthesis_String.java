/**
 * Created by lujunqiu on 17/9/17.
 * Description:
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid.
 * We define the validity of a string by these rules:
 * 1.Any left parenthesis '(' must have a corresponding right parenthesis ')'
 * 2.Any right parenthesis ')' must have a corresponding left parenthesis '('
 * 3.Left parenthesis '(' must go before the corresponding right parenthesis ')'
 * 4.'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string
 * 5.An empty string is also valid
 * Example:
 * Input: "(*))"
 * Output: True
 */
public class Test678_Valid_Parenthesis_String {
    /*
    由于'*'可以视作'(',')'或者空字符,那么我们可以在判断是否是合理的括号的时候,去寻找'('可能的界限,low表示下界,high表示上界,然后判断界限是否合理.
    只要low表示的下界不大于0,而且high表示的上界不小于0,那么我们一定可以将'*'变成所需的括号或者空字符来完成匹配.
    也就是说,我们假设2种极端情况,'*'全部为'('或者全部为')',这2种情况都必须同时可能完成匹配才能返回true.
     */
    static public boolean checkValidString(String s) {
        int low = 0;//'('的下界
        int high = 0;//'('的上界
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                low++;
                high++;
            } else if (s.charAt(i) == ')') {
                if (low > 0) {
                    low--;
                }
                high--;
            } else {
                if (low > 0) {//将'*'视作')'找下界,如果下界等于0,则视为空字符
                    low--;
                }
                high++;//将'*'视作'('找上界
            }
            if (high < 0) {//相当于在没有'*'的括号匹配算法中的,栈内必须有'('或者为空,如果小于0,表示存在右括号失去匹配,返回false
                return false;
            }
        }
        return low == 0;
    }

    public static void main(String[] args) {
        System.out.println(checkValidString("(())()()****()()*(*(*)*))*(*"));
    }
}
