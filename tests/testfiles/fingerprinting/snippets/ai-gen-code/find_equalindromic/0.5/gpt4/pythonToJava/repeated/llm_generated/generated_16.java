Here is a Java semantic clone of the Python code:

```java
import java.util.*;
import java.lang.*;

class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.min(check(nums[n/2], nums), check(nums[n/2-1], nums));
    }
    
    private boolean isPalindrome(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }
    
    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        for(int a = n; a > 0; a--) {
            if(isPalindrome(a)) {
                res = getSum(a, nums);
                break;
            }
        }
        for(int b = n; b < 1000000000; b++) {
            if(isPalindrome(b)) {
                res = Math.min(res, getSum(b, nums));
                break;
            }
        }
        return res;
    }
    
    private int getSum(int a, int[] nums) {
        int sum = 0;
        for(int num : nums) {
            sum += Math.abs(num - a);
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.substring(1, input.length() - 1);
        String[] inputArray = input.split(", ");
        int[] nums = new int[inputArray.length];
        for(int i = 0; i < inputArray.length; i++) {
            nums[i] = Integer.parseInt(inputArray[i]);
        }
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

This Java code does the same thing as the Python code. It sorts the input array, checks for palindromes in the array, and then returns the minimum cost. The `main` method reads the input string, converts it to an integer array, and then calls the `minimumCost` method.