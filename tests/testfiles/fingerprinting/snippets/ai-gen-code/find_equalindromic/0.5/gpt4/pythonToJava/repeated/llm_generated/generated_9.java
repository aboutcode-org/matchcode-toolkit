Below is a Java semantic code clone of the provided Python code:

```java
import java.util.*;

public class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n/2], nums), check(nums[n/2-1], nums));
    }

    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (pal(a)) {
                res = sumDiff(a, nums);
                break;
            }
        }
        for (int b = n; b < Math.pow(10, 9); b++) {
            if (pal(b)) {
                res = Math.min(res, sumDiff(b, nums));
                break;
            }
        }
        return res;
    }

    private boolean pal(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    private int sumDiff(int a, int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - a);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] numStrings = input.split(",");
        int[] nums = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            nums[i] = Integer.parseInt(numStrings[i].trim());
        }
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```
This Java code does the same thing as the Python code. It defines a class `Solution` with a method `minimumCost` that takes an array of integers and returns the minimum cost. The `check` method checks for the palindrome number and calculates the sum of differences between the palindrome number and the array elements. The `pal` method checks if a number is palindrome or not. The `sumDiff` method calculates the sum of differences between a number and all the elements in the array. The main method reads the input, parses it into an array, and calls the `minimumCost` method.