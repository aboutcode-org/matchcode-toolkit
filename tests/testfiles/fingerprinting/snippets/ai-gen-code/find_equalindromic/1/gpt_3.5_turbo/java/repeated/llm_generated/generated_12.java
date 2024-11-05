import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long findMinCost(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        int mid = arr[n/2];
        int inc = mid;
        int dec = mid;

        while(!isPalindrome(inc)) inc++; 
        while(!isPalindrome(dec)) dec--;

        return Math.min(calculateCost(arr,inc), calculateCost(arr,dec));
    }

    public boolean isPalindrome(int num) {
        int rev, sum = 0, temp;
        temp = num;
        while (num > 0) {
            rev = num % 10;
            sum = (sum * 10) + rev;
            num = num / 10;
        }
        return temp == sum;
    }

    public long calculateCost(int[] arr, int num) {
        long cost = 0;
        for (int n: arr) cost += Math.abs(n - num);
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
        long cost = solution.findMinCost(numbers);

        // Print the result
        System.out.println(cost);
        scanner.close();
    }
}