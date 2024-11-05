import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long findMinimumCost(int[] arr) {
        int length = arr.length;
        Arrays.sort(arr);
        int median = arr[length/2];
        int increment = median;
        int decrement = median;

        while(!isPalindrome(increment)) increment++; 
        while(!isPalindrome(decrement)) decrement--;

        return Math.min(calculateTotalCost(arr,increment), calculateTotalCost(arr,decrement));
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

    public long calculateTotalCost(int[] arr, int r) {
        long totalCost = 0;
        for (int num: arr) totalCost += Math.abs(num - r);
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        input = input.substring(1, input.length() - 1);

        // Split the input string into an array of strings
        String[] numStrings = input.split(",");

        // Convert the string array to an array of integers
        int[] nums = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            nums[i] = Integer.parseInt(numStrings[i].trim());
        }

        // Create an instance of the Solution class and call the findMinimumCost method
        Solution sol = new Solution();
        long totalCost = sol.findMinimumCost(nums);

        // Print the result
        System.out.println(totalCost);
        scanner.close();
    }
}