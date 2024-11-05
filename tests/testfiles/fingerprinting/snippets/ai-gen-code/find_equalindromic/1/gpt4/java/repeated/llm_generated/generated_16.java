import java.util.Arrays;
import java.util.Scanner;
class Solution {
    public long findMinCost(int[] values) {
        int size = values.length;
        Arrays.sort(values);
        int midVal = values[size/2];
        int incremented = midVal;
        int decremented = midVal;

        while(!isPalindrome(incremented)) incremented++; 
        while(!isPalindrome(decremented)) decremented--;

        return Math.min(getCost(values,incremented), getCost(values,decremented));
    }

    public boolean isPalindrome(int num) {
        int remainder, totalSum = 0, temporary;
        temporary = num;
        while (num > 0) {
            remainder = num % 10; 
            totalSum = (totalSum * 10) + remainder;
            num = num / 10;
        }
        return temporary == totalSum;
    }

    public long getCost(int[] values, int num) {
        long cost = 0;
        for (int val: values) cost += Math.abs(val - num);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scannerIn = new Scanner(System.in);
        String entered = scannerIn.nextLine();

        entered = entered.substring(1, entered.length() - 1);
        String[] splitted = entered.split(",");

        int[] numbers = new int[splitted.length];
        for (int i = 0; i < splitted.length; i++) {
            numbers[i] = Integer.parseInt(splitted[i].trim());
        }

        Solution sol = new Solution();
        long result = sol.findMinCost(numbers);

        System.out.println(result);
        scannerIn.close();
    }
}