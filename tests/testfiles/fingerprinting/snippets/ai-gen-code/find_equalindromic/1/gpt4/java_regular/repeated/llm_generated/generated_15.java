Here is a semantically equivalent clone of your provided Java code:

```java
import java.util.Arrays;
import java.util.Scanner;

class AlternativeSolution {
    public long findMinimumCost(int[] array) {
        int size = array.length;
        Arrays.sort(array);
        int center = array[size / 2];
        int increase = center;
        int decrease = center;

        while(!checkPalindrome(increase)) increase++;
        while(!checkPalindrome(decrease)) decrease--;

        return Math.min(computeCost(array, increase), computeCost(array, decrease));
    }

    public boolean checkPalindrome(int num) {
        int remainder, accumulator = 0, placeholder;
        placeholder = num;
        while (num > 0) {
            remainder = num % 10; 
            accumulator = (accumulator * 10) + remainder;
            num = num / 10;
        }
        return placeholder == accumulator;
    }

    public long computeCost(int[] array, int ref) {
        long expense = 0;
        for (int item: array) expense += Math.abs(item - ref);
        return expense;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        userInput = userInput.substring(1, userInput.length() - 1);
        String[] strNumbers = userInput.split(",");
        int[] numbers = new int[strNumbers.length];

        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        AlternativeSolution altSolution = new AlternativeSolution();
        long expense = altSolution.findMinimumCost(numbers);
        System.out.println(expense);
        scanner.close();
    }
}
```
This code does exactly the same thing as your code. It sorts an array and then it finds the nearest palindrome numbers (one greater and one less than the middle number of the sorted array). After that, it calculates the cost to convert all numbers in array to this palindrome number by using the absolute difference. Finally, it returns the minimum cost between these two costs. The `main` method reads an array of integers from input, passes it to `findMinimumCost` method and prints the result.