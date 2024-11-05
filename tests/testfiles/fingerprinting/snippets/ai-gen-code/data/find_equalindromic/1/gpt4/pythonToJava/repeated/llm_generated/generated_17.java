Below is a Java semantic clone for the provided Python code:

```java
import java.util.*;

class Solution {
    public int minimumCost(int[] nums) {
        
        Arrays.sort(nums);
        int n = nums.length;

        int check(int n) {
            int res = 0;
            for (int a = n; a > 0; a--) {
                if (isPalindrome(a)) {
                    res = sumDifference(nums, a);
                    break;
                }
            }
            for (int b = n; b <= (int)Math.pow(10, 9); b++) {
                if (isPalindrome(b)) {
                    res = Math.min(res, sumDifference(nums, b));
                    break;
                }
            }
            return res;
        }

        return Math.min(check(nums[n/2]), check(nums[n/2-1]));
    }

    private boolean isPalindrome(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    private int sumDifference(int[] nums, int a) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - a);
        }
        return sum;
    }
}

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String inputStr = scanner.nextLine();
    int[] nums = Arrays.stream(inputStr.split(","))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();

    Solution solution = new Solution();
    int cost = solution.minimumCost(nums);
    System.out.println(cost);
}
```
This Java code maintains the semantic behaviour of the original Python code, handling input in the same way and implementing a very similar logic to reach the final result. Therefore, it can be considered as a Java clone of the Python code. Please note that the exact interpretation of the inputs differs between languages, you might need to adjust the input-taking code as per your needs. For this example, the input string is assumed to be comma delimited integers.