import java.util.Arrays;
import java.util.Scanner;

public class MinCost {
    public long findMinimumCost(int[] numArray) {
        int arrayLength = numArray.length;
        Arrays.sort(numArray);
        int mid = numArray[arrayLength/2];
        int increment = mid;
        int decrement = mid;

        while(!isPalindrome(increment)) increment++;
        while(!isPalindrome(decrement)) decrement--;

        return Math.min(getCost(numArray,increment), getCost(numArray,decrement));
    }

    public boolean isPalindrome(int number) {
        int remainder, sum = 0, original;
        original = number;
        while (number > 0) {
            remainder = number % 10;
            sum = (sum * 10) + remainder;
            number = number / 10;
        }
        return original == sum;
    }

    public long getCost(int[] numArray, int num) {
        long costValue = 0;
        for (int n: numArray) costValue += Math.abs(n - num);
        return costValue;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputLine = scan.nextLine();

        inputLine = inputLine.substring(1, inputLine.length() - 1);
        String[] strNumbers = inputLine.split(",");
        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        MinCost minCost = new MinCost();
        long cost = minCost.findMinimumCost(numbers);
        System.out.println(cost);
        scan.close();
    }
}