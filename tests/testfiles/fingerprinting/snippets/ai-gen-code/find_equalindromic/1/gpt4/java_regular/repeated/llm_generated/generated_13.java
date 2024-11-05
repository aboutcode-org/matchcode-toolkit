import java.util.Arrays;
import java.util.Scanner;

class MinCostSolver {
    public long findMinCost(int[] inputNumbers) {
        int total = inputNumbers.length;
        Arrays.sort(inputNumbers);
        int mid = inputNumbers[total/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(costCalculation(inputNumbers,increment), costCalculation(inputNumbers,decrement));
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

    public long costCalculation(int[] inputNumbers, int reference) {
        long cost = 0;
        for (int num: inputNumbers) cost += Math.abs(num - reference);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputData = scanner.nextLine(); 

        inputData = inputData.substring(1, inputData.length() - 1);

        String[] inputStrings = inputData.split(",");

        int[] numbers = new int[inputStrings.length];
        for (int i = 0; i < inputStrings.length; i++) {
            numbers[i] = Integer.parseInt(inputStrings[i].trim());
        }

        MinCostSolver minCostSolver = new MinCostSolver();
        long minCost = minCostSolver.findMinCost(numbers);

        System.out.println(minCost);
        scanner.close();
    }
}