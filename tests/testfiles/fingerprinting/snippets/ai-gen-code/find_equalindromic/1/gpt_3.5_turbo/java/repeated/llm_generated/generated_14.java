import java.util.Arrays;
import java.util.Scanner;

class DifferentSolution {
    public long calculateMinimum(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        int midValue = arr[len/2];
        int inc = midValue;
        int dec = midValue;

        while(!checkPalindrome(inc)) inc++; 
        while(!checkPalindrome(dec)) dec--;

        return Math.min(getCost(arr, inc), getCost(arr, dec));
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

    public long getCost(int[] arr, int r) {
        long totalCost = 0;
        for (int n: arr) totalCost += Math.abs(n - r);
        return totalCost;
    }

    public static void main(String[] arguments) {
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine(); 

        input = input.substring(1, input.length() - 1);

        String[] numberStrings = input.split(",");

        int[] numbers = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i].trim());
        }

        DifferentSolution sol = new DifferentSolution();
        long finalResult = sol.calculateMinimum(numbers);

        System.out.println(finalResult);
        inputScanner.close();
    }
}