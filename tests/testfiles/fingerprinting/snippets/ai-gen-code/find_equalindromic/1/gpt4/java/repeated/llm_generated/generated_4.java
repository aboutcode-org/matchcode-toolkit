import java.util.Arrays;
import java.util.Scanner;
public class MinCostCalculator {
    public long computeMinCost(int[] inputNums) {
        int totalNums = inputNums.length;
        Arrays.sort(inputNums);
        int median = inputNums[totalNums/2];
        int incNum = median;
        int decNum = median;
        while(!checkPalindrome(incNum)) incNum++;
        while(!checkPalindrome(decNum)) decNum--;
        return Math.min(determineCost(inputNums,incNum), determineCost(inputNums,decNum));
    }

    public boolean checkPalindrome(int num) {
        int remainder, sum = 0, original;
        original = num;
        while (num > 0) {
            remainder = num % 10;
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return original == sum;
    }

    public long determineCost(int[] inputNums, int replacementNum) {
        long computationCost = 0;
        for (int number : inputNums) computationCost += Math.abs(number - replacementNum);
        return computationCost;
    }
    
    public static void main(String[] elements) {
        Scanner sc = new Scanner(System.in);
        String arrayStr = sc.nextLine(); 
        arrayStr = arrayStr.substring(1, arrayStr.length() - 1);
        String[] strNumbers = arrayStr.split(",");
        int[] integerArray = new int[strNumbers.length];
        for (int index = 0; index < strNumbers.length; index++) {
            integerArray[index] = Integer.parseInt(strNumbers[index].trim());
        }
        MinCostCalculator minCostCalcObj = new MinCostCalculator();
        long totalCost = minCostCalcObj.computeMinCost(integerArray);
        System.out.println(totalCost);
        sc.close();
    }
}