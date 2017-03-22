/**
 * Created by lujunqiu on 17/3/16.
 * Description:
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class Test14_Longest_Common_Prefix {
    /**
     * 第一眼看到这个题的想法:
     * 字符串数组中所有字符串的最长公共前缀是数组中任意2个字符串的公共前缀的子集.于是,我们遍历数组然后依次比较即可.
     *
     * @param strs Accept
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            result = CommonPrefix(result, strs[i]);
        }
        return result;
    }

    /*
    找出2个字符串公共前缀
     */
    private static String CommonPrefix(String temp, String target) {
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

    /**
     * Leetcode提供的参考解法之一:
     * 上述的解法仍然有优化的空间:我们假设worst case 就是字符串数组的最短的字符串在数组的末尾,那么按照上述算法比较的次数就比较多了.针对这种情况,我们提出一种优化策略.
     * 在Leetcode Editorial 称作vertical scanning,意思就是我们每次比较数组中所有字符串的特定位置的字符是否相等,在比较的过程中,逐渐完善最长公共前缀.
     * 这样一来,公共前缀是慢慢增加的,算法的比较次数也由较短的字符串决定的.
     *
     * @param strs
     * @return
     */
    public static String EditorialSolution(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        String[] s = {"123555", "12333","1234","1234345","123"};
        System.out.println(longestCommonPrefix(s));
        System.out.println(EditorialSolution(s));
    }
}
