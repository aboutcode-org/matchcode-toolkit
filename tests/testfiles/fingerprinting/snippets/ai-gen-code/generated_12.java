import java.util.Arrays;
import java.util.Scanner;

class CostCalculator {
    public long findMinimumCost(int[] values) {
        int size = values.length;
        Arrays.sort(values);
        int median = values[size/2];
        int increment = median;
        int decrement = median;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(computeCost(values,increment), computeCost(values,decrement));
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

    public long computeCost(int[] values, int reference) {
        long cost = 0;
        for (int value: values) cost += Math.abs(value - reference);
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

        CostCalculator costCalculator = new CostCalculator();
        long cost = costCalculator.findMinimumCost(numbers);

        System.out.println(cost);
        scanner.close();
    }
}