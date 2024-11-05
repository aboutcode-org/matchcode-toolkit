import java.util.Arrays;
import java.util.Scanner;

class CostCalculator {
    public long findMinimumCost(int[] values) {
        int size = values.length;
        Arrays.sort(values);
        int median = values[size/2];
        int increase = median;
        int decrease = median;

        while(!checkPalindrome(increase)) increase++; 
        while(!checkPalindrome(decrease)) decrease--;

        return Math.min(computeCost(values,increase), computeCost(values,decrease));
    }


    public boolean checkPalindrome(int num) {
        int remainder, sum = 0, temp;
        temp = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return temp == sum;
    }

    public long computeCost(int[] values, int res) {
        long cost = 0;
        for (int val: values) cost += Math.abs(val - res);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        input = input.substring(1, input.length() - 1);

        String[] strNumbers = input.split(",");

        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        CostCalculator costCalculator = new CostCalculator();
        long cost = costCalculator.findMinimumCost(numbers);

        System.out.println(cost);
        scanner.close();
    }
}