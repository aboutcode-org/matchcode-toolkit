Below is a Java equivalent of the Python code:

```java
import java.util.*;

public class Solution {
    
    private boolean isPal(int n) {
        String t = String.valueOf(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }
    
    private int check(int n, List<Integer> nums) {
        int res = 0;
        for (int a = n; a >= 1; a--) {
            if (isPal(a)) {
                res = nums.stream().mapToInt(it -> Math.abs(it - a)).sum();
                break;
            }
        }     
        for (int b = n; b <= Math.pow(10,9); b++) {
            if (isPal(b)) {
                res = Math.min(res, nums.stream().mapToInt(it -> Math.abs(it - b)).sum());         
                break;
            }
        }
        return res;
    }
    
    public int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();
        return Math.min(check(nums.get(n / 2), nums), check(nums.get(n / 2 - 1), nums));
    }
  
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        // Read the input string
        String inputStr = scanner.nextLine();
      
        // Convert the input string to a Java list
        List<Integer> nums = new ArrayList<>();
        for (String num : inputStr.replace("[","").replace("]","").split(", ")) {
            nums.add(Integer.parseInt(num));
        }
      
        // Create an instance of the Solution class
        Solution solution = new Solution();
      
        // Call the minimumCost method and store the result
        int cost = solution.minimumCost(nums);
      
        // Print the result
        System.out.println(cost);
    }
}
```

Please note that in Java, getting the middle value of list `nums` is a bit different than Python but the logic still remains the same. In python you can just use `nums[n//2]` to get the middle value but in Java you have to use `nums.get(n / 2)` because there's no operator `//` in Java. Here `/` will do integer division so no need to worry. Also, the backward string comparison for palindrome is performed using StringBuilder's reverse method in Java, as python style slicing won’t work in Java.