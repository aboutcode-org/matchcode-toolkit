Below is the Java code semantic clone of the provided C++ code snippet: 

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().replace("[", "").replace("]", "").split(",");
        List<Integer> nums = new ArrayList<>();
        for (String item : line) {
            nums.add(Integer.parseInt(item.trim()));
        }
        System.out.println(new Solution().minimumCost(nums));
    }
}

class Solution {
    public long minimumCost(List<Integer> nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.size();
        Collections.sort(nums);
        median = (n % 2 != 0) ? nums.get(n / 2) : (nums.get(n / 2) + nums.get(n / 2 - 1)) / 2;
        List<Long> pal = new ArrayList<>();
        String t = String.valueOf(median);
        pal.add((long) Math.pow(10, t.length() - 1) - 1);
        pal.add((long) Math.pow(10, t.length()) + 1);
        for (int i = 0; i < t.length() / 2; ++i)
            t = new StringBuilder(t).replace(t.length() - i - 1, t.length() - i, String.valueOf(t.charAt(i))).toString();
        pal.add(Long.parseLong(t));

        String pal_next = Integer.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if (t.length() % 2 != 0) pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal.add(Long.parseLong(temp + new StringBuilder(pal_next).reverse().toString()));

        String pal_prev = Integer.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if (t.length() % 2 != 0) pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal.add(Long.parseLong(temp + new StringBuilder(pal_prev).reverse().toString()));

        for (long p : pal) {
            long sum = 0;
            for (int num : nums) sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }
}
```
In this Java code clone, first, the inputs are read and parsed using a `Scanner` object. An instance of the `Solution` class is created and the `minimumCost` method is invoked with the parsed list of integers, `nums`. Inside the `minimumCost` method, the list 'nums' is sorted and manipulations are done (similar to the C++ code) to find the minimum cost. The final minimum cost is printed out.