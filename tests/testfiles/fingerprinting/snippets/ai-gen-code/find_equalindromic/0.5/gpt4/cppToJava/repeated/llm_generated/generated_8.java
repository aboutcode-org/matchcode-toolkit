Here is the semantic equivalent code in Java:

```java
import java.util.*;
import java.lang.Math;

public class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.length;
        Arrays.sort(nums);
        median = (n % 2 != 0) ? nums[n / 2] : (nums[n / 2] + nums[n / 2 - 1]) / 2;
        ArrayList<Long> pal = new ArrayList<>();
        String t = String.valueOf(median);
        pal.add((long) Math.pow(10, t.length() - 1) - 1);
        pal.add((long) Math.pow(10, t.length()) + 1);
        for (int i = 0; i < t.length() / 2; ++i) {
            t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
        }
        pal.add(Long.parseLong(t));
        String pal_next = String.valueOf(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if (t.length() % 2 != 0) {
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        }
        pal_next = new StringBuilder(pal_next).reverse().toString();
        pal.add(Long.parseLong(temp + pal_next));
        String pal_prev = String.valueOf(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if (t.length() % 2 != 0) {
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        }
        pal_prev = new StringBuilder(pal_prev).reverse().toString();
        pal.add(Long.parseLong(temp + pal_prev));
        for (long p : pal) {
            long sum = 0;
            for (int num : nums) {
                sum += Math.abs(num - p);
            }
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().replace("[", "").replace("]", "").split(",");
        int[] nums = new int[line.length];
        for (int i = 0; i < line.length; i++) {
            nums[i] = Integer.parseInt(line[i]);
        }
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

This Java code does the same thing as the original C++ code. It reads a line of input, parses it into an array of integers, and then finds the minimum cost of converting the array into a palindrome. The implementation of the `minimumCost` method follows the same logic as the original C++ code.