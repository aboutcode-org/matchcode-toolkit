Here is the Java version of this C++ code. Note that some numerical algorithms need to be adjusted because of the subtle difference between language-integer arithmetic operations.

```java
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE;
        int n = nums.length;
        long median;
        Arrays.sort(nums);
        median = (n % 2 != 0) ? nums[n/2] : (nums[n/2] + nums[n/2 -1])/2;

        List<Long> pal = new ArrayList<>();
        String t = String.valueOf(median);
        pal.add((long) Math.pow(10, t.length()-1) - 1);
        pal.add((long) Math.pow(10, t.length()) + 1);

        for(int i = 0; i < t.length()/2; ++i) t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
        pal.add(Long.parseLong(t));

        String pal_next = String.valueOf(Long.valueOf(t.substring(0,(t.length()+1)/2)) + 1);
        String temp = pal_next;
        if(t.length() % 2 == 1) pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal_next = new StringBuilder(pal_next).reverse().toString();
        pal.add(Long.parseLong(temp + pal_next));

        String pal_prev = String.valueOf(Long.valueOf(t.substring(0,(t.length()+1)/2)) - 1);
        temp = pal_prev;
        if(t.length()%2 == 1)  pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
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
        int[] nums = {1, 2, 3, 4, 5};
        long cost = minimumCost(nums);
        System.out.println(cost);
    }
}
```