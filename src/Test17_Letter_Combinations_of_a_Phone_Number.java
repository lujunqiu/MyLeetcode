import java.util.*;


/**
 * Created by lujunqiu on 17/8/25.
 * Description:
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class Test17_Letter_Combinations_of_a_Phone_Number {

    public static void main(String[] args) {
        for (String s : NonRecursionletterCombinations("23")) {
            System.out.println(s);
        }
    }

    /*
    拿到题目第一想法就是深度优先搜索,类比求0-9中某个数字的全排列问题.
    在设计深度优先搜索递归的时候,需要注意在访问节点返回的时候(回溯的时候)将是否访问的标识位改回来,并且在当次深度优先搜索过程的结果集合中(StringBuilder)删去当前节点.
    但是,下面这份代码虽然AC了,可是效率不高,属于在AC的代码中排在很后面的那种.我们可以在java代码层面重构一下代码,提高运行的效率,比如map的使用可以替换掉.
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();

        Map<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});


        char[] digits1 = digits.toCharArray();
        if (digits1.length == 0) {
            return list;
        }
        boolean[][] bbb = new boolean[digits1.length][];
        for (int i = 0; i < digits1.length; i++) {
            bbb[i] = new boolean[map.get(digits1[i]).length];
        }
        StringBuilder result = new StringBuilder();
        dfs(0, digits.length(), digits1, map,result,list,bbb);
        return list;
    }

    static private void dfs(int i , int length , char[] digits ,Map<Character , char[]> map1 , StringBuilder result , List<String> list,boolean[][] bbb) {
        if (i == length ) {
            list.add(result.toString());
            System.out.println(result);
            return;
        }
        char num = digits[i];
        for (int i1 = 0; i1 < map1.get(num).length; i1++) {
            if (bbb[i][i1] == false) {
                result.append(map1.get(num)[i1]);
                bbb[i][i1] = true;
                dfs(i + 1, length, digits, map1, result,list,bbb);
                bbb[i][i1] = false;
                result.deleteCharAt(result.length() - 1);
            }
        }
    }

    /*
    非递归算法:类似广度优先搜索的思想
    关键在于:使用2个List<String>,每次从上次的结果List<String>添加字符扩展得到当前结果的List<String>,然后将当前结果赋值给"上次结果List<String>",继续循环操作,直到结束.
     */
    static public List<String> NonRecursionletterCombinations(String digits) {
        List<String> res = new ArrayList<>();//最终结果的字符串集合
        if (digits == null || digits.length() == 0) {
            return res;
        }

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");map.put('4', "ghi");map.put('5', "jkl");map.put('6', "mno");
        map.put('7', "pqrs"); map.put('8', "tuv");
        map.put('9', "wxyz");

        res.add("");
        for (int i = 0; i<digits.length(); i++) {

            if (!map.containsKey(digits.charAt(i))) continue;

            String str = map.get(digits.charAt(i));

            List<String> res2 = new ArrayList<>();//保存中间结果的字符串集合

            for (int j = 0; j < str.length(); j++) {
                for (String pre : res) {
                    res2.add(pre + str.charAt(j));//从上一个字符串集合扩展得到的新的字符串集合
                }
            }
            res = res2;//更新最终结果的字符串集合
        }

        return res;
    }
}
