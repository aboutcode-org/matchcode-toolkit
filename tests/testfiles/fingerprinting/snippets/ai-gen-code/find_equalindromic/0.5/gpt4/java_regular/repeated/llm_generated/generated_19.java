import java.util.Arrays;
import java.util.Scanner;

class MinCostCalculator {
    public long computeMinCost(int[] numArray) {
        int size = numArray.length;
        Arrays.sort(numArray);
        int center = numArray[size/2];
        int increment = center;
        int decrement = center;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(costCalculation(numArray,increment), costCalculation(numArray,decrement));
    }

    public boolean checkPalindrome(int number) {
        int remainder, total = 0, temporary;
        temporary = number;
        while (number > 0) {
            remainder = number % 10; 
            total = (total * 10) + remainder;
            number = number / 10;
        }
        return temporary == total;
    }

    public long costCalculation(int[] numArray, int ref) {
        long cost = 0;
        for (int num : numArray) cost += Math.abs(num - ref);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputLine = scan.nextLine(); 

        // Remove the square brackets from the input string
        inputLine = inputLine.substring(1, inputLine.length() - 1);

        // Split the input string into an array of strings
        String[] strNumbers = inputLine.split(",");

        // Convert the string array to an array of integers
        int[] intNumbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            intNumbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        // Create an instance of the MinCostCalculator class and call the computeMinCost method
        MinCostCalculator minCostCalculator = new MinCostCalculator();
        long minCost = minCostCalculator.computeMinCost(intNumbers);

        // Print the result
        System.out.println(minCost);
        scan.close();
    }
}