Here is the Java semantic clone of the provided C++ code:

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine(); // Read the input line

        // Remove the square brackets, trim the string and split it based on comma.
        String[] items = line.substring(1, line.length() - 1).trim().split(",");
        
        List<Integer> nums = new ArrayList<>();

        // Parse the integers from the string
        for (String item: items) {
            nums.add(Integer.parseInt(item));
        }

        Long cost = Solution.minimumCost(nums);

        // Print the result
        System.out.println(cost);
    }
}

class Solution {
    public static long minimumCost(List<Integer> nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.size();
        Collections.sort(nums);
        median = (n % 2 != 0) ? nums.get(n / 2) : (nums.get(n / 2) + nums.get(n / 2 - 1)) / 2;
        List<Long> pals = new ArrayList<>();
        String numString = String.valueOf(median);
        pals.add((long) Math.pow(10, numString.length() - 1) - 1);
        pals.add((long) Math.pow(10, numString.length()) + 1);
        for (int i = 0; i < numString.length() / 2; ++i)
            numString = numString.substring(0, numString.length() - i - 1)
                    + numString.charAt(i)
                    + numString.substring(numString.length() - i);
        pals.add(Long.parseLong(numString));
        String pal_next = String.valueOf(Integer.parseInt(numString.substring(0, (numString.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if (numString.length() % 2 != 0)
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        pals.add(Long.parseLong(temp + new StringBuilder(pal_next).reverse().toString()));
        String pal_prev = String.valueOf(Integer.parseInt(numString.substring(0, (numString.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if (numString.length() % 2 != 0)
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pals.add(Long.parseLong(temp + new StringBuilder(pal_prev).reverse().toString()));
        for (Long p : pals) {
            long sum = 0;
            for (Integer num : nums) sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }
}
```
This version in Java maintains the core functionality and structure of the original C++ script, with necessary adaptations to fit Java's syntax and conventions.

Note: Java doesn't support operator overloading. So string addition and substraction in the given C++ is mainly handled using substring, charAt, and concatenation. It also uses StringBuilder to reverse strings in some cases.