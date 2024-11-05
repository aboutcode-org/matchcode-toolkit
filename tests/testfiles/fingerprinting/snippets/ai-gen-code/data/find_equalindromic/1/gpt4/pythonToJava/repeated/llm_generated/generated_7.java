The semantics of the Python code clone in Java is as follows:

```java
import java.util.*;

public class Solution {
    private boolean pal(int n) {
        String t = String.valueOf(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }
    
    private int check(int[] nums, int n) {
        int res = Integer.MAX_VALUE;

        for (int a = n; a > 0 ; a--){
            if (pal(a)){
                int sum = 0;
                for (int num : nums){
                    sum += Math.abs(num - a);
                }
                res = sum;
                break;
            }
        } 
        
        for (int b = n; b < 1000000000; b++){
            if (pal(b)){
                int sum = 0;
                for (int num : nums) {
                    sum += Math.abs(num - b);
                }
                res = Math.min(res, sum);
                break;
            }
        }
        return res;
    }

    public int minCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.min(check(nums, nums[n / 2]), check(nums, nums[n / 2 - 1]));
    }
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        // You can test your program using this integer array, you should convert your String input into this forma.
        int[] nums = new int[]{x, y, z, ..., n};
        System.out.println(solution.minCost(nums));
    }
}
```

Please replace `x, y, z, ..., n` with actual numerical values for testing. This solution expects that you have the integer array 'nums' ready for input. If you want to take a string input, you may need to convert it to an integer array using functions like `Integer.parseInt()`.