import java.util.Arrays;
import java.util.Scanner;

class Solution2 {
    public long findMinimumCost(int[] integers) {
        int length = integers.length;
        Arrays.sort(integers);
        int mid = integers[length/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(calculateTotalCost(integers,increment), calculateTotalCost(integers,decrement));
    }


    public boolean checkPalindrome(int number) {
        int remainder, sum = 0, temporary;
        temporary = number;
        while (number > 0) {
            remainder = number % 10; 
            sum = (sum * 10) + remainder;
            number = number / 10;
        }
        return temporary == sum;
    }

    public long calculateTotalCost(int[] integers, int r) {
        long totalCost = 0;
        for (int num: integers) totalCost += Math.abs(num - r);
        return totalCost;
    }
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String inputLine = inputScanner.nextLine(); 

        inputLine = inputLine.substring(1, inputLine.length() - 1);

        String[] splitNumbers = inputLine.split(",");

        int[] numbersArray = new int[splitNumbers.length];
        for (int i = 0; i < splitNumbers.length; i++) {
            numbersArray[i] = Integer.parseInt(splitNumbers[i].trim());
        }

        Solution2 solution2 = new Solution2();
        long totalCost = solution2.findMinimumCost(numbersArray);

        System.out.println(totalCost);
        inputScanner.close();
    }
}