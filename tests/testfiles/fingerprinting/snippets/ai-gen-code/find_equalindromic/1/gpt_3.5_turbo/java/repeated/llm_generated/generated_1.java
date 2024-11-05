import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long findMinimumCost(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        int mid = arr[n/2];
        int inc = mid;
        int dec = mid;

        while(!isPalindrome(inc)) inc++; 
        while(!isPalindrome(dec)) dec--;

        return Math.min(calculateCostHelper(arr,inc), calculateCostHelper(arr,dec));
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

    public long calculateCostHelper(int[] arr, int num) {
        long totalCost = 0;
        for (int el: arr) totalCost += Math.abs(el - num);
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String inputStr = inputScanner.nextLine(); // Retrieve the input

        // Remove brackets from the input string
        inputStr = inputStr.substring(1, inputStr.length() - 1);

        // Split the input string into an array of string elements
        String[] numStrings = inputStr.split(",");

        // Convert the string array to an array of integers
        int[] elements = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            elements[i] = Integer.parseInt(numStrings[i].trim());
        }

        // Instantiate the Solution class and invoke the findMinimumCost method
        Solution solver = new Solution();
        long costResult = solver.findMinimumCost(elements);

        // Output the calculated cost
        System.out.println(costResult);
        inputScanner.close();
    }
}