Here is a Java clone of the provided Python code.

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
        for (int a = n; a >= 0; a--) {
            if (pal(a)) {
                res = sumDiffs(a, nums);
                break;
            }
        }

        for (int b = n; b <= Math.pow(10, 9); b++) {
            if (pal(b)) {
                res = Math.min(res, sumDiffs(b, nums));
                break;
            }
        }
        return res;
    }

    private int sumDiffs(int a, int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - a);
        }
        return sum;
    }

    private boolean pal(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }
}

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String inputStr = in.nextLine();
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

Explanation: This Java code imitates the Python version's functionality. The Solution class provides the `minimumCost(int[] nums)` method, which sorts `nums` and returns the smaller result of `check(nums[n/2])` and `check(nums[n/2-1])`. The `check(int n)` helper method calculates the sum of the differences between all elements in `nums` and the closest palindrome lower and higher than `n`. The other helper methods are `sumDiffs(int a, int[] nums)` that calculates the sum of differences and `pal(int n)` that checks if a number is a palindrome. The main method takes a string input, converts it into an integer array, and uses the Solution class to find and print the minimum cost.