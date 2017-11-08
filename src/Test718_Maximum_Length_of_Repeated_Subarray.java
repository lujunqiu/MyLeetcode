/**
 * Created by lujunqiu on 17/11/3.
 * Description:
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 * Example 1:
 * Input:
    A: [1,2,3,2,1]
    B: [3,2,1,4,7]
    Output: 3
    Explanation:
    The repeated subarray with maximum length is [3, 2, 1].
 */
public class Test718_Maximum_Length_of_Repeated_Subarray {
    /**
    动态规划的简单应用题.
     状态定义:dp[i][j],以A[i],B[j]结尾的最大的相同子数组的长度值
     状态转移:如果A[i]==B[j],dp[i][j] = dp[i-1][j-1]+1.根据状态的定义,很容易写出状态转移方程
     */
    public int findLength(int[] A, int[] B) {
        //状态定义:dp[i][j],以A[i],B[j]结尾的最大相同子数组的长度值
        int[][] dp = new int[A.length + 1][B.length + 1];//trick:边界的0值在初始化的时候就已经完成了dp[0][0],dp[0][1],dp[1][0]等于0
        int ans = 0;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = ans > dp[i][j] ? ans : dp[i][j];
                }
            }
        }
        return ans;
    }

    /**
     * trick:
    对于上述动态规划算法,我们在空间复杂度上可以有优化,利用dp[]的一维数组,优化思路与背包问题中dp数组的类似:因为dp[i][j]的值仅仅依赖与dp[i-1][j-1]的值,我们可以将dp[j]代表dp[i][j],所以dp[j-1]代表dp[i-1][j-1].
     这就要求我们从后往前遍历B数组,A数组从前往后遍历.可以仔细体会!!!背包问题中也有类似的情况,将二维的dp数组变成一维的数组!!!

     效率比上述的算法提高了很多!
     */
    public int optimizedFindLength(int[] A, int[] B) {
        int[]dp = new int[B.length + 1];
        int ans = 0;
        for (int i = 1; i <= A.length; i++) {
            for (int j = B.length; j > 0; j--) {
                if (A[i - 1] == B[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                    ans = ans > dp[j] ? ans : dp[j];
                } else {
                    dp[j] = 0;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Test718_Maximum_Length_of_Repeated_Subarray test = new Test718_Maximum_Length_of_Repeated_Subarray();
        int[] A = new int[]{1,2,3,2,1};
        int[] B = new int[]{3,2,1,4,7};
        System.out.println(test.findLength(A, B));
    }
}
