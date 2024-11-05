import java.util.Arrays;
import java.util.Scanner;
class Solution {
    public long findLowestCost(int[] input) {
        int size = input.length;
        Arrays.sort(input);
        int mid = input[size/2];
        int increment = mid;
        int decrement = mid;

        while(!isPalindrome(increment)) increment++; 
        while(!isPalindrome(decrement)) decrement--;

        return Math.min(calculateTotalCost(input,increment), calculateTotalCost(input,decrement));
    }


    public boolean isPalindrome(int num) {
        int reminder, sum = 0, temp;
        temp = num;
        while (num > 0) {
            reminder = num % 10; 
            sum = (sum * 10) + reminder;
            num = num / 10;
        }
        return temp == sum;
    }

    public long calculateTotalCost(int[] input, int selected) {
        long total = 0;
        for (int num: input) total += Math.abs(num - selected);
        return total;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        inputLine = inputLine.substring(1, inputLine.length() - 1);

        // Split the input string into an array of strings
        String[] numberSubstrings = inputLine.split(",");

        // Convert the string array to an array of integers
        int[] numberArray = new int[numberSubstrings.length];
        for (int i = 0; i < numberSubstrings.length; i++) {
            numberArray[i] = Integer.parseInt(numberSubstrings[i].trim());
        }

        // Create an instance of the Solution class and call the findLowestCost method
        Solution solutionObj = new Solution();
        long totalCost = solutionObj.findLowestCost(numberArray);

        // Print the result
        System.out.println(totalCost);
        scanner.close();
    }
}