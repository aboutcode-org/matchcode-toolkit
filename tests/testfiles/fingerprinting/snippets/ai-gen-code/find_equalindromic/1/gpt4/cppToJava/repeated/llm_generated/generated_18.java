Here is the semantic clone of the code in Java:

```java
import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    public long minimumCost(long[] nums) {
        long ans = Long.MAX_VALUE, median = 0, n = nums.length;
        Arrays.sort(nums);
        median = (n % 2 != 0) ? nums[(int)n/2] : (nums[(int)n/2] + nums[(int)n/2 - 1])/2;
        List<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long)Math.pow(10, t.length()-1) - 1);
        pal.add((long)Math.pow(10, t.length()) + 1);
        for(int i = 0; i < t.length()/2; ++i) t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
        pal.add(Long.parseLong(t));
        String pal_next = Long.toString(Long.parseLong(t.substring(0,(t.length()+1)/2)) + 1);
        String temp = pal_next;
        if(t.length()%2 != 0) pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal_next = new StringBuilder(pal_next).reverse().toString();
        pal.add(Long.parseLong(temp + pal_next));
        String pal_prev = Long.toString(Long.parseLong(t.substring(0,(t.length()+1)/2)) - 1);
        temp = pal_prev;
        if(t.length()%2 != 0)  pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal_prev = new StringBuilder(pal_prev).reverse().toString();
        pal.add(Long.parseLong(temp + pal_prev));
        for(Long p: pal){
        long sum = 0;
        for(long num: nums) sum += Math.abs(num - p);
        ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main (String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // To read from console
        String line = br.readLine().trim();
        line = line.substring(1, line.length() - 1); // Remove the square brackets

        String[] arrStr = line.split(",");
        long[] nums = new long[arrStr.length];

        for(int i = 0; i < arrStr.length; i++) {
            nums[i] = Long.parseLong(arrStr[i].trim());
        }
        
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}
```

Please note the use of `java.util.*` package for list operations, `java.lang.Math` class for mathematical operations such as `Math.pow()` and `Math.abs()` and `java.lang.Long` class for the `parseLong()` method. Moreover, Java does not support passing String by reference, so the changes in the string variable in the loop need to be manually re-assigned. Also, it should be pointed out that in Java the array indices must be of type `int`, not `long`.