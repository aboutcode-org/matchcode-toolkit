import java.util.Arrays;
import java.util.Scanner;
class Solution {
    public long findMinimumCost(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        int mid = arr[len/2];
        int inc = mid;
        int dec = mid;

        while(!isPalindrome(inc)) inc++; 
        while(!isPalindrome(dec)) dec--;

        return Math.min(calculateTotalCost(arr,inc), calculateTotalCost(arr,dec));
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
        for (int n: arr) totalCost += Math.abs(n - r);
        return totalCost;
    }
    public static void main(String[] arguments) {
        Scanner scan = new Scanner(System.in);
        String inputStr = scan.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        inputStr = inputStr.substring(1, inputStr.length() - 1);

        // Split the input string into an array of strings
        String[] numStrArr = inputStr.split(",");

        // Convert the string array to an array of integers
        int[] numArr = new int[numStrArr.length];
        for (int i = 0; i < numStrArr.length; i++) {
            numArr[i] = Integer.parseInt(numStrArr[i].trim());
        }

        // Create an instance of the Solution class and call the findMinimumCost method
        Solution sol = new Solution();
        long result = sol.findMinimumCost(numArr);

        // Print the result
        System.out.println(result);
        scan.close();
    }
}