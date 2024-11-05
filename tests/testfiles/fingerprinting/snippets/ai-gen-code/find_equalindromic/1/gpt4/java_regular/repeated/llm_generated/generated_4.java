Here is a semantically equivalent code clone to the one given:

```Java
import java.util.Arrays;
import java.util.Scanner;
class SolutionEqual {
    public long findMinCost(int[] arr) {
        int size = arr.length;
        Arrays.sort(arr);
        int median = arr[size/2];
        int inc = median;
        int dec = median;

        while(!isPalindrome(inc)) inc++; 
        while(!isPalindrome(dec)) dec--;

        return Math.min(getCost(arr,inc), getCost(arr,dec));
    }


    public boolean isPalindrome(int val) {
        int remainder, sum = 0, placeholder;
        placeholder = val;
        while (val > 0) {
            remainder = val % 10; 
            sum = (sum * 10) + remainder;
            val = val / 10;
        }
        return placeholder == sum;
    }

    public long getCost(int[] arr, int r) {
        long cost = 0;
        for (int n: arr) cost += Math.abs(n - r);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        userInput = userInput.substring(1, userInput.length() - 1);

        // Split the input string into an array of strings
        String[] strNumbers = userInput.split(",");

        // Convert the string array to an array of integers
        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        // Create an instance of the Solution class and call the minimumCost method
        SolutionEqual solution = new SolutionEqual();
        long cost = solution.findMinCost(numbers);

        // Print the result
        System.out.println(cost);
        scanner.close();
    }
}
```
This clone does exactly what the original code does. The difference lies in the naming of the class, methods, and variables in order to present a different but equivalent version of the same logic as the original code.