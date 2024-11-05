Below is the Java semantic code clone of the given Python code:

```Java
import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n / 2], nums), check(nums[n / 2 - 1], nums));
    }

    private boolean isPalindrome(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;

        for (int a = n; a > 0; a--) {
            if (isPalindrome(a)) {
                res = sumOfDifferences(nums, a);
                break;
            }
        }

        for (int b = n; b < 1_000_000_000; b++) {
            if (isPalindrome(b)) {
                res = Math.min(res, sumOfDifferences(nums, b));
                break;
            }
        }

        return res;
    }

    private int sumOfDifferences(int[] nums, int n) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - n);
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String[] numsStr = input.split(" ");
        int[] nums = new int[numsStr.length];

        for (int i = 0; i < numsStr.length; i++) {
            nums[i] = Integer.parseInt(numsStr[i]);
        }

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}
```

This Java code does the same thing as the Python code. It reads an input string, converts it to an array, and then calculates the minimum cost by checking the sum of the absolute differences between each element and the closest palindrome number. It does this for both the middle number and the number before the middle number (in the sorted array), and returns the minimum of these two costs.