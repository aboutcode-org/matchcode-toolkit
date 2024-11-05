Here is the equivalent Java code:

```java
import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE, median = 0, n = nums.length;
        Arrays.sort(nums);
        median = (n %2 != 0)?nums[(int)n/2]: (nums[(int)n/2] + nums[(int)n/2 - 1])/2;
        //Find out possible palindroms
        List<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long)Math.pow(10, t.length()-1) - 1); //case 1 : next palindrom with one digit less
        pal.add((long)Math.pow(10, t.length()) + 1);   //case 2: next palindrom with one digit more
        //case 3: palidrom with just mirror image of the left half
        for(int i = 0; i < t.length()/2; ++i) t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
        pal.add(Long.parseLong(t));
        //case 4: palidrom with mirror image of the left half with left half + 1
        String pal_next = Long.toString(Long.parseLong(t.substring(0,(t.length()+1)/2)) + 1);
        String temp = pal_next;
        if(t.length()%2 != 0) pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal_next = new StringBuilder(pal_next).reverse().toString();
        pal.add(Long.parseLong(temp + pal_next));
        //case 5: palidrom with mirror image of the left half with left half - 1
        String pal_prev = Long.toString(Long.parseLong(t.substring(0,(t.length()+1)/2)) - 1);
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
    
    public static void main (String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        line = line.substring(1, line.length() - 2);
        String[] numsStr = line.split(",");
        int[] nums = new int[numsStr.length];
        for (int i = 0; i < numsStr.length; i++) {
            nums[i] = Integer.parseInt(numsStr[i]);
        }
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

This Java code does the same thing as the C++ code. It reads the input line, removes the square brackets, parses the integers from the string, and then calls the minimumCost method on the Solution class. The minimumCost method does the same operations as in the C++ code, but using Java syntax and classes.