import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long findMinimumCost(int[] arr) {
        int size = arr.length;
        Arrays.sort(arr);
        int middleElement = arr[size/2];
        int incVal = middleElement;
        int decVal = middleElement;

        while(!isPalindrome(incVal)) incVal++; 
        while(!isPalindrome(decVal)) decVal--;

        return Math.min(calculateCost(arr, incVal), calculateCost(arr, decVal));
    }

    public boolean isPalindrome(int number) {
        int remainder, sum = 0, temp;
        temp = number;
        while (number > 0) {
            remainder = number % 10; 
            sum = (sum * 10) + remainder;
            number = number / 10;
        }
        return temp == sum;
    }

    public long calculateCost(int[] arr, int val) {
        long cost = 0;
        for (int num : arr) cost += Math.abs(num - val);
        return cost;
    }

    public static void main(String[] arguments) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); 

        input = input.substring(1, input.length() - 1);
        String[] numStringArr = input.split(",");

        int[] numbersArr = new int[numStringArr.length];
        for (int i = 0; i < numStringArr.length; i++) {
            numbersArr[i] = Integer.parseInt(numStringArr[i].trim());
        }

        Solution solutionObj = new Solution();
        long resultCost = solutionObj.findMinimumCost(numbersArr);

        System.out.println(resultCost);
        scanner.close();
    }
}