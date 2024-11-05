Here's a semantically equivalent clone in Java of the provided code:
```java
import java.util.Arrays;
import java.util.Scanner;

public class MinimumCostSolution {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        String inputLine = inputScanner.nextLine();

        inputLine = inputLine.substring(1, inputLine.length() - 1);

        String[] splitNumbers = inputLine.split(",");

        int[] numArray = new int[splitNumbers.length];

        for (int index = 0; index < splitNumbers.length; index++) {
            numArray[index] = Integer.parseInt(splitNumbers[index].trim());
        }

        MinimumCostSolution mcs = new MinimumCostSolution();

        long minimumCost = mcs.computeMinCost(numArray);

        System.out.println(minimumCost);

        inputScanner.close();
    }

    public long computeMinCost(int[] numArray) {
        int arraySize = numArray.length;

        Arrays.sort(numArray);

        int midElement = numArray[arraySize / 2];

        int plusCounter = midElement;
        int minusCounter = midElement;

        while(!isPalindrome(plusCounter)) plusCounter++; 
        while(!isPalindrome(minusCounter)) minusCounter--;

        return Math.min(evaluateCost(numArray, plusCounter), evaluateCost(numArray, minusCounter));
    }

    public boolean isPalindrome(int number) {
        int remainder, totalSum = 0;
        int temporary = number;
        
        while (number > 0) {
            remainder = number % 10;  
            totalSum = (totalSum * 10) + remainder;
            number = number / 10;
        }

        return temporary == totalSum;
    }

    public long evaluateCost(int[] numArray, int counter) {
        long finalCost = 0;
        for (int eachNum : numArray) finalCost += Math.abs(eachNum - counter);
        return finalCost;
    }
}
```
This code is a semantic clone of the provided code and will provide the same output for the same inputs. Only variable names and class names have been changed, but their functionality remains the same.