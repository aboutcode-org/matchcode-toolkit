Below is a Java semantic code clone of the provided Python code:

```java
import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n / 2], nums), check(nums[n / 2 - 1], nums));
    }

    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;

        for (int a = n; a > 0; a--) {
            if (isPalindrome(a)) {
                res = sumOfDifferences(a, nums);
                break;
            }
        }

        for (int b = n; b < Math.pow(10, 9); b++) {
            if (isPalindrome(b)) {
                res = Math.min(res, sumOfDifferences(b, nums));
                break;
            }
        }

        return res;
    }

    private boolean isPalindrome(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    private int sumOfDifferences(int a, int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - a);
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int[] nums = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}
```
Please note that this Java code does not exactly mirror the Python code because of the differences in the language features. The Python `input()` function is replaced with `Scanner.nextLine()` in Java. The Python `ast.literal_eval()` function is replaced with `Arrays.stream().mapToInt().toArray()` in Java. The Python `for` loop with a negative step is replaced with a regular `for` loop and a `break` statement in Java. The Python `sum()` function is replaced with a custom `sumOfDifferences()` method in Java. The Python `min()` function is replaced with `Math.min()` in Java.