import java.util.Arrays;
import java.util.Scanner;

public class MinCost {
    
    private static long findMinimumCost(int[] numbers) {
        int arrayLength = numbers.length;
        Arrays.sort(numbers);
        int central = numbers[arrayLength / 2];
        int increasing = central;
        int decreasing = central;

        while(!isPalindrome(increasing)) increasing++;
        while(!isPalindrome(decreasing)) decreasing--;

        return Math.min(getCost(numbers, increasing), getCost(numbers, decreasing));
    }

    private static boolean isPalindrome(int x) {
        int remainder, sumResult = 0, temp;
        temp = x;
        while (x > 0) {
            remainder = x % 10;
            sumResult = (sumResult * 10) + remainder;
            x = x / 10;
        }
        return temp == sumResult;
    }

    private static long getCost(int[] numbers, int num) {
        long totalCost = 0;
        for (int number: numbers) totalCost += Math.abs(number - num);
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String inputData = inputScanner.nextLine();

        inputData = inputData.substring(1, inputData.length() - 1);
        String[] stringNumbers = inputData.split(",");

        int[] parsedNumbers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            parsedNumbers[i] = Integer.parseInt(stringNumbers[i].trim());
        }

        long calculatedCost = findMinimumCost(parsedNumbers);

        System.out.println(calculatedCost);
        inputScanner.close();
    }
}