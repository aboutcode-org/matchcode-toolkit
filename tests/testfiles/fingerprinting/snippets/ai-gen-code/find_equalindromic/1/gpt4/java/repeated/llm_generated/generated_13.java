import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public long calculateMinimumCost(int[] numbers) {
        int size = numbers.length;
        Arrays.sort(numbers);
        
        int mid = numbers[size / 2];
        int increment = mid;
        int decrement = mid;

        while (!isPalindrome(increment)) 
            increment += 1;
        while (!isPalindrome(decrement)) 
            decrement -= 1;

        return Math.min(computeCost(numbers, increment), computeCost(numbers, decrement));
    }

    public boolean isPalindrome(int num) {
        int remainder, sum = 0;
        int original = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = sum * 10 + remainder;
            num = num / 10;
        }
        return original == sum;
    }

    public long computeCost(int[] numbers, int value) {
        long totalCost = 0;
        for (int number: numbers) 
            totalCost += Math.abs(number - value);
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Remove the square brackets from the input string
        String line = sc.nextLine().substring(1, sc.nextLine().length() - 1);

        // Split the input string into an array of strings
        String[] stringNumbers = line.split(",");

        // Convert the string array to an array of integers
        int[] nums = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            nums[i] = Integer.parseInt(stringNumbers[i].trim());
        }

        // Create an instance of the Main class and call the calculateMinimumCost method
        Main main = new Main();
        long result = main.calculateMinimumCost(nums);

        // Print the result
        System.out.println(result);
        sc.close();
    }
}