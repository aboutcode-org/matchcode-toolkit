import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long findMinimumCost(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        int mid = arr[len / 2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(calculateTotalCost(arr, increment), calculateTotalCost(arr, decrement));
    }


    public boolean checkPalindrome(int num) {
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
        for (int num : arr) totalCost += Math.abs(num - r);
        return totalCost;
    }

    public static void main(String[] arguments) {
        Scanner inputScanner = new Scanner(System.in);
        String inputString = inputScanner.nextLine(); 

        inputString = inputString.substring(1, inputString.length() - 1);

        String[] numStrings = inputString.split(",");

        int[] numbers = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            numbers[i] = Integer.parseInt(numStrings[i].trim());
        }

        Solution solution = new Solution();
        long result = solution.findMinimumCost(numbers);

        System.out.println(result);
        inputScanner.close();
    }
}