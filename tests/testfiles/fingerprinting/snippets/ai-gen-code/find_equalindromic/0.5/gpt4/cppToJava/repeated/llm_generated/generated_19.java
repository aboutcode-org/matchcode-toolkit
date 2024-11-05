Here is the Java semantic code clone for the above C++ code:

```java
import java.util.*;

public class Solution {
    public long minimumCost(List<Integer> nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.size();
        Collections.sort(nums);
        median = (n %2 != 0)?nums.get(n/2): (nums.get(n/2) + nums.get(n/2 - 1))/2;
        //Find out possible palindroms
        List<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long)Math.pow(10, t.length()-1) - 1); //case 1 : next palindrom with one digit less
        pal.add((long)Math.pow(10, t.length()) + 1);   //case 2: next palindrom with one digit more
        //case 3: palidrom with just mirror image of the left half
        for(int i = 0; i < t.length()/2; ++i) 
            t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
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
            for(Integer num: nums) sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine(); // Read the input line

        // Remove the square brackets
        line = line.substring(1, line.length() - 2);

        List<Integer> nums = new ArrayList<>();
        String[] items = line.split(",");

        // Parse the integers from the string
        for (String item : items) {
            nums.add(Integer.parseInt(item.trim()));
        }

        // Instantiate the Solution class and call the minimumCost method
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        // Print the result
        System.out.println(cost);
    }
}
```
Please note that Java does not support changing the value of a string character directly as in C++. So, to change the value of a character in a string, we have to create a new string with the changed character.