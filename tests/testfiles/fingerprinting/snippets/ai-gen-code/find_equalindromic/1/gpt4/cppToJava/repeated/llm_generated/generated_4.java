Here is the Java semantic clone of the provided C++ code:

```java
import java.util.*;

public class Solution {
    public long minimumCost(ArrayList<Long> nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.size();
        Collections.sort(nums);
        median = (n %2 != 0)?nums.get(n/2): (nums.get(n/2) + nums.get(n/2 - 1))/2;
        ArrayList<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long) Math.pow(10, t.length()-1) - 1);
        pal.add((long) Math.pow(10, t.length()) + 1);  
        
        for(int i = 0; i < t.length()/2; ++i) 
            t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
        pal.add(Long.parseLong(t));
        
        String pal_next = String.valueOf(Integer.parseInt(t.substring(0,(t.length()+1)/2)) + 1);
        String temp = pal_next;
        if(t.length()%2 != 0) 
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal.add(Long.parseLong(temp + new StringBuilder(pal_next).reverse().toString()));
        
        String pal_prev = String.valueOf(Integer.parseInt(t.substring(0,(t.length()+1)/2)) - 1);
        temp = pal_prev;
        if(t.length()%2 != 0)  
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal.add(Long.parseLong(temp + new StringBuilder(pal_prev).reverse().toString()));
        
        for(long p: pal){
            long sum = 0;
            for(long num: nums) 
                sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        line = line.substring(1, line.length() - 2);
        ArrayList<Long> nums = new ArrayList<>();
        String[] items = line.split(",");

        for (String item : items) 
            nums.add(Long.parseLong(item.trim()));

        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}
```
This Java code is implemented with similar logic and functionality to the provided C++ code. Some changes were made due to differing syntax and library features between the two languages, but the semantic concept is the same.