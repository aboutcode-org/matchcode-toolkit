Below is the Java semantic clone of the provided Python code. The Java code also sorts the input list of integers, checks for palindromes and calculates the minimum cost. 

```java
import java.util.*;

public class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n / 2], nums), check(nums[n / 2 - 1], nums));
    }

    private boolean isPalindrome(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (isPalindrome(a)) {
                res = sumAbsDiff(a, nums);
                break;
            }
        }
        for (int b = n; b < 1000000000; b++) {
            if (isPalindrome(b)) {
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
        String[] input = scanner.nextLine().split(",");
        int[] nums = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

In this Java code, the `minimumCost` method does the same thing as in the Python code. It sorts the array of numbers and finds the minimum cost. The `isPalindrome` method checks if a number is a palindrome. The `check` method finds the minimum cost for a given number and the `sumAbsDiff` method calculates the sum of absolute differences between a given number and all numbers in the array. The `main` method reads the input, converts it to an array of integers, calls the `minimumCost` method and prints the result.