import java.util.Arrays;
import java.util.Scanner;

public class CostCalculator {
    public long findMinCost(int[] values) {
        int size = values.length;
        Arrays.sort(values);
        int middle = values[size/2];
        int increase = middle;
        int decrease = middle;

        while(!checkPalindrome(increase)) increase++; 
        while(!checkPalindrome(decrease)) decrease--;

        return Math.min(computeCost(values,increase), computeCost(values,decrease));
    }


    public boolean checkPalindrome(int n) {
        int remainder, sum = 0, temp;
        temp = n;
        while (n > 0) {
            remainder = n % 10; 
            sum = (sum * 10) + remainder;
            n = n / 10;
        }
        return temp == sum;
    }

    public long computeCost(int[] values, int r) {
        long cost = 0;
        for (int value: values) cost += Math.abs(value - r);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        input = input.substring(1, input.length() - 1);
        String[] numberStrings = input.split(",");

        int[] numbers = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i].trim());
        }

        CostCalculator costCalculator = new CostCalculator();
        long cost = costCalculator.findMinCost(numbers);

        System.out.println(cost);
        scanner.close();
    }
}