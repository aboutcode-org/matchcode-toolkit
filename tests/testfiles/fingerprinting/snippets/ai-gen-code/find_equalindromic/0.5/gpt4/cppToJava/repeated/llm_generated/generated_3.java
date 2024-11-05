Here is the Java version of the above C++ code:

```java
import java.util.*;
import java.lang.*;

public class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.length;
        Arrays.sort(nums);
        median = (n % 2 != 0) ? nums[n / 2] : (nums[n / 2] + nums[n / 2 - 1]) / 2;
        
        // Find out possible palindroms
        ArrayList<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long)Math.pow(10, t.length() - 1) - 1); // case 1 : next palindrome with one digit less
        pal.add((long)Math.pow(10, t.length()) + 1); // case 2: next palindrome with one digit more
        
        // case 3: palindrome with just mirror image of the left half
        for(int i = 0; i < t.length() / 2; ++i) 
            t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
        pal.add(Long.parseLong(t));
        
        // case 4: palindrome with mirror image of the left half with left half + 1
        String pal_next = Long.toString(Long.parseLong(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if(t.length() % 2 != 0) 
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal_next = new StringBuilder(pal_next).reverse().toString();
        pal.add(Long.parseLong(temp + pal_next));
        
        // case 5: palindrome with mirror image of the left half with left half - 1
        String pal_prev = Long.toString(Long.parseLong(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if(t.length() % 2 != 0) 
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal_prev = new StringBuilder(pal_prev).reverse().toString();
        pal.add(Long.parseLong(temp + pal_prev));
        
        // find the cost with each palindrome and take the min as answer
        for(long p: pal){
            long sum = 0;
            for(int num: nums) 
                sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().replace("[", "").replace("]", "").split(",");
        int[] nums = new int[input.length];
        for(int i = 0; i < input.length; i++)
            nums[i] = Integer.parseInt(input[i]);
        long cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

This Java code does the same thing as the provided C++ code. It reads an array of integers from the standard input, calls the `minimumCost` method on this array, and then prints the result. The `minimumCost` method itself is a direct translation of the C++ method with the same name. It calculates the minimum cost to make all elements of the array equal to some palindrome number.