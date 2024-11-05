import java.util.Arrays;
import java.util.Scanner;

class PalindromeCost {
    public long getMinimumCost(int[] values) {
        int size = values.length;
        Arrays.sort(values);
        int mid = values[size/2];
        int inc = mid;
        int dec = mid;

        while(!isPalindrome(inc)) inc++; 
        while(!isPalindrome(dec)) dec--;

        return Math.min(calculateTotalCost(values, inc), calculateTotalCost(values, dec));
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

    public long calculateTotalCost(int[] values, int r) {
        long totalCost = 0;
        for (int value: values) totalCost += Math.abs(value - r);
        return totalCost;
    }

    public static void main(String[] commandArgs) {
        Scanner inputScanner = new Scanner(System.in);
        String inputString = inputScanner.nextLine(); 

        // Remove the square brackets from the input string
        inputString = inputString.substring(1, inputString.length() - 1);

        // Split the input string data into an array of string values
        String[] valueStrings = inputString.split(",");

        // Convert the string array to an array of integers
        int[] integerValues = new int[valueStrings.length];
        for (int i = 0; i < valueStrings.length; i++) {
            integerValues[i] = Integer.parseInt(valueStrings[i].trim());
        }

        // Create an instance of the PalindromeCost class and call the getMinimumCost method
        PalindromeCost palindromeCost = new PalindromeCost();
        long costValue = palindromeCost.getMinimumCost(integerValues);

        // Print the final output
        System.out.println(costValue);
        inputScanner.close();
    }
}