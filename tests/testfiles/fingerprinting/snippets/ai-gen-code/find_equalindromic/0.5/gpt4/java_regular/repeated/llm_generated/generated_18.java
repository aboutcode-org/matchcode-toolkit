import java.util.Arrays;
import java.util.Scanner;

class MinCostCalculator {
    public long findMinCost(int[] values) {
        int length = values.length;
        Arrays.sort(values);
        int mid = values[length/2];
        int increase = mid;
        int decrease = mid;

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

    public long computeCost(int[] values, int num) {
        long cost = 0;
        for (int value: values) cost += Math.abs(value - num);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine(); 

        userInput = userInput.substring(1, userInput.length() - 1);

        String[] numberStrings = userInput.split(",");

        int[] numbers = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i].trim());
        }

        MinCostCalculator minCostCalculator = new MinCostCalculator();
        long cost = minCostCalculator.findMinCost(numbers);

        System.out.println(cost);
        scanner.close();
    }
}