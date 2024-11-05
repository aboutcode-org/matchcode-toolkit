Here's the equivalent Java code:

```java
import java.util.*;
import java.lang.*;
import java.io.*;

public class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE, median = 0, n = nums.length;
        Arrays.sort(nums);
        median = (n %2 != 0)? nums[(int)n/2]: (nums[(int)n/2] + nums[(int)(n/2) - 1])/2;
        List<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long)Math.pow(10, t.length()-1) - 1); 
        pal.add((long)Math.pow(10, t.length()) + 1); 
        for(int i = 0; i < t.length()/2; ++i) {
            t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
        }
        pal.add(Long.parseLong(t));
        String pal_next = Long.toString(Long.parseLong(t.substring(0,(t.length()+1)/2)) + 1);
        String temp = pal_next;
        if(t.length()%2 != 0) 
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal_next = new StringBuilder(pal_next).reverse().toString();
        pal.add(Long.parseLong(temp + pal_next));
        String pal_prev = Long.valueOf(Long.parseLong(t.substring(0,(t.length()+1)/2)) - 1).toString();
        temp = pal_prev;
        if(t.length()%2 != 0)  
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal_prev = new StringBuilder(pal_prev).reverse().toString();
        pal.add(Long.parseLong(temp + pal_prev));
        for(long p: pal){
            long sum = 0;
            for(int num: nums) 
                sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        line = line.substring(1, line.length() - 1);
        String[] strings = line.split(",");
        int[] nums = new int[strings.length];
        for (int i = 0; i < nums.length; i++)
            nums[i] = Integer.parseInt(strings[i]);
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```
The changes made involve adapting the C++ code to the Java language. This includes IO formatting, type conversion, string manipulation, using classes and methods from the Java Standard Library instead of the C++ Standard Library, and altering the syntax to match Java's specifications.