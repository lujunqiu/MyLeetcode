import java.util.HashSet;
import java.util.Set;

/**
 * Created by lujunqiu on 17/10/11.
 * Description:
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how
 * many times a digit can be reused.
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 * Example 1:

 Input: "19:34"
 Output: "19:39"
 Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.
 It is not 19:33, because this occurs 23 hours and 59 minutes later.
 Example 2:

 Input: "23:59"
 Output: "22:22"
 Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
 It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
 */
public class Test681_Next_Closest_Time {
    int h;//当前时间的小时数
    int m;//当前时间的分钟数
    int dis = Integer.MAX_VALUE;
    String result = null;//保存结果
    /*
    我们最多有4个不同的数字,那么最多有4*4*4*4=256种时间,我们使用深度优先搜索,搜索所有可能的解,然后在搜索过程中找到最优解.
    在搜索的过程中,如果出现无效的时间比如33:22这种,就可以立即停止这一搜索路径,可以优化解空间的搜索范围.
     */
    public String nextClosestTime(String time) {
        String[] strings = time.split(":");
        Set<Character> set = new HashSet<>();
        for (String string : strings) {
            for (char c : string.toCharArray()) {
                set.add(c);
            }
        }
        int[] digits = new int[set.size()];//得到所有不用的数字集合
        for (int i = 0; i < set.size(); i++) {
            digits[i] = Integer.parseInt(set.toArray()[i].toString());
        }

        h = Integer.parseInt(strings[0]);//记录输入的时间的小时数
        m = Integer.parseInt(strings[1]);//记录输入的时间的分钟数

        dfs(digits, 0, new int[4]);

        return result;
    }

    private void dfs(int[] digits, int i, int[] ans) {
        if (i == 4) {
            int hour = ans[0] * 10 + ans[1];
            int min = ans[2] * 10 + ans[3];
            if (hour < 24 & min < 60) {
                int distance = distance(hour, min);//计算得到的解与当前时间的"距离"
                if (dis > distance) {
                    dis = distance;
                    result = form(hour,min);
                }
            }
        } else {
            for (int j = 0; j < digits.length; j++) {
                ans[i] = digits[j];
                if (i == 1) {//如果小时数超过24,则停止这条搜索路径
                    int hour = 10 * ans[0] + ans[1];
                    if (hour >= 0 && hour <= 23) dfs(digits, i + 1, ans);
                }
                else if (i == 3) {//如果分钟数超过60,则停止这条搜索路径
                    int minu = 10 * ans[2] + ans[3];
                    if (minu >= 0 && minu <= 59) dfs(digits, i + 1, ans);
                }
                else {
                    dfs(digits, i + 1, ans);
                }
            }
        }
    }

    /*
    计算输入的解与当前解之间的差距,注意这是在计算时间的"距离"
     */
    private int distance(int hour, int min) {
        int c2o = 24 * 60 - h * 60 - m;
        int n2o = 24 * 60 - hour * 60 - min;
        return n2o < c2o ? c2o - n2o : c2o - n2o + 24 * 60;
    }

    /*
    将输入的小时数与分钟数组合为字符串
     */
    private String form(int hour, int min) {
        StringBuilder stringBuilder = new StringBuilder();
        if (hour <= 9) {
            stringBuilder.append("0" + hour);
        } else {
            stringBuilder.append("" + hour);
        }
        stringBuilder.append(":");
        if (min <= 9) {
            stringBuilder.append("0" + min);
        } else {
            stringBuilder.append("" + min);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Test681_Next_Closest_Time test681_next_closest_time = new Test681_Next_Closest_Time();
        System.out.println(test681_next_closest_time.nextClosestTime("19:35"));
    }
}
