Here is a Java code that semantically clones the Python code:

```java
import java.util.*;

public class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n/2], nums), check(nums[n/2 - 1], nums));
    }

    private boolean isPal(int n) {
        String str = Integer.toString(n);
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    private int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (isPal(a)) {
                res = sumDiff(a, nums);
                break;
            }
        }
        for (int b = n; b < Math.pow(10, 9); b++) {
            if (isPal(b)) {
                res = Math.min(res, sumDiff(b, nums));
                break;
            }
        }
        return res;
    }

    private int sumDiff(int num, int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += Math.abs(n - num);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strNums = scanner.nextLine().split(",");
        int[] nums = new int[strNums.length];
        for (int i = 0; i < strNums.length; i++) {
            nums[i] = Integer.parseInt(strNums[i].trim());
        }
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```
This Java code works the same way as the Python code. It sorts the input array, then checks for the minimum cost by calculating the sum of the absolute differences between each number in the array and a palindrome number. The palindrome number is found by starting from the median of the sorted array and going both downwards and upwards until a palindrome is found. The minimum cost is then returned. The main method reads an input string, converts it to an array of integers, and calls the minimumCost method. The result is printed to the console.