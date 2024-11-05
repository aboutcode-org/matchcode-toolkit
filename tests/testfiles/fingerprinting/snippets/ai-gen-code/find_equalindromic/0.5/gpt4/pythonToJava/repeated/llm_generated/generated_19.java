Below is the Java semantic code clone of the given Python code:

```java
import java.util.Arrays;
import java.util.Scanner;

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
        for (int b = n; b < Math.pow(10, 9); b++) {
            if (isPalindrome(b)) {
                res = Math.min(res, sumAbsDiff(b, nums));
                break;
            }
        }
        return res;
    }

    private int sumAbsDiff(int a, int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - a);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        String[] strNums = inputStr.split(",");
        int[] nums = new int[strNums.length];
        for (int i = 0; i < strNums.length; i++) {
            nums[i] = Integer.parseInt(strNums[i].trim());
        }
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```
In the above Java code, we have the `Solution` class with the `minimumCost` method which is the Java equivalent of the Python code. The `isPalindrome` method checks if a number is a palindrome, and the `check` method checks for the minimum cost. The `sumAbsDiff` method calculates the sum of absolute differences. The `main` method reads the input string, converts it to an array of integers, calls the `minimumCost` method, and prints the result.