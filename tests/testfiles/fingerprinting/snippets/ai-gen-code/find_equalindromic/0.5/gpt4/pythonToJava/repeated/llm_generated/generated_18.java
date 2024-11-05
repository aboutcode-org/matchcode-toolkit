Below is the Java semantic code clone of the provided Python code:

```java
import java.util.*;

public class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n/2], nums), check(nums[n/2-1], nums));
    }

    private boolean isPalindrome(int n) {
        String str = Integer.toString(n);
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (isPalindrome(a)) {
                res = getSum(a, nums);
                break;
            }
        }
        for (int b = n; b < Math.pow(10, 9); b++) {
            if (isPalindrome(b)) {
                res = Math.min(res, getSum(b, nums));
                break;
            }
        }
        return res;
    }

    private int getSum(int n, int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - n);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int[] nums = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```
This Java code does the same thing as the Python code. It sorts the given array, checks for palindrome numbers, calculates the sum of the absolute differences between the palindrome number and each number in the array, and returns the minimum sum. The main difference is in the syntax and some method names, as Java and Python have different conventions.