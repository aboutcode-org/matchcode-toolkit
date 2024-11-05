Here is a Java semantic code clone:

```java
import java.util.Arrays;
import java.util.Scanner;

class FindMinimumCost {
    public long computeMinimumCost(int[] numbers) {
        int size = numbers.length;
        Arrays.sort(numbers);
        int mid = numbers[size/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(getCost(numbers,increment), getCost(numbers,decrement));
    }

    public boolean checkPalindrome(int num) {
        int remainder, total = 0, original;
        original = num;
        while (num > 0) {
            remainder = num % 10; 
            total = (total * 10) + remainder;
            num = num / 10;
        }
        return original == total;
    }

    public long getCost(int[] numbers, int ref) {
        long cost = 0;
        for (int number: numbers) cost += Math.abs(number - ref);
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

        FindMinimumCost findMinimumCost = new FindMinimumCost();
        long cost = findMinimumCost.computeMinimumCost(numbers);

        System.out.println(cost);
        scanner.close();
    }
}
```
The code performs the same operations even though identifiers such as class name, variable names and method names have been changed.