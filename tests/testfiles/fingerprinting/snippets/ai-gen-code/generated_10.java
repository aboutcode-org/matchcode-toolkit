import java.util.Arrays;
import java.util.Scanner;

class CostCalculator {
    public long findMinimumCost(int[] values) {
        int size = values.length;
        Arrays.sort(values);
        int median = values[size/2];
        int increase = median;
        int decrease = median;

        while(!isPalindrome(increase)) increase++; 
        while(!isPalindrome(decrease)) decrease--;

        return Math.min(computeCost(values,increase), computeCost(values,decrease));
    }


    public boolean isPalindrome(int num) {
        int remainder, total = 0, temp;
        temp = num;
        while (num > 0) {
            remainder = num % 10; 
            total = (total * 10) + remainder;
            num = num / 10;
        }
        return temp == total;
    }

    public long computeCost(int[] values, int num) {
        long cost = 0;
        for (int value: values) cost += Math.abs(value - num);
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