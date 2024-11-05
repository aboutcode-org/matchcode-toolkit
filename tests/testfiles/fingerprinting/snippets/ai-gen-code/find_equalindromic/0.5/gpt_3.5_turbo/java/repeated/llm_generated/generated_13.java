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

    public long calculateTotalCost(int[] arr, int val) {
        long totalCost = 0;
        for (int num: arr) totalCost += Math.abs(num - val);
        return totalCost;
    }
    
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String userInput = inputScanner.nextLine(); 

        userInput = userInput.substring(1, userInput.length() - 1);

        String[] numberStrings = userInput.split(",");

        int[] numbers = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i].trim());
        }

        Solution solution = new Solution();
        long finalCost = solution.findMinimumCost(numbers);

        System.out.println(finalCost);
        inputScanner.close();
    }
}