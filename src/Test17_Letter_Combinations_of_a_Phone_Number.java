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

    }

    /*
    拿到题目第一想法就是深度优先搜索.
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

     */
    public List<String> ImprovedletterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        String[] keys={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        //Example is 45
        StringBuilder s=new StringBuilder();
        if(digits.length()!=0){
            findWordsIterative(digits,result,s,keys,0);
        }
        //System.out.println(result.toString());
        return result;
    }

    private static void findWordsIterative(String digits, List<String> result,StringBuilder s,String[] keys, int digit) {
        char[] res = new char[digits.length()];
        int digitsLength = digits.length();
        for(int i=0;i<digits.length();i++){
            res[i]=keys[digits.charAt(i)-'0'].charAt(0);
        }

        while(true){
            //System.out.println(resNew);
            result.add(new String(res));

            for(int i=digitsLength-1;i>=-1;i--){
                if(i==-1){
                    return;
                }
                int j = keys[digits.charAt(i)-'0'].length();
                String x = keys[digits.charAt(i)-'0'];
                if(j-1>=0 && (res[i]==x.charAt(j-1) || digits.charAt(i)-'0'==0 || digits.charAt(i)-'0'==1)){
                    res[i]=x.charAt(0);
                }else if(j-2>=0 && res[i]==x.charAt(j-2)){
                    res[i]=x.charAt(j-1);
                    break;
                }else if(j-3>=0 && res[i]==x.charAt(j-3)){
                    res[i]=x.charAt(j-2);
                    break;
                }else if(j-4>=0 && res[i]==x.charAt(j-4)){
                    res[i]=x.charAt(j-3);
                    break;
                }
            }
        }
    }

    /*

     */
    public List<String> NonRecursionletterCombinations(String digits) {
        List<String> res = new ArrayList<>();
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

            List<String> res2 = new ArrayList<>();
            for (int j = 0; j < str.length(); j++) {
                for (String pre : res) {
                    res2.add(pre + str.charAt(j));
                }
            }
            res = res2;
        }

        return res;
    }
}
