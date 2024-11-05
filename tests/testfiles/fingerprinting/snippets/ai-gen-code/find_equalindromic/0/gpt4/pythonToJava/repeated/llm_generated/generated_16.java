Below is the Java semantic code clone of the given Python code:

```java
import java.util.Arrays;
import java.util.Scanner;

class Solution {
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
                res = sumDiff(a, nums);
                break;
            }
        }
        for (int b = n; b < Math.pow(10, 9); b++) {
            if (isPalindrome(b)) {
                res = Math.min(res, sumDiff(b, nums));
                break;
            }
        }
        return res;
    }

    private int sumDiff(int a, int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - a);
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] strNums = input.split(",");
        int[] nums = new int[strNums.length];
        for (int i = 0; i < strNums.length; i++) {
            nums[i] = Integer.parseInt(strNums[i]);
        }
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

This Java code does the same thing as the Python code. It reads an input string, converts it to an array of integers, and then uses the `minimumCost` method of the `Solution` class to calculate and print the minimum cost. The `minimumCost` method sorts the array and then checks the middle two numbers to see which one gives the lower cost when used as the base for calculating the sum of the absolute differences with all the other numbers. The `check` method checks for the nearest palindrome number to the given number in both directions and calculates the sum of the absolute differences. The `isPalindrome` method checks if a number is a palindrome.