import java.util.Scanner;
import java.util.Arrays;

public class CostCalculator {
    public long computeMinCost(int[] array) {
        Arrays.sort(array);
        int arrayLength = array.length;
        int midValue = array[arrayLength/2];
        int less = midValue;
        int more = midValue;

        while(!checkPalindrome(less)) less--;
        while(!checkPalindrome(more)) more++

        return Math.min(findCost(array,less), findCost(array,more));
    }

    public boolean checkPalindrome(int number) {
        int remainder, sum = 0, temp;
        temp = number;
        while (number > 0) {
            remainder = number % 10;
            sum = (sum * 10) + remainder;
            number = number / 10;
        }
        return temp == sum;
    }

    public long findCost(int[] array, int num) {
        long cost = 0;
        for (int element: array) cost += Math.abs(element - num);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scannerObject = new Scanner(System.in);
        String inputData = scannerObject.nextLine();

        inputData = inputData.substring(1, inputData.length() - 1);
        String[] strNumbers = inputData.split(",");

        int[] integerArray = new int[strNumbers.length];
        for (int index = 0; index < strNumbers.length; index++) {
            integerArray[index] = Integer.parseInt(strNumbers[index].trim());
        }

        CostCalculator costCalculatorObject = new CostCalculator();
        long minCost = costCalculatorObject.computeMinCost(integerArray);
        
        System.out.println(minCost);
        scannerObject.close();
    }
}