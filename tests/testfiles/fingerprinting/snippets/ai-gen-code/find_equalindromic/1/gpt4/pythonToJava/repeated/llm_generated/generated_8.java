Below is a Java semantic code clone of the provided Python code.

```java
import java.util.*;
import java.lang.*;

class Solution {
    
    static int minimumCost(int [] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(Check(n, nums[n/2]), Check(n, nums[n/2 - 1]));
    }

    private static int Check(int n, int m) {
        int result = Integer.MAX_VALUE;
        for (int a = m; a > 0; a--) {
            if (IsPalindrome(a)) {
                result = CalculateSum(n, a, nums);
                break;
            }
        }

        for (int b = m; b < 10^9; b++) {
            if (IsPalindrome(b)) {
                result = Math.min(result, CalculateSum(n, b, nums));
                break;
            }
        }

        return result;
    }

    private static int CalculateSum(int n, int a, int[] nums) {
        int sum = 0;
        for (int it : nums) {
            sum += Math.abs(it - a);
        }
        return sum;
    }

    private static boolean IsPalindrome(int n) {
        String s = Integer.toString(n);
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++; j--;
        }
        return true;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(",");
        int[] nums = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
        scanner.close();
    }
}
```

Please note that I've written a separate function for calculating sum and palindrome check to keep the code clean and readable, which is generally a good practice in any programming language. Also note that the `check` method in python is translated as `Check` method in Java and same for the `pal` method which is translated as `IsPalindrome` in Java. Given the nature of Java, additional helper methods such as `CalculateSum` and `IsPalindrome` were needed which were part of `check` method in Python code.