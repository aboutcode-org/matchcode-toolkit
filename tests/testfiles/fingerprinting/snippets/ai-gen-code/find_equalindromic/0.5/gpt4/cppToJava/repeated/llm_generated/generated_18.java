Below is a Java semantic code clone of the provided C++ code:

```java
import java.util.*;
import java.io.*;

public class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.length;
        Arrays.sort(nums);
        median = (n % 2 != 0) ? nums[n / 2] : (nums[n / 2] + nums[n / 2 - 1]) / 2;
        List<Long> pal = new ArrayList<>();
        String t = String.valueOf(median);
        pal.add((long) Math.pow(10, t.length() - 1) - 1);
        pal.add((long) Math.pow(10, t.length()) + 1);
        for (int i = 0; i < t.length() / 2; ++i)
            t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
        pal.add(Long.parseLong(t));
        String pal_next = String.valueOf(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if (t.length() % 2 != 0)
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal.add(Long.parseLong(temp + new StringBuilder(pal_next).reverse().toString()));
        String pal_prev = String.valueOf(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if (t.length() % 2 != 0)
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal.add(Long.parseLong(temp + new StringBuilder(pal_prev).reverse().toString()));
        for (Long p : pal) {
            long sum = 0;
            for (int num : nums) sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine().trim();
        line = line.substring(1, line.length() - 1);
        String[] items = line.split(",");
        int[] nums = new int[items.length];
        for (int i = 0; i < items.length; i++)
            nums[i] = Integer.parseInt(items[i].trim());
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

This Java code does the same thing as the provided C++ code. It reads a line of input, parses it into an array of integers, passes the array to the `minimumCost` method of the `Solution` class, and then prints the result. The `minimumCost` method calculates the minimum cost to make all the numbers in the array equal to a palindrome by adding or subtracting from them.