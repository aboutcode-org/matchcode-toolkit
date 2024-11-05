import java.util.Arrays;
import java.util.Scanner;

public class MinCostCalculator {
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
        int remainder, total = 0, temp;
        temp = num;
        while (num > 0) {
            remainder = num % 10; 
            total = (total * 10) + remainder;
            num = num / 10;
        }
        return temp == total;
    }

    public long computeCost(int[] numbers, int value) {
        long cost = 0;
        for (int num: numbers) cost += Math.abs(num - value);
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

        MinCostCalculator minCostCalculator = new MinCostCalculator();
        long cost = minCostCalculator.findMinimumCost(numbers);

        System.out.println(cost);
        scanner.close();
    }
}