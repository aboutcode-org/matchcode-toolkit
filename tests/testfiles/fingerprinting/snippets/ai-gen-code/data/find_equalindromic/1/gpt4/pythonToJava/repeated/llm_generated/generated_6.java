Below is the Java version of the given Python code:

```java
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

    private static boolean isPalindrome(int n) {
        String str = Integer.toString(n);
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    private static int check(int n, List<Integer> nums) {
        Integer result = null;
        for (int i = n; i > 0; i--) {
            if (isPalindrome(i)) {
                result = nums.stream().reduce(0, (a, b) -> a + Math.abs(b - i));
                break;
            }
        }
        for (int i = n; i < 1000000000; i++) {
            if (isPalindrome(i)) {
                result = Math.min(result, nums.stream().reduce(0, (a, b) -> a + Math.abs(b - i)));
                break;
            }
        }
        return result;
    }

    public static int minimumCost(List<Integer> nums) {
        nums.sort(Integer::compare);
        int n = nums.size();
        return Math.min(check(nums.get(n/2), nums), check(nums.get(n/2 - 1), nums));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();
        List<Integer> nums = Arrays.stream(inputStr.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println(minimumCost(nums));
        sc.close();
    }
}
```

Few points to note in the translation:

1. The 'range' function in Python is translated into a for loop in Java.
2. List slicing in Python is converted into 'stream' processing in Java.

I hope you find this helpful! Feel free to ask for any further clarification in case needed.