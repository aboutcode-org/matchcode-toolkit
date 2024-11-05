Here is a Java semantic code clone of the provided C++ code:

```java
import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE, median = 0, n = nums.length;
        Arrays.sort(nums);
        median = (n % 2 != 0) ? nums[(int)n/2] : (nums[(int)n/2] + nums[(int)n/2 - 1])/2;
        ArrayList<Long> pal = new ArrayList<>();
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
        if(t.length()%2 != 0) pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal_prev = new StringBuilder(pal_prev).reverse().toString();
        pal.add(Long.parseLong(temp + pal_prev));
        for(long p: pal){
            long sum = 0;
            for(int num: nums) sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        line = line.substring(1, line.length() - 2);
        String[] items = line.split(",");
        int[] nums = new int[items.length];
        for(int i = 0; i < items.length; i++) {
            nums[i] = Integer.parseInt(items[i].trim());
        }
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

This Java code does the same thing as the C++ code. It reads a line of input, parses it into an array of integers, and then uses the `minimumCost` method of the `Solution` class to calculate and print the minimum cost. The `minimumCost` method itself is a direct translation of the C++ code, using Java's equivalent classes and methods.