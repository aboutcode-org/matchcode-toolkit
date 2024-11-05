import java.util.Arrays;
import java.util.Scanner;

public class MinimumCostCalculation {

    public long computeMinimumCost(int[] array) {
        int size = array.length;
        Arrays.sort(array);
        int mid = array[size/2];
        int increment = mid;
        int decrement = mid;

        while(!isPalindrome(increment)) increment++; 
        while(!isPalindrome(decrement)) decrement--;

        return Math.min(calculationCost(array,increment), calculationCost(array,decrement));
    }

    public boolean isPalindrome(int number) {
        int remainder, total = 0, copy;
        copy = number;
        while (number > 0) {
            remainder = number % 10; 
            total = (total * 10) + remainder;
            number = number / 10;
        }
        return copy == total;
    }

    public long calculationCost(int[] array, int value) {
        long cost = 0;
        for (int num: array) cost += Math.abs(num - value);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scannerInput = new Scanner(System.in);
        String inputData = scannerInput.nextLine(); // Get the input data

        // Delete the square brackets from the input string
        inputData = inputData.substring(1, inputData.length() - 1);

        // Split the input string into an array of strings
        String[] numStrings = inputData.split(",");

        // Convert the string array to an array of integers
        int[] integerNumbers = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            integerNumbers[i] = Integer.parseInt(numStrings[i].trim());
        }

        // Create an instance of the MinimumCostCalculation class and call the computeMinimumCost method
        MinimumCostCalculation minimumCostCalculation = new MinimumCostCalculation();
        long totalCost = minimumCostCalculation.computeMinimumCost(integerNumbers);

        // Print the result
        System.out.println(totalCost);
        scannerInput.close();
    }
}