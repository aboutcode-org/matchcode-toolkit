import java.util.Scanner;
import java.util.Arrays;

public class CostCalculation {
    public long findMinimumCost(int[] inputNumbers) {
        int size = inputNumbers.length;
        Arrays.sort(inputNumbers);
        int mid = inputNumbers[size/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(costCalculation(inputNumbers,increment), costCalculation(inputNumbers,decrement));
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

    public long costCalculation(int[] inputNumbers, int palindrome) {
        long cost = 0;
        for (int num: inputNumbers) cost += Math.abs(num - palindrome);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        inputLine = inputLine.substring(1, inputLine.length() - 1);

        // Split the input string into an array of strings
        String[] strNumbers = inputLine.split(",");

        // Convert the string array to an array of integers
        int[] intNumbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            intNumbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        // Create an instance of the Solution class and call the minimumCost method
        CostCalculation costCalc = new CostCalculation();
        long minCost = costCalc.findMinimumCost(intNumbers);

        // Print the result
        System.out.println(minCost);
        scanner.close();
    }
}