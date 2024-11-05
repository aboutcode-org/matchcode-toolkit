import java.util.Scanner;
import java.util.Arrays;

public class MinimumCostCalculator {
    public long computeMinimumCost(int[] numbers) {
        int length = numbers.length;
        Arrays.sort(numbers);
        int median = numbers[length/2];
        int increment = median;
        int decrement = median;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(findCost(numbers,increment), findCost(numbers,decrement));
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

    public long findCost(int[] numbers, int num) {
        long totalCost = 0;
        for (int n: numbers) totalCost += Math.abs(n - num);
        return totalCost;
    }
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String inputLine = inputScanner.nextLine(); 

        // Remove the square brackets from the input string
        inputLine = inputLine.substring(1, inputLine.length() - 1);

        // Split the input string into an array of strings
        String[] stringNumbers = inputLine.split(",");

        // Convert the string array to an array of integers
        int[] integers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            integers[i] = Integer.parseInt(stringNumbers[i].trim());
        }

        // Create an instance of the Solution class and call the minimumCost method
        MinimumCostCalculator minimumCostCalculator = new MinimumCostCalculator();
        long cost = minimumCostCalculator.computeMinimumCost(integers);

        // Print the result
        System.out.println(cost);
        inputScanner.close();
    }
}