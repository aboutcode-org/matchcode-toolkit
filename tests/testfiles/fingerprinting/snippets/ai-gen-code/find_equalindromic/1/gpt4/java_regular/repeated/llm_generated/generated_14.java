import java.util.*;
import java.util.stream.*;

class Calculation {
    public long findMinimumCost(int[] numArr) {
        int size = numArr.length;
        Arrays.sort(numArr);
        int center = numArr[size/2];
        int increment = center;
        int decrement = center;

        while(!isPalindrome(increment)) increment++; 
        while(!isPalindrome(decrement)) decrement--;

        return Math.min(costCalculation(numArr, increment), costCalculation(numArr, decrement));
    }


    public boolean isPalindrome(int num) {
        int remainder, sum = 0, origNum;
        origNum = num;
        while(num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return origNum == sum;
    }

    public long costCalculation(int[] numArr, int refNum) {
        long costValue = 0;
        for(int num: numArr) costValue += Math.abs(num - refNum);
        return costValue;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputText = sc.nextLine();

        inputText = inputText.substring(1, inputText.length() - 1);
        String[] strNumArr = inputText.split(",");
        int[] intNumArr = new int[strNumArr.length];
        for(int i = 0; i < strNumArr.length; i++) {
            intNumArr[i] = Integer.parseInt(strNumArr[i].trim());
        }

        Calculation calc = new Calculation();
        long totalCost = calc.findMinimumCost(intNumArr);
        System.out.println(totalCost);
        sc.close();
    }
}