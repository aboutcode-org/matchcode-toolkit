import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String inputLine = inputScanner.nextLine();
        inputLine = inputLine.substring(1, inputLine.length() - 1);
        String[] strNumbers = inputLine.split(",");
        int[] intNumbers = new int[strNumbers.length];

        for(int i = 0 ; i<strNumbers.length ; i++){
            intNumbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        CostCalculator calculator = new CostCalculator();
        long result = calculator.findMinimumCost(intNumbers);
        System.out.println(result);
        inputScanner.close();
    }
}

class CostCalculator {
    public long findMinimumCost(int[] numbers) {
        int len = numbers.length;
        Arrays.sort(numbers);
        int mid = numbers[len/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++;
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(computeCost(numbers,increment), computeCost(numbers,decrement));
    }

    public boolean checkPalindrome(int number) {
        int remainder, sum = 0, copy;
        copy = number;

        while(number > 0){
            remainder = number%10;
            sum = (sum*10) + remainder;
            number = number/10;
        }
        return copy == sum;
    }

    public long computeCost(int[] numbers, int num) {
        long cost = 0;
        for(int n : numbers) cost += Math.abs(n - num);
        return cost;
    }
}