import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long findMinimumCost(int[] arr) {
        int size = arr.length;
        Arrays.sort(arr);
        int median = arr[size/2];
        int increment = median;
        int decrement = median;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(computeCost(arr,increment), computeCost(arr,decrement));
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

    public long computeCost(int[] arr, int num) {
        long cost = 0;
        for (int element: arr) cost += Math.abs(element - num);
        return cost;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); 

        input = input.substring(1, input.length() - 1);

        String[] strNums = input.split(",");

        int[] nums = new int[strNums.length];
        for (int i = 0; i < strNums.length; i++) {
            nums[i] = Integer.parseInt(strNums[i].trim());
        }

        Solution solution = new Solution();
        long cost = solution.findMinimumCost(nums);

        System.out.println(cost);
        scanner.close();
    }
}