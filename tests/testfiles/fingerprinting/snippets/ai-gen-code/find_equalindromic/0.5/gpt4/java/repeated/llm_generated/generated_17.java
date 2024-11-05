import java.util.Arrays;
import java.util.Scanner;

class SolutionClone {
    public long findMinimumCost(int[] inputNumbers) {
        int length = inputNumbers.length;
        Arrays.sort(inputNumbers);
        int median = inputNumbers[length/2];
        int increment = median;
        int decrement = median;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(getCost(inputNumbers,increment), getCost(inputNumbers,decrement));
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

    public long getCost(int[] inputNumbers, int num) {
        long totalCost = 0;
        for (int n: inputNumbers) totalCost += Math.abs(n - num);
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine(); 

        userInput = userInput.substring(1, userInput.length() - 1);

        String[] stringNumbers = userInput.split(",");

        int[] numbers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i].trim());
        }

        SolutionClone solutionClone = new SolutionClone();
        long minCost = solutionClone.findMinimumCost(numbers);

        System.out.println(minCost);
        sc.close();
    }
}