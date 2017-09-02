import java.util.*;
/**
 * Created by lujunqiu on 17/9/1.
 * Description:
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * [
    "((()))",
    "(()())",
    "(())()",
    "()(())",
    "()()()"
    ]
 */
public class Test22_Generate_Parentheses {
    /*
    看到这个题目,我首先做了一个简单的抽象,用0代表'(',用1代表')',我们要求的结果就是一个01的整型数组,里面要保证在任何索引位置之前的0的数量要大于或者等于1的数量即可.
    解题思路是深度优先搜索,类比求0-9某个数字的全排列问题的解法,我们可以得到该题的递归解法,不同的是我们在递归的每一层只有2个可能的结果0或者1,根据条件判断即可.
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        int[] result = new int[n * 2];
        dfs(res, result, 0, 0, 0);
        return res;
    }

    public void dfs(List<String> res, int[] result,int left, int right, int i) {
        if (i == result.length && left == right) {//深度搜索结果输出,将我们抽象的整型数组还原括号的字符输出即可
            StringBuffer stringBuffer = new StringBuffer();
            for (int i1 : result) {
                if (i1 == 0) {
                    stringBuffer.append('(');
                } else {
                    stringBuffer.append(')');
                }
            }
            res.add(stringBuffer.toString());
            return;
        }
        //只要0(左括号)的数量没有超过输入的括号对数,那么任何位置都可以添加0(左括号)
        if (left < result.length / 2.0) {//这里要除2.0,如果是2的话结果的小数点会被抹掉
            result[i] = 0;
            dfs(res, result, left + 1, right, i + 1);
        }

        if (right < left) {//只有0(左括号)的数量大于1(右括号)的数量的时候才可以添加1(右括号)
            result[i] = 1;
            dfs(res, result, left, right + 1, i + 1);
        }
    }

}
