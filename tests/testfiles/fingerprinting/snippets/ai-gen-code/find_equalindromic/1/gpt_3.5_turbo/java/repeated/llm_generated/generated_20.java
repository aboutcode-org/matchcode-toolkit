import java.util.Arrays;
import java.util.Scanner;
class Solution {
    public long findMinimumCost(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int mid = nums[len/2];
        int incr = mid;
        int decr = mid;

        while(!checkPal(incr)) incr++; 
        while(!checkPal(decr)) decr--;

        return Math.min(getCost(nums,incr), getCost(nums,decr));
    }


    public boolean checkPal(int num) {
        int remainder, sum = 0, tempNum;
        tempNum = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return tempNum == sum;
    }

    public long getCost(int[] nums, int reference) {
        long totalCost = 0;
        for (int n: nums) totalCost += Math.abs(n - reference);
        return totalCost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        inputLine = inputLine.substring(1, inputLine.length() - 1);

        // Split the input string into an array of strings
        String[] numStrings = inputLine.split(",");

        // Convert the string array to an array of integers
        int[] numsArray = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            numsArray[i] = Integer.parseInt(numStrings[i].trim());
        }

        // Create an instance of the Solution class and call the findMinimumCost method
        Solution obj = new Solution();
        long result = obj.findMinimumCost(numsArray);

        // Print the result
        System.out.println(result);
        scanner.close();
    }
}