import java.util.Scanner;
import java.util.Arrays;

class Solution {
    public long findMinimumCost(int[] array) {
        int arrayLength = array.length;
        Arrays.sort(array);
        int mid = array[arrayLength/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++;
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(computeCost(array,increment), computeCost(array,decrement));
    }

    public boolean checkPalindrome(int number) {
        int remainder, sum = 0, temp;
        temp = number;
        while (number > 0) {
            remainder = number % 10;
            sum = (sum * 10) + remainder;
            number = number / 10;
        }
        return temp == sum;
    }

    public long computeCost(int[] array, int r) {
        long cost = 0;
        for (int num: array) cost += Math.abs(num - r);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        input = input.substring(1, input.length() - 1);

        String[] stringNumbers = input.split(",");

        int[] numbers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i].trim());
        }

        Solution sol = new Solution();
        long cost = sol.findMinimumCost(numbers);

        System.out.println(cost);
        scan.close();
    }
}