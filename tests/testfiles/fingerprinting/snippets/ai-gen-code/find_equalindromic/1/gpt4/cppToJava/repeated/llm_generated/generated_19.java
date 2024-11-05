Here is a Java version of the C++ code provided. Note that the method names, data types, libraries, class names etc. are different in Java and C++. For example, instead of `vector`s, we use `ArrayList`s in Java. There's no exact equivalent of `stoll` and `stoi` methods in Java, so we use `Long.parseLong` and `Integer.parseInt` instead. Here's the equivalent code:

```java
import java.util.*;

public class Solution {
    public long minimumCost(ArrayList<Integer> nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.size();
        Collections.sort(nums);
        median = (n % 2) != 0 ? nums.get(n / 2) : (nums.get(n / 2) + nums.get(n / 2 - 1)) / 2;
        ArrayList<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long) Math.pow(10, t.length() - 1) - 1);
        pal.add((long) Math.pow(10, t.length()) + 1);
        for (int i = 0; i < t.length() / 2; ++i)
            t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
        pal.add(Long.parseLong(t));
        String pal_next = Integer.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if (t.length() % 2 != 0)
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal_next = new StringBuilder(pal_next).reverse().toString();
        pal.add(Long.parseLong(temp + pal_next));
        String pal_prev = Integer.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if (t.length() % 2 != 0)
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal_prev = new StringBuilder(pal_prev).reverse().toString();
        pal.add(Long.parseLong(temp + pal_prev));
        for (Long p : pal) {
            long sum = 0;
            for (Integer num : nums)
                sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine(); 
        line = line.substring(1, line.length() - 2);
        ArrayList<Integer> nums = new ArrayList<>();
        Scanner numScanner = new Scanner(line);
        numScanner.useDelimiter(",");
        while (numScanner.hasNext()) {
            nums.add(Integer.parseInt(numScanner.next().trim()));
        }
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```