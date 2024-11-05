Below is a Java semantic code clone of the provided Python code:

```java
import java.util.*;
import java.lang.*;

class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n/2], nums), check(nums[n/2-1], nums));
    }

    private boolean isPalindrome(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (isPalindrome(a)) {
                res = calculateSum(a, nums);
                break;
            }
        }
        for (int b = n; b < Math.pow(10, 9); b++) {
            if (isPalindrome(b)) {
                res = Math.min(res, calculateSum(b, nums));
                break;
            }
        }
        return res;
    }

    private int calculateSum(int a, int[] nums) {
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
        String[] inputStr = scanner.nextLine().split(",");
        int[] nums = new int[inputStr.length];
        for (int i = 0; i < inputStr.length; i++) {
            nums[i] = Integer.parseInt(inputStr[i].trim());
        }
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

In the Java version, the `main` method reads a comma-separated string of integers from the console, converts it into an array of integers, and then uses it to create a `Solution` instance and call the `minimumCost` method. The result is then printed to the console. The `minimumCost` method and its helper methods (`isPalindrome`, `check`, and `calculateSum`) are implemented in the `Solution` class.