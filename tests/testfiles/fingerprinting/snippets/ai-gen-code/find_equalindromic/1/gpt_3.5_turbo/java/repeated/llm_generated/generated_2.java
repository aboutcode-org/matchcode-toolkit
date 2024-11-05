import java.util.Arrays;
import java.util.Scanner;
class Solution {
    public long findMinCost(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int middle = nums[n/2];
        int inc = middle;
        int dec = middle;

        while(!isPalindrome(inc)) inc++; 
        while(!isPalindrome(dec)) dec--;

        return Math.min(calculateCost(nums,inc), calculateCost(nums,dec));
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

    public long calculateExpenses(int[] nums, int r) {
        long cost = 0;
        for (int number: nums) cost += Math.abs(number - r);
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

        // Create an instance of the Solution class and call the findMinCost method
        Solution solution = new Solution();
        long finalCost = solution.findMinCost(numbers);

        // Print the result
        System.out.println(finalCost);
        scanner.close();
    }
}