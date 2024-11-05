Below is the Java semantic code clone of the provided C++ code:

```java
import java.util.*;
import java.math.*;

public class Solution {
    public long minimumCost(int[] nums) {
        Arrays.sort(nums);
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.length;
        median = (n %2 != 0) ? nums[n/2]: (nums[n/2] + nums[n/2 - 1])/2;
        
        ArrayList<Long> pal = new ArrayList<>();
        String t = String.valueOf(median);
        pal.add((long) Math.pow(10, t.length()-1) - 1);
        pal.add((long) Math.pow(10, t.length()) + 1);
        
        for(int i = 0; i < t.length()/2; ++i) {
            char[] chars = t.toCharArray();
            chars[t.length() - i - 1] = chars[i];
            t = new String(chars);
        }
        pal.add(Long.parseLong(t));
        
        String palNext = String.valueOf(Long.parseLong(t.substring(0, (t.length() + 1)/2)) + 1);
        String temp = palNext;
        if(t.length() %2 != 0) palNext = palNext.substring(0, palNext.length() - 1);
        palNext = new StringBuilder(palNext).reverse().toString();
        pal.add(Long.parseLong(temp + palNext));
        
        String palPrev = String.valueOf(Long.parseLong(t.substring(0, (t.length() + 1)/2)) - 1);
        temp = palPrev;
        if(t.length() %2 != 0) palPrev = palPrev.substring(0, palPrev.length() - 1);
        palPrev = new StringBuilder(palPrev).reverse().toString();
        pal.add(Long.parseLong(temp + palPrev));

        for(long p: pal){
            long sum = 0;
            for(int num: nums) sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String[] inputStrings = scanner.nextLine().replace("[","").replace("]","").split(",");
        int[] nums = new int[inputStrings.length];
        for(int i=0; i<inputStrings.length; i++) {
            nums[i] = Integer.parseInt(inputStrings[i]);
        }

        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        System.out.println(cost);
        scanner.close();
    } 
}
```
The guest code uses vector and mathematical operations heavily along with string processing to compute the minimum cost. Both C++ code and Java code have similar constructs, but the approaches used for string reversals, array sortings, and power operations are specific to each language. The overall structure and logic remain consistent across both languages.