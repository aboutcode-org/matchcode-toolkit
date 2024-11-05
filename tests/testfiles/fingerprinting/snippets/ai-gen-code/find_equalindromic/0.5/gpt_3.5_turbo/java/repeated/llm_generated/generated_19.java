import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long findMinimumCost(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        int mid = arr[len/2];
        int increment = mid;
        int decrement = mid;

        while(!isPalindrome(increment)) increment++; 
        while(!isPalindrome(decrement)) decrement--;

        return Math.min(calculateTotalCost(arr,increment), calculateTotalCost(arr,decrement));
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

    public long calculateTotalCost(int[] arr, int num) {
        long totalCost = 0;
        for (int n: arr) totalCost += Math.abs(n - num);
        return totalCost;
    }

    public static void main(String[] arguments) {
        Scanner scan = new Scanner(System.in);
        String inputLine = scan.nextLine(); 

        inputLine = inputLine.substring(1, inputLine.length() - 1);

        String[] numStrings = inputLine.split(",");

        int[] nums = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            nums[i] = Integer.parseInt(numStrings[i].trim());
        }

        Solution sol = new Solution();
        long result = sol.findMinimumCost(nums);

        System.out.println(result);
        scan.close();
    }
}