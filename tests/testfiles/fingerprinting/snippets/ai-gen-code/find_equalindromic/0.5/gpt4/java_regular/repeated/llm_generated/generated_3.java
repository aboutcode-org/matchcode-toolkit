import java.util.Arrays;
import java.util.Scanner;

class MinimumCostCalculator {
    public long computeMinimumCost(int[] numbers) {
        int size = numbers.length;
        Arrays.sort(numbers);
        int median = numbers[size/2];
        int increment = median;
        int decrement = median;

        while(!isPalindrome(increment)) increment++; 
        while(!isPalindrome(decrement)) decrement--;

        return Math.min(findCost(numbers,increment), findCost(numbers,decrement));
    }

    public boolean isPalindrome(int number) {
        int remainder, total = 0, temp;
        temp = number;
        while (number > 0) {
            remainder = number % 10; 
            total = (total * 10) + remainder;
            number = number / 10;
        }
        return temp == total;
    }

    public long findCost(int[] numbers, int result) {
        long cost = 0;
        for (int num: numbers) cost += Math.abs(num - result);
        return cost;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        input = input.substring(1, input.length() - 1);

        // Split the input string into an array of strings
        String[] numberStrings = input.split(",");

        // Convert the string array to an array of integers
        int[] numbers = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i].trim());
        }

        // Create an instance of the MinimumCostCalculator class and call the computeMinimumCost method
        MinimumCostCalculator calculator = new MinimumCostCalculator();
        long cost = calculator.computeMinimumCost(numbers);

        // Print the result
        System.out.println(cost);
        scanner.close();
    }
}