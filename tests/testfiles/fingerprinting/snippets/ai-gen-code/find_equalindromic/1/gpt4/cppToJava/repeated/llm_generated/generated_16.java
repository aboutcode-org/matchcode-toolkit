Here is a Java implementation of the given C++ code. Note that due to the differences between C++ and Java, some code had to be modified.

```java
import java.util.*;
import java.math.*;

public class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE, n = nums.length;
        Arrays.sort(nums);

        long median = (n %2 != 0)?nums[(int)n/2]:(nums[(int)n/2] + nums[(int)n/2 - 1])/2;
        List<Long> pal = new ArrayList<>();

        String t = Long.toString(median);
        pal.add((long)Math.pow(10,t.length() - 1) - 1);
        pal.add((long)Math.pow(10, t.length()) + 1);

        for(int i = 0; i < t.length()/2; ++i) 
            t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);

        pal.add(Long.parseLong(t));

        String pal_next = Long.toString(Integer.parseInt(t.substring(0,(t.length() + 1)/2)) + 1);
        String temp = pal_next;
        if(t.length() % 2 != 0) 
            pal_next = pal_next.substring(0, pal_next.length()  - 1);
        pal_next = new StringBuilder(pal_next).reverse().toString();
        pal.add(Long.parseLong(temp + pal_next));

        String pal_prev = Long.toString(Integer.parseInt(t.substring(0,(t.length() + 1)/2)) - 1);
        temp = pal_prev;
        if(t.length() % 2 != 0) 
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
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
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine(); 
        
        line = line.substring(1, line.length() - 2);
        String[] arr = line.split(",");
        int[] nums = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();

        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        System.out.println(cost);

        sc.close();
    }
}
```
The primary changes are made to support reading from console, absence of product function in String class and reverse function in Java, use of Long.parseLong() instead of stoll, calculation of median, and distribution of numbers from string array to integer array.