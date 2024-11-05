import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long minimumDifference(int[] numbers) {
        int size = numbers.length;
        Arrays.sort(numbers);
        int mid = numbers[size/2];
        int increase = mid;
        int decrease = mid;

        while(!isPalindrome(increase)) increase++; 
        while(!isPalindrome(decrease)) decrease--;

        return Math.min(calculateDifference(numbers,increase), calculateDifference(numbers,decrease));
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

    public long calculateDifference(int[] numbers, int r) {
        long diff = 0;
        for (int num: numbers) diff += Math.abs(num - r);
        return diff;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        input = input.substring(1, input.length() - 1);

        // Split the input string into an array of strings
        String[] numberStrings = input.split(",");

        // Convert the string array to an array of integers
        int[] nums = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            nums[i] = Integer.parseInt(numberStrings[i].trim());
        }

        // Create an instance of the Solution class and call the minimumDifference method
        Solution solution = new Solution();
        long diff = solution.minimumDifference(nums);

        // Print the result
        System.out.println(diff);
        scanner.close();
    }
}