Below is the Java semantic clone of the given Python code:

```java
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n / 2], nums), check(nums[n / 2 - 1], nums));
    }

    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (isPalindrome(a)) {
                res = sumDiff(a, nums);
                break;
            }
        }
        for (int b = n; b < Math.pow(10, 9); b++) {
            if (isPalindrome(b)) {
                res = Math.min(res, sumDiff(b, nums));
                break;
            }
        }
        return res;
    }

    private int sumDiff(int a, int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - a);
        }
        return sum;
    }

    private boolean isPalindrome(int n) {
        String str = Integer.toString(n);
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(",");
        int[] nums = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i].trim());
        }
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

This Java code does the same thing as the Python code. It sorts the array, checks for the minimum cost by checking the palindrome of the middle elements and their neighbors, and finally prints out the minimum cost. The main difference is the way of handling input and output, Java uses a Scanner to read from standard input and System.out.println() to output, while Python uses the input() and print() functions. Also, Java does not have a built-in absolute function for integers, so we use Math.abs() instead.