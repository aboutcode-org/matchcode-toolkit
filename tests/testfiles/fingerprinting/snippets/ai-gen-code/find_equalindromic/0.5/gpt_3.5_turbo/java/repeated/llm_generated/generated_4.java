import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long findLowestCost(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        int mid = arr[len/2];
        int incVal = mid;
        int decVal = mid;

        while(!isPalindrome(incVal)) incVal++; 
        while(!isPalindrome(decVal)) decVal--;

        return Math.min(calculateTotalCost(arr,incVal), calculateTotalCost(arr,decVal));
    }


    public boolean isPalindrome(int num) {
        int remainder, sum = 0, tempNum;
        tempNum = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return tempNum == sum;
    }

    public long calculateTotalCost(int[] arr, int val) {
        long totalCost = 0;
        for (int num: arr) totalCost += Math.abs(num - val);
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
        int[] numbers = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            numbers[i] = Integer.parseInt(numStrings[i].trim());
        }

        // Create an instance of the Solution class and call the findLowestCost method
        Solution solution = new Solution();
        long cost = solution.findLowestCost(numbers);

        // Print the result
        System.out.println(cost);
        scanner.close();
    }
}