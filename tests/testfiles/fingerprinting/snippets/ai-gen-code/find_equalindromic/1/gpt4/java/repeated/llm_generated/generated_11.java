import java.util.Arrays;
import java.util.Scanner;

public class CostMinimizer {
    public long getMinimumCost(int[] numbers) {
        int length = numbers.length;
        Arrays.sort(numbers);
        int median = numbers[length/2];
        int increasedNum = median;
        int decreasedNum = median;

        while(!isPalindrome(increasedNum)) increasedNum++; 
        while(!isPalindrome(decreasedNum)) decreasedNum--;

        return Math.min(getCost(numbers,increasedNum), getCost(numbers,decreasedNum));
    }

    public boolean isPalindrome(int num) {
        int remainder, sum = 0, temp;
        temp = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return temp == sum;
    }

    public long getCost(int[] numbers, int r) {
        long cost = 0;
        for (int n: numbers) cost += Math.abs(n - r);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String arrayAsString = scanner.nextLine();
        arrayAsString = arrayAsString.substring(1, arrayAsString.length() - 1);

        String[] numbersAsStrings = arrayAsString.split(",");
        int[] parsedNumbers = new int[numbersAsStrings.length];
        for(int i = 0; i < numberStrings.length; i++) {
            parsedNumbers[i] = Integer.parseInt(numberStrings[i].trim());
        }

        CostMinimizer costMinimizer = new CostMinimizer();
        long cost = costMinimizer.getMinimumCost(parsedNumbers);

        System.out.println(cost);
        scanner.close();
    }
}