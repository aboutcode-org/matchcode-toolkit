Below is the Java version of the given Python code:

```java
import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n / 2], nums), check(nums[n / 2 - 1], nums));
    }

    private boolean isPalindrome(int n) {
        String t = String.valueOf(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;

        for(int a = n; a >= 0; a--) {
            if(isPalindrome(a)) {
                res = getRes(a, nums);
                break;
            }
        }
        for(int b = n; b < 10^9; b++) {
            if(isPalindrome(b)) {
                res = Math.min(res, getRes(b, nums));
                break;
            }
        }

        return res;
    }

    private int getRes(int a, int[] nums) {
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
        String inputStr = scanner.nextLine();

        int[] nums = Arrays.stream(inputStr.replace("[", "").replace("]", "")
                .split(",")).mapToInt(Integer::parseInt).toArray();

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

The Java code reads an input string containing an integer list, transforms it into an integer array, sorts it, and then finds the minimum cost. Here the Java `minimumCost` method performs a similar task as the Python `minimumCost` method.