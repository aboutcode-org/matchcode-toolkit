Here is a refactored version of the provided C++ code in Java. 

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine()
            .replace(" ", "")
            .replace("[", "")
            .replace("]", "");

        String[] strs = str.split(",");
        List<Integer> nums = new ArrayList<>();
        for (String s : strs)
            nums.add(Integer.parseInt(s));

        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        System.out.println(cost);

        scanner.close();
    }
}

class Solution {
    public long minimumCost(List<Integer> nums) {
        long ans = Long.MAX_VALUE;
        int n = nums.size();

        Collections.sort(nums);
        int median = (n % 2 != 0) ? nums.get(n / 2) : (nums.get(n / 2) + nums.get(n / 2 - 1)) / 2;

        List<Long> pal = new ArrayList<>();
        String t = String.valueOf(median);
        pal.add((long) Math.pow(10, t.length() - 1) - 1); // case 1 : next palindrome with one digit less
        pal.add((long) Math.pow(10, t.length()) + 1);  // case 2: next palindrome with one digit more

        // case 3: palindrome with just mirror image of the left half
        for (int i = 0; i < t.length() / 2; i++)
            t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);

        pal.add(Long.parseLong(t));

        // case 4: palindrome with mirror image of the left half with left half + 1
        String pal_next = String.valueOf(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        if (t.length() % 2 != 0) pal_next = pal_next.substring(0, pal_next.length() -1);
        pal.add(Long.parseLong(pal_next + new StringBuilder(pal_next).reverse().toString()));

        // case 5: palindrome with mirror image of the left half with left half - 1
        String pal_prev = String.valueOf(Integer.parseInt(t.substring(0,(t.length() + 1) / 2)) - 1);
        if (t.length() % 2 != 0) pal_prev = pal_prev.substring(0, pal_prev.length()-1);
        pal.add(Long.parseLong(pal_prev + new StringBuilder(pal_prev).reverse().toString()));
        
        // find the cost with each palindrome and take the min as answer
        for (long p : pal) {
            long sum = 0;
            for (int num : nums) sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }
}
```

This Java code does precisely what the C++ code does. It computes the minimum cost to make all array elements palindromes. The array elements are read as an input line separated by commas, and the minimum cost is printed out. '+' operator is used for string concatenation in Java. 
Since Java does not support pass by reference, a new ArrayList is used instead of an array. Collections.sort() is used instead of sort().