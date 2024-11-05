Below is the Java semantic code clone of the provided Python code:

```java
import java.util.Arrays;
import java.util.Scanner;

class Solution {
    private static boolean isPalindrome(int n) {
        String str = Integer.toString(n);
        int i = 0, j = str.length() - 1;
        while(i < j) {
            if(str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private static int calculateCost(int[] nums, int n) {
        int res = Integer.MAX_VALUE;
        for(int a = n; a > 0; a--) {
            if(isPalindrome(a)) {
                res = Arrays.stream(nums).map(it -> Math.abs(it - a)).sum();
                break;
            }
        }
        for(int b = n; b < Math.pow(10, 9); b++) {
            if(isPalindrome(b)) {
                res = Math.min(res, Arrays.stream(nums).map(it -> Math.abs(it - b)).sum());
                break;
            }
        }
        return res;
    }

    public static int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.min(calculateCost(nums, nums[n/2]), calculateCost(nums, nums[n/2 - 1]));
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();
        String[] strArr = inputStr.split(",");
        int[] nums = new int[strArr.length];
        for(int i = 0; i < strArr.length; i++) {
            nums[i] = Integer.parseInt(strArr[i]);
        }
        int cost = Solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

In this Java code, the `Solution` class contains the `minimumCost` method which calculates the minimum cost to make the array elements palindromic. The `isPalindrome` method checks if a number is palindrome or not. The `calculateCost` method calculates the cost for a given number. The `main` method reads the input, parses it to an integer array and calls the `minimumCost` method to get the result.