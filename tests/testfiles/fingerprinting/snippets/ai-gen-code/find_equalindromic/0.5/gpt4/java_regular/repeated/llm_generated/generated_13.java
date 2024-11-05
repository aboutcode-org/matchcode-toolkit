import java.util.Arrays;
import java.util.Scanner;

public class CostCalculator {
    public long getMinCost(int[] numbers) {
        int size = numbers.length;
        Arrays.sort(numbers);
        int median = numbers[size/2];
        int increment = median;
        int decrement = median;

        while(!isPalindrome(increment)) increment++; 
        while(!isPalindrome(decrement)) decrement--;

        return Math.min(computeCost(numbers,increment), computeCost(numbers,decrement));
    }

    public boolean isPalindrome(int number) {
        int remainder, sum = 0, temp;
        temp = number;
        while (number > 0) {
            remainder = number % 10; 
            sum = (sum * 10) + remainder;
            number = number / 10;
        }
        return temp == sum;
    }

    public long computeCost(int[] numbers, int value) {
        long cost = 0;
        for (int num: numbers) cost += Math.abs(num - value);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputLine = scan.nextLine(); 

        inputLine = inputLine.substring(1, inputLine.length() - 1);

        String[] strNumbers = inputLine.split(",");

        int[] nums = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            nums[i] = Integer.parseInt(strNumbers[i].trim());
        }

        CostCalculator costCalculator = new CostCalculator();
        long minimumCost = costCalculator.getMinCost(nums);

        System.out.println(minimumCost);
        scan.close();
    }
}