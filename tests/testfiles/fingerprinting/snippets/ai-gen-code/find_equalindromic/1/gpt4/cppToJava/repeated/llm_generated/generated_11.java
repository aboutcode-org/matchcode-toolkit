Below is the Java implementation of the provided C++ code:

```java
import java.util.*;
import java.lang.*;
import java.math.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] numStrings = input.nextLine().replaceAll("\\[", "").replaceAll("\\]", "").split(",");
        ArrayList<Long> nums = new ArrayList<>();
        for(String s : numStrings)
            nums.add(Long.parseLong(s.trim()));
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}

class Solution {
    public Long minimumCost(ArrayList<Long> nums) {
        Long ans = Long.MAX_VALUE, median = 0l;
        int n = nums.size();
        Collections.sort(nums);
        median = (n % 2 != 0) ? nums.get(n / 2) : (nums.get(n / 2) + nums.get(n / 2 - 1)) / 2;
        ArrayList<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long)Math.pow(10, t.length() - 1) - 1);
        pal.add((long)Math.pow(10, t.length()) + 1);
        for(int i = 0; i < t.length() / 2; ++i)
            t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
        pal.add(Long.parseLong(t));

        String pal_next = Integer.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if(t.length() % 2 != 0)
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal_next = new StringBuilder(pal_next).reverse().toString();
        pal.add(Long.parseLong(temp + pal_next));

        String pal_prev = Integer.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if(t.length() % 2 != 0)
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal_prev = new StringBuilder(pal_prev).reverse().toString();
        pal.add(Long.parseLong(temp + pal_prev));

        for(long p : pal) {
            long sum = 0;
            for(long num : nums)
                sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }
}
```

The main differences between the two languages in this case include the usage of ArrayList in Java instead of std::vector in C++, the usage of Scanner for reading lines from the console in Java instead of using std::cin in C++, the usage of Long instead of long long in C++, and the different way of reversing a string in Java and C++.