import java.util.Arrays;
import java.util.Scanner;

class CostCalculator {
    public long getMinimumCost(int[] values) {
        int size = values.length;
        Arrays.sort(values);
        int median = values[size/2];
        int increment = median;
        int decrement = median;

        while(!isPalindrome(increment)) increment++;
        while(!isPalindrome(decrement)) decrement--;

        return Math.min(computeCost(values,increment), computeCost(values,decrement));
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

    public long computeCost(int[] values, int result) {
        long cost = 0;
        for (int value: values) cost += Math.abs(value - result);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();

        userInput = userInput.substring(1, userInput.length() - 1);

        String[] strNumbers = userInput.split(",");

        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        CostCalculator calculator = new CostCalculator();
        long cost = calculator.getMinimumCost(numbers);

        System.out.println(cost);
        scan.close();
    }
}