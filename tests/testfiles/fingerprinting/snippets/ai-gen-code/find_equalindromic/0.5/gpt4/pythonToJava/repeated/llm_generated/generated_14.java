Here is the Java semantic code clone of the Python code:

```java
import java.util.*;

public class Solution {
    private static boolean isPalindrome(int n) {
        String str = Integer.toString(n);
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    private static int check(int n, List<Integer> nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (isPalindrome(a)) {
                res = nums.stream().mapToInt(num -> Math.abs(num - a)).sum();
                break;
            }
        }
        for (int b = n; b < Math.pow(10, 9); b++) {
            if (isPalindrome(b)) {
                res = Math.min(res, nums.stream().mapToInt(num -> Math.abs(num - b)).sum());
                break;
            }
        }
        return res;
    }

    public static int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();
        return Math.min(check(nums.get(n / 2), nums), check(nums.get(n / 2 - 1), nums));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        List<Integer> nums = new ArrayList<>();
        for (String str : inputStr.split(",")) {
            nums.add(Integer.parseInt(str.trim()));
        }
        System.out.println(minimumCost(nums));
    }
}
```
This Java code does the same thing as the Python code. It first sorts the input list of numbers, then it checks for the minimum cost by comparing the sums of the absolute differences between each number and the closest palindrome number. The main difference is the way the palindrome check and the absolute difference sum are implemented, due to the differences between Python and Java syntax and standard libraries.