Here is the Java semantic code clone of the provided Python code.

```java
import java.util.*;

public class Solution {
    public static int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n / 2], nums), check(nums[n / 2 - 1], nums));
    }

    public static int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (isPal(a)) {
                res = sumAbsDiff(a, nums);
                break;
            }
        }
        for (int b = n; b < Math.pow(10, 9); b++) {
            if (isPal(b)) {
                res = Math.min(res, sumAbsDiff(b, nums));
                break;
            }
        }
        return res;
    }

    public static boolean isPal(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    public static int sumAbsDiff(int n, int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - n);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();
        String[] strNums = inputStr.split(",");
        int[] nums = new int[strNums.length];
        for (int i = 0; i < strNums.length; i++) {
            nums[i] = Integer.parseInt(strNums[i]);
        }
        System.out.println(minimumCost(nums));
    }
}
```

This Java code does the same thing as the Python code. It reads the input string and converts it to an array of integers. Then it calls the `minimumCost` method and prints the result. The `minimumCost` method sorts the array and calls the `check` method for two middle elements of the sorted array. The `check` method finds the closest palindrome number to the given number and calculates the sum of the absolute differences between this palindrome number and the elements of the array. The `isPal` method checks if the given number is a palindrome. The `sumAbsDiff` method calculates the sum of the absolute differences between the given number and the elements of the array.