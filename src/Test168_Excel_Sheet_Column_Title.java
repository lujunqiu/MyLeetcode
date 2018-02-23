/**
 * Created by lujunqiu on 2018/2/6.
 * Description:
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * For example:
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 */
public class Test168_Excel_Sheet_Column_Title {
    /**
     * 将十进制数转换成26进制数，十进制数1-26对应A-Z
     * 利用除k取余法，数学依据：
     * 将一个数x表示为几进制(假设为A进制)，从数学上来表示就是x=a1 * A^0 + a2 * A^1 + a3 * A^2 +... 所以x的A进制数为a3a2a1。
     * 按照上述的数学描述，我们每次除以A得到的余数就是当前最小位数的数值了，依次递归求解即可，注意char类型与int类型的转换对应关系
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        int temp = 0;
        while (n > 0) {
            temp = n % 26;
            if (temp == 0) {
                temp = 26;
            }
            stringBuilder.insert(0, (char) (temp + 64));
            n = (n - temp) / 26;
        }
        return stringBuilder.toString();
    }
}
