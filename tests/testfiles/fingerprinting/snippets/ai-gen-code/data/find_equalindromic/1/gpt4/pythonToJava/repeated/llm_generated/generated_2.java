The equivalent Java code would look as follows:

```Java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public int minimumCost(List<Integer> nums) {
        nums.sort(null);
        int n = nums.size();

        return Math.min(check(nums.get(n / 2), nums), check(nums.get(n / 2 - 1), nums));
    }

    private static boolean pal(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    private static int check(int n, List<Integer> nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (pal(a)) {
                res = nums.stream().mapToInt(it -> Math.abs(it - a)).sum();
                break;
            }
        }
        for (int b = n; b < 1000000000; b++) {
            if (pal(b)) {
                res = Math.min(res, nums.stream().mapToInt(it -> Math.abs(it - b)).sum());
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // Create a Scanner object and read the input string
        Scanner scn = new Scanner(System.in);
        String input_str = scn.nextLine();

        // Convert the input string to a Java List
        List<Integer> nums = new ArrayList<>();
        input_str = input_str.substring(1, input_str.length() - 1);
        String[] parts = input_str.split(",");
        for (String part : parts)
            nums.add(Integer.parseInt(part.trim()));

        // Create an instance of the Solution class
        Solution solution = new Solution();
        
        // Call the minimumCost method and store the result
        int cost = solution.minimumCost(nums);
        
        // Print the result
        System.out.println(cost);
    }
}
```
Please note that mapToInt() and sum() are some of the methods provided by Java 8 Stream API which are used to transform and perform aggregate operations on collections. The `equals()` method is used to compare strings, while `new StringBuilder(t).reverse().toString()` is used to reverse a string. The `substring()` method is used to cut the first and last parentheses from the input string. The `trim()` method is used to remove any leading and trailing spaces from the string.

I had to convert this to integer rather than keeping List<int> as Java doesn't support primitives in generic types (like List<int> instead it supports List<Integer>).