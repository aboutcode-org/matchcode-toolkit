import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public long getMinimumCost(int[] numArray) {
        int size = numArray.length;
        Arrays.sort(numArray);
        int median = numArray[size/2];
        int increment = median;
        int decrement = median;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(findCost(numArray,increment), findCost(numArray,decrement));
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

    public long findCost(int[] numArray, int num) {
        long cost = 0;
        for (int n: numArray) cost += Math.abs(n - num);
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
        Main main = new Main();
        long minCost = main.getMinimumCost(numbers);
        System.out.println(minCost);
        scanner.close();
    }
}