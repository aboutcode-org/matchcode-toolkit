Below is a Java semantic code clone for the given Python code.

```java
import java.util.*;
import java.lang.*;

public class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(n, nums[n / 2]), check(n, nums[n / 2 - 1]));
    }

    private boolean isPalindrome(int n) {
        String t = String.valueOf(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    private int check(int n, int num) {
        int res = Integer.MAX_VALUE;
        for (int a = num; a > 0; a--) {
            if (isPalindrome(a)) {
                res = getSum(n, a);
                break;
            }
        }
        for (int b = num; b < Math.pow(10, 9); b++) {
            if (isPalindrome(b)) {
                res = Math.min(res, getSum(n, b));
                break;
            }
        }
        return res;
    }

    private int getSum(int n, int num) {
        int sum = 0;
        for (int it : nums) {
            sum += Math.abs(it - num);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input_str = scanner.nextLine();
        String[] nums_str = input_str.split(",");
        int[] nums = new int[nums_str.length];
        for (int i = 0; i < nums_str.length; i++) {
            nums[i] = Integer.parseInt(nums_str[i]);
        }
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

Please note that this Java code assumes that the input string is a comma-separated list of integers.