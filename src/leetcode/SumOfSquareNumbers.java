package leetcode;

/**
 * Created by CX无敌 on 2017-07-05.
 */
public class SumOfSquareNumbers {
    public boolean judgeSquareSum(int c) {
        for (int i = 0; i <= Math.ceil(Math.sqrt(c)/Math.sqrt(2.0)) ; i++)
            if (Math.pow(i,2) + Math.pow((int)Math.sqrt(c-Math.pow(i,2)),2) == c)
                return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new SumOfSquareNumbers().judgeSquareSum(2));
    }
}
