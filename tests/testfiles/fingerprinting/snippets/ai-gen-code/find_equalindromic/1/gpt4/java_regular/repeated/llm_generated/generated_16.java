Here is the semantic clone of the Java code:

```java
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public long findMinCost(int[] array) {
        int size = array.length;
        List<Integer> nums = new ArrayList<>();
        for(int num: array) nums.add(num);

        Collections.sort(nums);
        int median = nums.get(size/2);

        int increase = median, decrease = median; 

        while(!checkPal(increase)) increase++; 
        while(!checkPal(decrease)) decrease--;

        return Math.min(computeCost(nums,increase), computeCost(nums,decrease));
    }

    public boolean checkPal(int number) {
        int remainder, total = 0, actual;
        actual = number;
        while (number > 0) {
            remainder = number % 10; 
            total = (total * 10) + remainder;
            number = number / 10;
        }
        return actual == total;
    }

    public long computeCost(List<Integer> nums, int val) {
        long cost = 0;
        for (int n: nums) cost += Math.abs(n - val);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); 

        // Remove the square brackets from the input string
        input = input.substring(1, input.length() - 1);

        // Split the input string into an array of strings
        String[] stringNumbers = input.split(",");

        // Convert the string array to an array of integers
        int[] numbers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i].trim());
        }

        // Create an instance of the Main class and call the findMinCost method
        Main main = new Main();
        long cost = main.findMinCost(numbers);

        // Print the result
        System.out.println(cost);
        scanner.close();
    }
}

```

This version uses a list to store the array, but the overall logic remains the same.