Below is a semantic clone of the Python code in Java:

```java
import java.util.*;

public class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        int check(int n) {
            int res = Integer.MAX_VALUE;
            for (int a = n; a > 0; a--) {
                if (pal(a)) {
                    res = sumDiffs(nums, a);
                    break;
                }
            }
            for (int b = n; b < Math.pow(10, 9); b++) {
                if (pal(b)) {
                    res = Math.min(res, sumDiffs(nums, b));
                    break;
                }
            }
            return res;
        }

        boolean pal(int n) {
            String t = Integer.toString(n);
            return t.equals(new StringBuilder(t).reverse().toString());
        }

        int sumDiffs(int[] nums, int val) {
            int sum = 0;
            for (int num : nums) {
                sum += Math.abs(num - val);
            }
            return sum;
        }

        return Math.min(check(nums[n / 2]), check(nums[n / 2 - 1]));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numsStr = scanner.nextLine().split(",");
        int[] nums = new int[numsStr.length];
        for (int i = 0; i < numsStr.length; i++) {
            nums[i] = Integer.parseInt(numsStr[i]);
        }
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

This Java code does the same thing as the Python code. It sorts the input array, checks for palindrome numbers around the middle of the sorted array, and returns the minimum sum of the absolute differences between the input numbers and the palindrome number. The `main` function reads a string of comma-separated numbers from the standard input, converts it to an array of integers, and prints the minimum cost.