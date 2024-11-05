import java.util.Arrays;
import java.util.Scanner;

public class MinimumCostCalculator {
    public long findMinimumCost(int[] numbers) {
        int size = numbers.length;
        Arrays.sort(numbers);
        int median = numbers[size/2];
        int increment = median;
        int decrement = median;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(computeCost(numbers,increment), computeCost(numbers,decrement));
    }


    public boolean checkPalindrome(int num) {
        int remainder, total = 0, temporary;
        temporary = num;
        while (num > 0) {
            remainder = num % 10; 
            total = (total * 10) + remainder;
            num = num / 10;
        }
        return temporary == total;
    }

    public long computeCost(int[] numbers, int value) {
        long cost = 0;
        for (int num: numbers) cost += Math.abs(num - value);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputData = scanner.nextLine(); 

        inputData = inputData.substring(1, inputData.length() - 1);

        String[] strNumbers = inputData.split(",");

        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        MinimumCostCalculator calculator = new MinimumCostCalculator();
        long cost = calculator.findMinimumCost(numbers);

        System.out.println(cost);
        scanner.close();
    }
}