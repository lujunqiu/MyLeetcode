/**
 * Created by lujunqiu on 17/3/16.
 * Description:
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class Test_14_Longest_Common_Prefix {
    /**
     * 第一眼看到这个题的想法:
     * 字符串数组中所有字符串的最长公共前缀是数组中任意2个字符串的公共前缀的子集.于是,我们遍历数组然后依次比较即可.
     * @param strs
     * Accept
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String result = strs[0].length() == 0 ? "" : strs[0];
        for (int i = 1; i < strs.length; i++) {
            result = CommonPrefix(result, strs[i]);
        }
        return result;
    }
    /*
    找出2个字符串公共前缀
     */
    private static String CommonPrefix(String temp,String target){
        StringBuffer prefix = new StringBuffer();
        int length = temp.length() > target.length() ? target.length() : temp.length();
        for (int i = 0; i < length; i++) {
            if (temp.charAt(i) == target.charAt(i)) {
                prefix.append(temp.charAt(i));
            } else {
                break;
            }
        }
        return prefix.toString();
    }

    public static void main(String[] args) {
        String[] s = {"123","12"};
        System.out.println(longestCommonPrefix(s));
    }
}
