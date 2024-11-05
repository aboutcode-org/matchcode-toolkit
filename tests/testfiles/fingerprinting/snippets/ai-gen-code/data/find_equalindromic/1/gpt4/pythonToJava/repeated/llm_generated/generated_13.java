Below is a Java semantic equivalent to the provided Python code.

```java
import java.util.*;

public class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n / 2], nums), check(nums[n / 2 - 1], nums));
    }

    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (pal(a)) {
                res = getSum(a, nums);
                break;
            }
        }

        for (int b = n; b < Math.pow(10, 9); b++) {
            if (pal(b)) {
                res = Math.min(res, getSum(b, nums));
                break;
            }
        }
        return res;
    }

    private boolean pal(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    private int getSum(int key, int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += Math.abs(i - key);
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);

        String[] parts = scn.nextLine().split(",");
        int[] nums = new int[parts.length];
        for (int n = 0; n < parts.length; n++) {
            nums[n] = Integer.parseInt(parts[n]);
        }
        scn.close();

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}
```

In this Java code, a `Solution` class is defined with one method: `minimumCost`. The `minimumCost` method contains two helper methods: `check` and `pal`.

- `check`: checks to see if there is a palindrome existing between `n` (midpoint of sorted `nums` array) to `0` and between `n` to 10^9. If it finds a palindrome, it gets the absolute difference between every element in `nums` and the palindrome value and assigns it to the variable, `res`. And finally returns `res`.
  
- `pal`: Checks if a given value is a palindrome. 

The `main` method reads a line of integers from the console input. This line should contain integers separated by commas. This line is then split by the comma and parsed into an array of integers. After that, an instance of `Solution` class is created and its `minimumCost` method is called with the parsed array of integers. Finally, result is printed to the console.