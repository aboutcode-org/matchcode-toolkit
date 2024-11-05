import java.util.Arrays;
import java.util.Scanner;
class Solution {
    public long findMinimumCost(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        int middle = nums[length/2];
        int increase = middle;
        int decrease = middle;

        while(!isPalindrome(increase)) increase++; 
        while(!isPalindrome(decrease)) decrease--;

        return Math.min(calculateTotalCost(nums,increase), calculateTotalCost(nums,decrease));
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

    public long calculateTotalCost(int[] nums, int r) {
        long totalCost = 0;
        for (int n: nums) totalCost += Math.abs(n - r);
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