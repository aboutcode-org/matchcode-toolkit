Here is a Java semantic code clone of the provided C++ code:

```java
import java.util.*;
import java.lang.Math;
import java.io.*;

public class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE, median = 0, n = nums.length;
        Arrays.sort(nums);
        median = (n % 2) != 0 ? nums[(int)n/2] : (nums[(int)n/2] + nums[(int)n/2 - 1])/2;
        //Find out possible palindroms
        ArrayList<Long> pal = new ArrayList<Long>();
        String t = Long.toString(median);
        pal.add((long)Math.pow(10, t.length()-1) - 1); //case 1 : next palindrom with one digit less
        pal.add((long)Math.pow(10, t.length()) + 1);   //case 2: next palindrom with one digit more
        //case 3: palidrom with just mirror image of the left half
        for(int i = 0; i < t.length()/2; ++i) t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
        pal.add(Long.parseLong(t));
        //case 4: palidrom with mirror image of the left half with left half + 1
        String pal_next = Integer.toString(Integer.parseInt(t.substring(0,(t.length()+1)/2)) + 1);
        String temp = pal_next;
        if(t.length()%2 != 0) pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal_next = new StringBuilder(pal_next).reverse().toString();
        pal.add(Long.parseLong(temp + pal_next));
        //case 5: palidrom with mirror image of the left half with left half - 1
        String pal_prev = Integer.toString(Integer.parseInt(t.substring(0,(t.length()+1)/2)) - 1);
        temp = pal_prev;
        if(t.length()%2 != 0)  pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal_prev = new StringBuilder(pal_prev).reverse().toString();
        pal.add(Long.parseLong(temp + pal_prev));
        //find the cost with each palindrom and take the min as answer
        for(long p: pal){
        long sum = 0;
        for(int num: nums) sum += Math.abs(num - p);
        ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        // Remove the square brackets
        line = line.substring(1, line.length() - 2);

        String[] items = line.split(",");
        int[] nums = new int[items.length];

        // Parse the integers from the string
        for (int i = 0; i < items.length; i++) {
            nums[i] = Integer.parseInt(items[i]);
        }

        // Instantiate the Solution class and call the minimumCost method
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        // Print the result
        System.out.println(cost);
    }
}
```

This Java code does the same thing as the given C++ code. It reads a line of input, parses it into an array of integers, finds the minimum cost to make the array a palindrome, and then prints the result. The minimumCost method is a direct translation of the C++ function, using Java's equivalent methods and classes.