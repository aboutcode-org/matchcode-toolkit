import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long calculateMinimum(int[] numbers) {
        int size = numbers.length;
        Arrays.sort(numbers);
        int middle = numbers[size / 2];
        int increment = middle;
        int decrement = middle;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(calculateTotalCost(numbers, increment), calculateTotalCost(numbers, decrement));
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

    public long calculateTotalCost(int[] numbers, int r) {
        long totalCost = 0;
        for (int num: numbers) totalCost += Math.abs(num - r);
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); 

        input = input.substring(1, input.length() - 1);

        String[] numStrings = input.split(",");

        int[] nums = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            nums[i] = Integer.parseInt(numStrings[i].trim());
        }

        Solution solution = new Solution();
        long minCost = solution.calculateMinimum(nums);

        System.out.println(minCost);
        scanner.close();
    }
}