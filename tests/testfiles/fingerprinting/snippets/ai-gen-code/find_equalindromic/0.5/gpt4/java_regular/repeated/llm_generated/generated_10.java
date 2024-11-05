import java.util.Arrays;
import java.util.Scanner;

public class MinimumCostCalculator {
    public long computeMinimumCost(int[] inputNumbers) {
        int arrayLength = inputNumbers.length;
        Arrays.sort(inputNumbers);
        int median = inputNumbers[arrayLength/2];
        int increment = median;
        int decrement = median;

        while(!checkPalindrome(increment)) increment++;
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(getCost(inputNumbers,increment), getCost(inputNumbers,decrement));
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

    public long getCost(int[] inputNumbers, int referenceNumber) {
        long cost = 0;
        for (int num: inputNumbers) cost += Math.abs(num - referenceNumber);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        input = input.substring(1, input.length() - 1);

        String[] stringNumbers = input.split(",");

        int[] numbers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i].trim());
        }

        MinimumCostCalculator minimumCostCalculator = new MinimumCostCalculator();
        long cost = minimumCostCalculator.computeMinimumCost(numbers);

        System.out.println(cost);
        scanner.close();
    }
}