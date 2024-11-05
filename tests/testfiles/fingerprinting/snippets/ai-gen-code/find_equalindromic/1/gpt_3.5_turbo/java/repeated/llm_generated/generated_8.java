import java.util.Arrays;
import java.util.Scanner;
class Solution {
    public long minimumCost(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int middle = nums[n/2];
        int inc = middle;
        int dec = middle;

        while(!isPalindrome(inc)) inc++; 
        while(!isPalindrome(dec)) dec--;

        return Math.min(calculateCost(nums,inc), calculateCost(nums,dec));
    }


    public boolean isPalindrome(int n) {
        int reverse, sum = 0, temp;
        temp = n;
        while (n > 0) {
            reverse = n % 10; 
            sum = (sum * 10) + reverse;
            n = n / 10;
        }
        return temp == sum;
    }

    public long calculateCost(int[] nums, int r) {
        long cost = 0;
        for (int num: nums) 
            cost += Math.abs(num - r);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        input = input.substring(1, input.length() - 1);

        String[] numStrs = input.split(",");

        int[] numbers = new int[numStrs.length];
        for (int i = 0; i < numStrs.length; i++) {
            numbers[i] = Integer.parseInt(numStrs[i].trim());
        }

        Solution sol = new Solution();
        long minCost = sol.minimumCost(numbers);

        System.out.println(minCost);
        scanner.close();
    }
}