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
        for (int b = n; b < 1000000000; b++) {
            if (isPalindrome(b)) {
                res = Math.min(res, sumDiff(b, nums));
                break;
            }
        }
        return res;
    }

    private int sumDiff(int n, int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - n);
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

This Java code does the same thing as the Python code. It reads an input string, converts it to an array of integers, and then uses the `minimumCost` method of the `Solution` class to calculate and print the minimum cost. The `minimumCost` method sorts the array, and then checks the middle two elements of the array to find the minimum cost. The `check` method checks if a number is a palindrome and calculates the sum of the absolute differences between the palindrome and the elements of the array. The `isPalindrome` method checks if a number is a palindrome, and the `sumDiff` method calculates the sum of the absolute differences between a number and the elements of an array.