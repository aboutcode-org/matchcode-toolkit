import java.util.Scanner;
import java.util.Arrays;

class Solution {
    public long findMinimumCost(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int mid = nums[len/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(getCost(nums,increment), getCost(nums,decrement));
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

    public long getCost(int[] nums, int r) {
        long cost = 0;
        for (int num: nums) cost += Math.abs(num - r);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        input = input.substring(1, input.length() - 1);

        // Split the input string into an array of strings
        String[] strNumbers = input.split(",");

        // Convert the string array to an array of integers
        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        // Create an instance of the Solution class and call the findMinimumCost method
        Solution sol = new Solution();
        long cost = sol.findMinimumCost(numbers);

        // Print the result
        System.out.println(cost);
        scan.close();
    }
}