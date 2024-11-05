Below is a Java version of your C++ code:

```java
import java.util.*;

public class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.length;
        Arrays.sort(nums);
        median = (n % 2) != 0? nums[n/2] : (nums[n/2] + nums[n/2 - 1])/2;
        ArrayList<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long) Math.pow(10, t.length()-1) - 1);
        pal.add((long) Math.pow(10, t.length()) + 1);
        for(int i = 0; i < t.length()/2; ++i) {
            t = t.substring(0, t.length() - 1 - i) + t.charAt(i) + t.substring(t.length() - i);
        }
        pal.add(Long.parseLong(t));
        String palNext = Long.toString(Long.parseLong(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = palNext;
        if (t.length() % 2 != 0) {
            palNext = palNext.substring(0, palNext.length()-1);
        }
        palNext = new StringBuilder(palNext).reverse().toString();
        pal.add(Long.parseLong(temp + palNext));
        String palPrev = Long.toString(Long.parseLong(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = palPrev;
        if (t.length() % 2 != 0) {
            palPrev = palPrev.substring(0, palPrev.length() - 1);
        }
        palPrev = new StringBuilder(palPrev).reverse().toString();
        pal.add(Long.parseLong(temp + palPrev));
        for (Long p: pal) {
            long sum = 0;
            for (int num : nums) {
                sum += Math.abs(num - p);
            }
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String line = sc.nextLine().trim();
        line = line.substring(1, line.length() - 1);
        String[] items = line.split(",");
        int[] nums = new int[items.length];
        for(int i=0; i<items.length; i++)
            nums[i] = Integer.parseInt(items[i].trim());
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```
This Java code does exactly the same thing as your C++ code. It takes a line of input from the user, processes it into an integer array, calculates the minimum cost, and outputs it.