/**
 * Created by lujunqiu on 17/5/15.
 * Description:
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 *      Each child must have at least one candy.
 *      Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 */
public class Test135_Candy {
    static public int candy(int[] ratings) {

        int[] candy = new int[ratings.length];

        for (int i = 0; i < candy.length; i++) {
            candy[i] = 1;
        }

        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i] < ratings[i + 1]) {
                candy[i + 1] = candy[i] + 1;
            }
        }

//        for (int a1 :
//                candy) {
//            System.out.print(a1);
//        }
//        System.out.println();

        for (int i = ratings.length - 1; i >=1 ; i--) {
            if (ratings[i] < ratings[i - 1] && candy[i - 1] <= candy[i]) {
                candy[i - 1] = candy[i] + 1;
            }
        }
        int a = 0;
        for (int i = 0; i < candy.length; i++) {
            a = a + candy[i];
        }
//        for (int a1 :
//                candy) {
//            System.out.print(a1);
//        }
//        System.out.println();

        return a;
    }

    public static void main(String[] args) {
        int[] a = {4,2,3,4,1};
        System.out.println(candy(a));
    }
}
