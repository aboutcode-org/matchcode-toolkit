import java.util.Scanner;
import java.util.Arrays;

class Solution2 {
    public long findMinimumCost(int[] array) {
        Arrays.sort(array);
        int arrayLength = array.length;
        int mid = array[arrayLength/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(getCost(array,increment), getCost(array,decrement));
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

    public long getCost(int[] array, int r) {
        long totalCost = 0;
        for (int num: array) totalCost += Math.abs(num - r);
        return totalCost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine(); 

        userInput = userInput.substring(1, userInput.length() - 1);

        String[] stringNumbers = userInput.split(",");

        int[] integerNumbers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            integerNumbers[i] = Integer.parseInt(stringNumbers[i].trim());
        }

        Solution2 solutionObj = new Solution2();
        long minimumCost = solutionObj.findMinimumCost(integerNumbers);

        System.out.println(minimumCost);
        scan.close();
    }
}