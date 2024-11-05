Here is the Java semantic code clone of the given Python code:

```java
import java.util.*;

public class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n/2], nums), check(nums[n/2 - 1], nums));
    }

    private boolean isPal(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (isPal(a)) {
                res = sumAbsDiff(a, nums);
                break;
            }
        }
        for (int b = n; b < 1000000000; b++) {
            if (isPal(b)) {
                res = Math.min(res, sumAbsDiff(b, nums));
                break;
            }
        }
        return res;
    }

    private int sumAbsDiff(int n, int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - n);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] numStrings = input.split(",");
        int[] nums = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            nums[i] = Integer.parseInt(numStrings[i]);
        }

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

This Java code does the same thing as the Python code. It defines a class `Solution` with a method `minimumCost` that takes an array of integers and returns the minimum cost. The `minimumCost` method uses a helper method `check` to compute the cost of each number and `isPal` to check if a number is palindrome. The `sumAbsDiff` method calculates the sum of absolute differences between a number and all numbers in the array. The `main` method reads an input string from the console, converts it to an array of integers, and calls the `minimumCost` method to compute and print the minimum cost.