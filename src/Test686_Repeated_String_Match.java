/**
 * Created by lujunqiu on 17/10/12.
 * Description:
 * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.
 * For example, with A = "abcd" and B = "cdabcdab".
 * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").
 */
public class Test686_Repeated_String_Match {
    /*
    字符串的操作,思路很简单,熟悉关于String相关函数的使用即可
     */
    public int repeatedStringMatch(String A, String B) {
        StringBuilder stringBuilder = new StringBuilder(A);
        int times = 1;
        int a = A.length();
        int b = B.length();
        while (stringBuilder.length() < B.length()) {
            stringBuilder.append(A);
            times++;
        }
        if (stringBuilder.indexOf(B) != -1) {
            return times;
        } else {
            stringBuilder.append(A);
            return stringBuilder.indexOf(B) == -1 ? -1 : times + 1;
        }
    }
}
