import java.util.Arrays;
import java.util.Scanner;
class Solution {
    public long findMinimumCost(int[] arr) {
        int length = arr.length;
        Arrays.sort(arr);
        int median = arr[length/2];
        int inc = median;
        int dec = median;

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
        for (int num: arr) totalCost += Math.abs(num - r);
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); 

        input = input.substring(1, input.length() - 1);

        String[] numberStrings = input.split(",");

        int[] numbers = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i].trim());
        }

        Solution solution = new Solution();
        long cost = solution.findMinimumCost(numbers);

        System.out.println(cost);
        scanner.close();
    }
}