package dsa.chapter02;

public class MaxSubSum {

    public static int maxSubSum1(int[] seq){
        int maxSum = 0;

        for(int i=0; i < seq.length; i++){
            for(int j=i; j < seq.length; j++){
                int thisSum = 0;
                for(int k=i; k <= j; k++)
                    thisSum += seq[k];
                if(thisSum > maxSum)
                    maxSum = thisSum;
            }
        }

        return maxSum;
    }

    public static int maxSubSum2(int[] seq) {
        int maxSum = 0;

        for(int i=0; i < seq.length; i++){

            int thisSum = 0;
            for(int j=i; j < seq.length; j++){
                thisSum += seq[j];

                if(thisSum > maxSum)
                    maxSum = thisSum;
            }
        }

        return maxSum;
    }

    private static int max3(int x, int y, int z){
        int maxNum = x;
        if(y > maxNum) {
            maxNum = y;
        }
        if(z > maxNum) {
            maxNum = z;
        }

        return maxNum;
    }

    private static int maxSubSumRec(int[] seq, int left, int right) {
        if(left == right) {
            if (seq[left] > 0)
                return seq[left];
            else
                return 0;
        }

        int center = (left + right) / 2;
        int maxLeftSum = maxSubSumRec(seq, left, center);
        int maxRightSum = maxSubSumRec(seq, center+1, right);

        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for(int i = center; i >= left; i--){
            leftBorderSum += seq[i];
            if(leftBorderSum > maxLeftBorderSum)
                maxLeftBorderSum = leftBorderSum;
        }

        int maxRightBorderSum = 0, rightBorderSum = 0;
        for(int i = center+1; i <= right; i++){
            rightBorderSum += seq[i];
            if(rightBorderSum > maxRightBorderSum)
                maxRightBorderSum = rightBorderSum;
        }

        return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    public static int maxSubSum3(int[] seq) {
        return maxSubSumRec(seq, 0, seq.length - 1);
    }

    public static int maxSubSum4(int[] seq) {
        int maxSum = 0, thisSum = 0;

        for(int i=0; i < seq.length; i++){
            thisSum  += seq[i];

            if(thisSum > maxSum){
                maxSum = thisSum;
            } else  if(thisSum < 0){
                thisSum = 0;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] seq = {-2, 11, -4, 13, -5, -2};

        System.out.println(maxSubSum1(seq));
        System.out.println(maxSubSum2(seq));
        System.out.println(maxSubSum3(seq));
        System.out.println(maxSubSum4(seq));
    }
}
