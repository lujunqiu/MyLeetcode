import java.util.ArrayList;

/**
 * Created by lujunqiu on 17/9/24.
 * Description:
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, )
 * to get the value of 24.
 * Example 1:
 * Input: [4, 1, 8, 7]
 * Output: True
 * Explanation: (8-4) * (7-1) = 24
 * Note:
 * The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 * Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 * You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 */
public class Test679_24_Game {
    /*
    题目类似我们之前玩的24点的游戏,通过加减乘除(允许括号)来将4个数字运算之后凑成24.
    这是一个搜索可行解的问题,首先看看解空间有多大:首先4个数任选2个数有12种选择(4选2的排列),然后可以执行4种运算的一种,得到3个数,进行同样的操作,于是我们可以得到总共解空间为:12*4*6*4*2*4=9216种可能的情况.
    其实在求解空间的时候我们已经用到了深度优先搜索的思想,这里基于数学的原理有一个简单的优化,加法与乘法是满足交换律的,交换运算数的位置结果不变.
    于是,我们可以写出递归的深度优先搜索解空间的方法:每次在候选的数的集合中选择2个数,进行所有可能的运算,保存运算结果放入数的集合,递归的进行下一步,直到集合中只有一个数判断是否为24即可.
     */
    public boolean judgePoint24(int[] nums) {
        ArrayList<Double> numbers = new ArrayList<>();
        for (int num : nums) {
            numbers.add((double) num);
        }
        return dfs(numbers);
    }

    private boolean dfs(ArrayList<Double> numbers) {
        if (numbers.size() == 0) {
            return false;
        }
        if (numbers.size() == 1) {
            return Math.abs(numbers.get(0) - 24) < 1e-6;//计算机表示分数是有误差的,用于解决类似6 / (1 - 2/3) = 24 的情况
        }

        for (int i = 0; i < numbers.size();i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                ArrayList<Double> numbers2 = new ArrayList<>();
                for (int i1 = 0; i1 < numbers.size(); i1++) {
                    if (i1 != i && i1 != j) {
                        numbers2.add(numbers.get(i1));
                    }
                }
                for (int k = 0; k < 6; k++) {
                    if (k == 0) {
                        numbers2.add(numbers.get(i) + numbers.get(j));
                    }
                    if (k == 1) {
                        numbers2.add(numbers.get(i) * numbers.get(j));
                    }
                    if (k == 2) {
                        numbers2.add(numbers.get(i) - numbers.get(j));
                    }
                    if (k == 3) {
                        numbers2.add(numbers.get(j) - numbers.get(i));
                    }
                    if (k == 4) {
                        if (numbers.get(i) != 0) {
                            numbers2.add(numbers.get(j) / numbers.get(i));
                        } else {
                            continue;
                        }
                    }
                    if (k == 5) {
                        if (numbers.get(j) != 0) {
                            numbers2.add(numbers.get(i) / numbers.get(j));
                        } else {
                            continue;
                        }
                    }

                    if (dfs(numbers2)) return true;
                    numbers2.remove(numbers2.size() - 1);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Test679_24_Game test679_24_game = new Test679_24_Game();
        int[] ints = {1,2,1,2};
        System.out.println(test679_24_game.judgePoint24(ints));
    }



}
