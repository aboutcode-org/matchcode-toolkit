import java.util.Scanner;
import java.util.Arrays;

public class CostCalculator {
    public long getMinimumCost(int[] numbers) {
        int size = numbers.length;
        Arrays.sort(numbers);
        int mid = numbers[size/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(findCost(numbers,increment), findCost(numbers,decrement));
    }

    public boolean checkPalindrome(int num) {
        int remainder, sum = 0, temporary;
        temporary = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return temporary == sum;
    }

    public long findCost(int[] numbers, int reference) {
        long totalCost = 0;
        for (int num: numbers) totalCost += Math.abs(num - reference);
        return totalCost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine(); 

        // Remove the square brackets from the input string
        inputLine = inputLine.substring(1, inputLine.length() - 1);

        // Split the input string into an array of strings
        String[] strNumbers = inputLine.split(",");

        // Convert the string array to an array of integers
        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        // Create an instance of the CostCalculator class and call the getMinimumCost method
        CostCalculator calculator = new CostCalculator();
        long minCost = calculator.getMinimumCost(numbers);

        // Print the result
        System.out.println(minCost);
        scanner.close();
    }
}