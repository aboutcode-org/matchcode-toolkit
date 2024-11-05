```java
import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long minimumCost(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int middle = nums[n/2];
        int inc = middle;
        int dec = middle;

        while(!isPal(inc)) inc++; 
        while(!isPal(dec)) dec--;

        return Math.min(calculateCost(nums,inc), calculateCost(nums,dec));
    }

    public boolean isPal(int n) {
        int r, sum = 0, temp;
        temp = n;
        while (n > 0) {
            r = n % 10; 
            sum = (sum * 10) + r;
            n = n / 10;
        }
        return temp == sum;
    }

    public long calculateCost(int[] nums, int r) {
        long cost = 0;
        for (int n: nums) cost += Math.abs(n - r);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        input = input.substring(1, input.length() - 1);

        // Split the input string into an array of strings
        String[] numberStrings = input.split(",");

        // Convert the string array to an array of integers
        int[] numbers = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i].trim());
        }

        // Create an instance of the Solution class and call the minimumCost method
        Solution solution = new Solution();
        long cost = solution.minimumCost(numbers);

        // Print the result
        System.out.println(cost);
        scanner.close();
    }
}
```