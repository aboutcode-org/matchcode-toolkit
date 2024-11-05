Below is the Java version of the given Python code:

```java
import java.util.*;

public class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        
        return Math.min(check(nums[n/2], nums), check(nums[n/2-1], nums));
    }
    
    private boolean pal(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }
    
    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (pal(a)) {
                res = sumDiff(a, nums);
                break;
            }
        }
        for (int b = n; b < 1000000000; b++) {
            if (pal(b)) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputStr = scanner.nextLine().split(",");
        int[] nums = new int[inputStr.length];
        for (int i = 0; i < inputStr.length; i++) {
            nums[i] = Integer.parseInt(inputStr[i]);
        }
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

This Java code does the same thing as the Python code. I've created a `Solution` class with a `minimumCost` method that sorts the input array and returns the minimum cost by checking the middle elements of the sorted array. I've also created helper methods `pal`, `check`, and `sumDiff` to perform palindrome check, cost calculation, and sum of differences calculation respectively. The `main` method reads the input string from the user, converts it to an integer array, and then calls the `minimumCost` method to get the minimum cost.