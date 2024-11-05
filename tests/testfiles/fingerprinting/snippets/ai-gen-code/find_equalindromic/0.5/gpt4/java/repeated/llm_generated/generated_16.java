import java.util.Arrays;
import java.util.Scanner;

public class CostMinimizer {
    public static void main(String[] args) {
        Scanner scannerInstance = new Scanner(System.in);
        String inputString = scannerInstance.nextLine();

        inputString = inputString.substring(1, inputString.length() - 1);
        String[] stringNumbers = inputString.split(",");

        int[] integerNumbers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            integerNumbers[i] = Integer.parseInt(stringNumbers[i].trim());
        }

        CostMinimizer costMinimizer = new CostMinimizer();
        long totalCost = costMinimizer.findMinimumCost(integerNumbers);

        System.out.println(totalCost);
        scannerInstance.close();
    }

    public long findMinimumCost(int[] numbers) {
        int n = numbers.length;
        Arrays.sort(numbers);
        int mid = numbers[n/2];
        int inc = mid;
        int dec = mid;

        while(!checkPalindrome(inc)) inc++;
        while(!checkPalindrome(dec)) dec--;

        return Math.min(computeCost(numbers,inc), computeCost(numbers,dec));
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

    public long computeCost(int[] numbers, int r) {
        long cost = 0;
        for (int num: numbers) cost += Math.abs(num - r);
        return cost;
    }
}