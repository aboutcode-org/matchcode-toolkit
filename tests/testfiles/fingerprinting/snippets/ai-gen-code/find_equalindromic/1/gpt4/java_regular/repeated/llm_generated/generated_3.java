Here is a Java semantic code clone of your example:

```
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public long minimalExpense(int[] values) {
        int size = values.length;
        Arrays.sort(values);
        int median = values[size/2];
        int increment = median;
        int decrement = median;

        while(!isPalindrome(increment)) increment++; 
        while(!isPalindrome(decrement)) decrement--;

        return Math.min(calcExpense(values, increment), calcExpense(values, decrement));
    }

    public boolean isPalindrome(int num) {
        int remainder, total = 0, temporary;
        temporary = num;
        while (num > 0) {
            remainder = num % 10; 
            total = (total * 10) + remainder;
            num = num / 10;
        }
        return temporary == total;
    }

    public long calcExpense(int[] values, int mod) {
        long expense = 0;
        for (int value: values) expense += Math.abs(value - mod);
        return expense;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine(); 

        // Remove the square brackets from the input string
        inputLine = inputLine.substring(1, inputLine.length() - 1);

        // Split the input string into an array of strings
        String[] strNums = inputLine.split(",");

        // Convert the string array to an array of integers
        int[] numbers = new int[strNums.length];
        for (int i = 0; i < strNums.length; i++) {
            numbers[i] = Integer.parseInt(strNums[i].trim());
        }

        // Create an instance of the Main class and call the minimalExpense method
        Main main = new Main();
        long expense = main.minimalExpense(numbers);

        // Print the result
        System.out.println(expense);
        scanner.close();
    }
}
```
The core logic of the code is unchanged. It just renames the methods and their arguments to other meaningful ones but holds the same functionality. The `minimumCost` method is renamed to `minimalExpense`, `isPal` to `isPalindrome`, `calculateCost` to `calcExpense` and their arguments are renamed accordingly. Also, the `Solution` class is renamed to `Main`. Other minor changes include renaming variables inside the main method to match their new context but their logic remains the same.