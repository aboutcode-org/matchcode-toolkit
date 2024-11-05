Here is the Java version of the given C++ code:

```java
import java.util.*;

public class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE, median = 0, n = nums.length;
        Arrays.sort(nums);
        median = (n % 2 != 0) ? nums[(int) n / 2] : (nums[(int) n / 2] + nums[(int) n / 2 - 1]) / 2;
        List<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long)Math.pow(10, t.length() - 1) - 1);
        pal.add((long)Math.pow(10, t.length()) + 1);
        for(int i = 0; i < t.length() / 2; ++i) t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
        pal.add(Long.parseLong(t));
        String pal_next = Long.toString(Long.parseLong(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if(t.length() % 2 != 0) pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal_next = new StringBuilder(pal_next).reverse().toString();
        pal.add(Long.parseLong(temp + pal_next));
        String pal_prev = Long.toString(Long.parseLong(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if(t.length() % 2 != 0) pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal_prev = new StringBuilder(pal_prev).reverse().toString();
        pal.add(Long.parseLong(temp + pal_prev));
        for(long p: pal){
            long sum = 0;
            for(int num: nums) sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        line = line.substring(1, line.length() - 2);
        String[] items = line.split(",");
        int[] nums = new int[items.length];
        for(int i = 0; i < items.length; ++i) nums[i] = Integer.parseInt(items[i]);
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

This code does the same thing as the C++ code. It reads a line of input, parses it into an array of integers, and then computes the minimum cost of transforming the array into a palindrome. The main differences are in the syntax and the standard library functions used. For example, in Java we use `Arrays.sort()` instead of `std::sort`, and `Long.MAX_VALUE` instead of `LONG_MAX`.