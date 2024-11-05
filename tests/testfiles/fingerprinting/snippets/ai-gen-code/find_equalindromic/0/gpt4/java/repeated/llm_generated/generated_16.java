import java.util.Arrays;
import java.util.Scanner;

public class CostCalculator {
    public long findMinimumCost(int[] array) {
        int size = array.length;
        Arrays.sort(array);
        int mid = array[size/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(getCost(array,increment), getCost(array,decrement));
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

    public long getCost(int[] array, int r) {
        long cost = 0;
        for (int num: array) cost += Math.abs(num - r);
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