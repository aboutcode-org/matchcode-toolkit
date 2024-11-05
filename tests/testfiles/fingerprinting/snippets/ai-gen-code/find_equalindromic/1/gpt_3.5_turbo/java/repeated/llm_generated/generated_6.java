import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long minCost(int[] array) {
        int len = array.length;
        Arrays.sort(array);
        int mid = array[len/2];
        int incVal = mid;
        int decVal = mid;

        while(!isPalindrome(incVal)) incVal++; 
        while(!isPalindrome(decVal)) decVal--;

        return Math.min(calculateCost(array,incVal), calculateCost(array,decVal));
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

    public long calculateCost(int[] array, int val) {
        long totalCost = 0;
        for (int num: array) totalCost += Math.abs(num - val);
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); 

        input = input.substring(1, input.length() - 1);

        String[] numStrings = input.split(",");

        int[] nums = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            nums[i] = Integer.parseInt(numStrings[i].trim());
        }

        Solution sol = new Solution();
        long result = sol.minCost(nums);

        System.out.println(result);
        scanner.close();
    }
}