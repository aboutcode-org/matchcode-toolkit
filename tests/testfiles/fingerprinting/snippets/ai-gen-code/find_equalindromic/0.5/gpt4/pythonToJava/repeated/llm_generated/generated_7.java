Here is the Java semantic equivalent of the Python code you provided:

```java
import java.util.*;

class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n/2], nums), check(nums[n/2-1], nums));
    }

    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (isPalindrome(a)) {
                res = getSum(nums, a);
                break;
            }
        }
        for (int b = n; b < 1000000000; b++) {
            if (isPalindrome(b)) {
                res = Math.min(res, getSum(nums, b));
                break;
            }
        }
        return res;
    }

    private boolean isPalindrome(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    private int getSum(int[] nums, int a) {
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
        int[] nums = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```
This Java code does the same thing as the Python code. It reads an array of integers from the command line, sorts the array, and then calculates the minimum cost by checking the middle two elements of the sorted array. The `check` method checks numbers in both directions from the given number until it finds a palindrome, then calculates the sum of the absolute differences between the palindrome and each number in the array. The minimum cost is the smaller of the two sums.