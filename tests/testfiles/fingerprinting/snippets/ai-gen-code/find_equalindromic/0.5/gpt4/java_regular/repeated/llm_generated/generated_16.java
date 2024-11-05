import java.util.Arrays;
import java.util.Scanner;

public class MinCostCalculator {
    public long computeMinCost(int[] values) {
        int size = values.length;
        Arrays.sort(values);
        int median = values[size/2];
        int increasing = median;
        int decreasing = median;

        while(!checkPalindrome(increasing)) increasing++; 
        while(!checkPalindrome(decreasing)) decreasing--;

        return Math.min(getCost(values,increasing), getCost(values,decreasing));
    }

    public boolean checkPalindrome(int number) {
        int remainder, total = 0, original;
        original = number;
        while (number > 0) {
            remainder = number % 10; 
            total = (total * 10) + remainder;
            number = number / 10;
        }
        return original == total;
    }

    public long getCost(int[] values, int palindrome) {
        long cost = 0;
        for (int value: values) cost += Math.abs(value - palindrome);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        userInput = userInput.substring(1, userInput.length() - 1);
        String[] strNumbers = userInput.split(",");

        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        MinCostCalculator minCostCalculator = new MinCostCalculator();
        long minCost = minCostCalculator.computeMinCost(numbers);

        System.out.println(minCost);
        scanner.close();
    }
}