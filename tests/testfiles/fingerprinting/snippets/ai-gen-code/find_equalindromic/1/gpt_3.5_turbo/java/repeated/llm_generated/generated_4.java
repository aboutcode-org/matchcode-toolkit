import java.util.Arrays;
import java.util.Scanner;
class Solution {
    public long minCost(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        int m = arr[len/2];
        int inc = m;
        int dec = m;

        while(!isPalindrome(inc)) inc++; 
        while(!isPalindrome(dec)) dec--;

        return Math.min(calcCost(arr,inc), calcCost(arr,dec));
    }


    public boolean isPalindrome(int num) {
        int rem, sum = 0, temp;
        temp = num;
        while (num > 0) {
            rem = num % 10; 
            sum = (sum * 10) + rem;
            num = num / 10;
        }
        return temp == sum;
    }

    public long calcCost(int[] arr, int r) {
        long res = 0;
        for (int n: arr) res += Math.abs(n - r);
        return res;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        input = input.substring(1, input.length() - 1);

        // Split the input string into an array of strings
        String[] numStr = input.split(",");

        // Convert the string array to an array of integers
        int[] num = new int[numStr.length];
        for (int i = 0; i < numStr.length; i++) {
            num[i] = Integer.parseInt(numStr[i].trim());
        }

        // Create an instance of the Solution class and call the minCost method
        Solution sol = new Solution();
        long result = sol.minCost(num);

        // Print the result
        System.out.println(result);
        scan.close();
    }
}