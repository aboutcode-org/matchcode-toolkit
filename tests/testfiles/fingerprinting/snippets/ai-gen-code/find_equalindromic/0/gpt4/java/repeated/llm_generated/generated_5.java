import java.util.Arrays;
import java.util.Scanner;

public class CostCalculator {
    public long findMinimumCost(int[] inputNumbers) {
        int arrayLength = inputNumbers.length;
        Arrays.sort(inputNumbers);
        int mid = inputNumbers[arrayLength/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(computeCost(inputNumbers,increment), computeCost(inputNumbers,decrement));
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

    public long computeCost(int[] inputNumbers, int reference) {
        long totalCost = 0;
        for (int num: inputNumbers) totalCost += Math.abs(num - reference);
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine(); 

        inputLine = inputLine.substring(1, inputLine.length() - 1);

        String[] stringNumbers = inputLine.split(",");

        int[] numbers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i].trim());
        }

        CostCalculator costCalculator = new CostCalculator();
        long minimumCost = costCalculator.findMinimumCost(numbers);

        System.out.println(minimumCost);
        scanner.close();
    }
}