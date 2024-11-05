import java.util.Arrays;
import java.util.Scanner;

public class MinCostCalculator {
    public long findMinimumCost(int[] inputNums) {
        int size = inputNums.length;
        Arrays.sort(inputNums);
        int median = inputNums[size/2];
        int increase = median;
        int decrease = median;

        while(!checkPalindrome(increase)) increase++; 
        while(!checkPalindrome(decrease)) decrease--;

        return Math.min(getCost(inputNums,increase), getCost(inputNums,decrease));
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

    public long getCost(int[] inputNums, int result) {
        long cost = 0;
        for (int num: inputNums) cost += Math.abs(num - result);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine(); 

        inputLine = inputLine.substring(1, inputLine.length() - 1);

        String[] stringNumbers = inputLine.split(",");

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