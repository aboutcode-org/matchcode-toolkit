Here is the Java version of the given C++ code:

```java
import java.util.*;

public class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE, median = 0, n = nums.length;
        Arrays.sort(nums);
        median = (n %2 != 0) ? nums[(int)n/2] : (nums[(int)n/2] + nums[(int)n/2 - 1])/2;
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
        for(Long p: pal){
        long sum = 0;
        for(int num: nums) sum += Math.abs(num - p);
        ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        // Remove the square brackets
        line = line.substring(1, line.length() - 2);

        String[] items = line.split(",");
        int[] nums = new int[items.length];

        // Parse the integers from the string
        for (int i = 0; i < items.length; i++) {
            nums[i] = Integer.parseInt(items[i].trim());
        }

        // Instantiate the Solution class and call the minimumCost method
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        // Print the result
        System.out.println(cost);
    }
}
```
This Java code performs the same functionality as the given C++ code. It reads a line of input, parses it into an array of integers, and then calls the `minimumCost` method to calculate and print the minimum cost. The `minimumCost` function calculates the possible palindromes and finds the cost with each palindrome, taking the minimum as the answer.