Here is the Java code equivalent:

```java
import java.util.stream.*;
import java.util.*;
import java.text.DecimalFormat;
import java.io.*;

public class Solution {
    public long minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long median = (n % 2 == 1)? nums[n / 2] : (nums[n / 2] + nums[n / 2 - 1]) / 2;
        List<Long> pal = new ArrayList<Long>();

        DecimalFormat formatter = new DecimalFormat("0.#");
        String t = formatter.format(median);
        pal.add((long) Math.pow(10, t.length() - 1) - 1); 
        pal.add((long) Math.pow(10, t.length()) + 1);   

        StringBuffer str = new StringBuffer(t);
        for (int i = 0; i < t.length() / 2; i++) str.setCharAt(t.length() - i - 1, str.charAt(i));
        pal.add(Long.parseLong(str.toString()));

        String pal_next = Integer.toString(Integer.parseInt(str.substring(0,(str.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if (str.length() % 2 == 1) pal_next = pal_next.substring(0, pal_next.length() - 1);
        StringBuffer next = new StringBuffer(pal_next);
        pal.add(Long.parseLong(temp + next.reverse().toString()));

        String pal_prev = Integer.toString(Integer.parseInt(str.substring(0,(str.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if (str.length() % 2 == 1) pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        StringBuffer prev = new StringBuffer(pal_prev);
        pal.add(Long.parseLong(temp + prev.reverse().toString()));

        long ans = Long.MAX_VALUE;
        for(long p: pal){
            long sum = 0;
            for(int num: nums) sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = reader.readLine().replaceAll("\\[|\\]", "").split(",");
        int[] arr = Arrays.stream(nums).mapToInt(Integer::parseInt).toArray();
        Solution solution = new Solution();
        long cost = solution.minimumCost(arr);
        System.out.println(cost);
    }
}
```
In this Java code, BufferedReader is used to read the input integers and parseFloat() is used to convert a number string to a number. DecimalFormat is used to format a number to a specific string format. It behaves the same as the provided C++ code by taking in an array of numbers and finding the minimum cost to make all the array numbers equal. The number to be equal to is one of the palindromes that is formed from the median of the array numbers.
