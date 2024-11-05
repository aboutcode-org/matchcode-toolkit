Here is a semantically equivalent clone for the provided Java code:

```java
import java.util.Scanner;
import java.util.Arrays;

class Main {
    public long findMinimumCost(int[] inputs) {
        int len = inputs.length;
        Arrays.sort(inputs);
        int med = inputs[len / 2];
        int add = med;
        int subtract = med;

        while (!checkPal(add)) add++;
        while (!checkPal(subtract)) subtract--;

        return Math.min(findCost(inputs, add), findCost(inputs, subtract));
    }

    public boolean checkPal(int n) {
        int rem, added = 0, temporary;
        temporary = n;
        while (n > 0) {
            rem = n % 10;
            added = (added * 10) + rem;
            n = n / 10;
        }
        return temporary == added;
    }

    public long findCost(int[] inputs, int r) {
        long totalCost = 0;
        for (int val: inputs) totalCost += Math.abs(val - r);
        return totalCost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputData = scan.nextLine();

        // Remove the square brackets from the input string
        inputData = inputData.substring(1, inputData.length() - 1);

        // Split the input string into an array of strings
        String[] strNumbers = inputData.split(",");

        // Convert the string array to an array of integers
        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        // Create an instance of the Main class and call the findMinimumCost method
        Main objMain = new Main();
        long totalCost = objMain.findMinimumCost(numbers);

        // Print the result
        System.out.println(totalCost);
        scan.close();
    }
}
```

In this clone, we have refactored the methods and variable names to different but meaningful ones while preserving the same behavior. Finnish allows the content of the methods and the main idea of the script to remain the same, which is finding the minimum cost of sorting an array.