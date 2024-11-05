import java.util.Arrays;
import java.util.Scanner;
class Solution {
    public long findMinimumCost(int[] array) {
        int size = array.length;
        Arrays.sort(array);
        int middleIndex = array[size/2];
        int increment = middleIndex;
        int decrement = middleIndex;

        while(!isPalindrome(increment)) increment++; 
        while(!isPalindrome(decrement)) decrement--;

        return Math.min(calculateTotalCost(array,increment), calculateTotalCost(array,decrement));
    }


    public boolean isPalindrome(int num) {
        int remainder, sum = 0, temp;
        temp = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return temp == sum;
    }

    public long calculateTotalCost(int[] array, int val) {
        long totalCost = 0;
        for (int num: array) totalCost += Math.abs(num - val);
        return totalCost;
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

        // Create an instance of the Solution class and call the findMinimumCost method
        Solution solution = new Solution();
        long totalCost = solution.findMinimumCost(numbers);

        // Print the result
        System.out.println(totalCost);
        scanner.close();
    }
}