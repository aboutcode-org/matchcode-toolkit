import java.util.Arrays;
import java.util.Scanner;
class Functionality {
    public long calculateSum(int[] values) {
        int size = values.length;
        Arrays.sort(values);
        int mid = values[size/2];
        int inc = mid;
        int dec = mid;

        while(!isPalindrome(inc)) inc++; 
        while(!isPalindrome(dec)) dec--;

        return Math.min(calculateTotal(values,inc), calculateTotal(values,dec));
    }


    public boolean isPalindrome(int value) {
        int remainder, sum = 0, tempVal;
        tempVal = value;
        while (value > 0) {
            remainder = value % 10; 
            sum = (sum * 10) + remainder;
            value = value / 10;
        }
        return tempVal == sum;
    }

    public long calculateTotal(int[] values, int result) {
        long total = 0;
        for (int var: values) total += Math.abs(var - result);
        return total;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputStr = scan.nextLine(); // Read the input line

        // Remove the brackets from the input string
        inputStr = inputStr.substring(1, inputStr.length() - 1);

        // Split the input string into an array of strings
        String[] numStrs = inputStr.split(",");

        // Convert the string array to an array of integers
        int[] nums = new int[numStrs.length];
        for (int i = 0; i < numStrs.length; i++) {
            nums[i] = Integer.parseInt(numStrs[i].trim());
        }

        // Create an instance of the Functionality class and call the calculateSum method
        Functionality func = new Functionality();
        long totalCost = func.calculateSum(nums);

        // Print the result
        System.out.println(totalCost);
        scan.close();
    }
}