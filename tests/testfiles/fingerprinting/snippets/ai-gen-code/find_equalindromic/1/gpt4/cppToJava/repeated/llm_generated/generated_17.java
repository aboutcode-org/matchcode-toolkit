Here is a semantic clone of the given C++ code in Java.

```java
import java.util.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        String[] items = input.substring(1, input.length() - 1).split(",");
        List<Integer> nums = new ArrayList<>();
        for (String item : items){
            nums.add(Integer.parseInt(item.trim()));
        }
        long cost = minimumCost(nums);
        System.out.println(cost);
    }

    public static long minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        long ans = Long.MAX_VALUE;
        int median = nums.get(nums.size() / 2);
        long pow10Size1 = (long) Math.pow(10, String.valueOf(median).length()) - 1;
        long pow10Size2 = (long) Math.pow(10, String.valueOf(median).length() + 1) + 1;

        List<Long> pals = new ArrayList<>();
        pals.add(pow10Size1);
        pals.add(pow10Size2);
        pals.add(createPalindrome(new BigInteger(String.valueOf(median)), false));
        pals.add(createPalindrome(new BigInteger(String.valueOf(median)), false).add(BigInteger.ONE));
        pals.add(createPalindrome(new BigInteger(String.valueOf(median)), false).subtract(BigInteger.ONE));

        for (Long p : pals) {
            long sum = 0;
            for (Integer num : nums) {
                sum += Math.abs(num - p);
            }
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static long createPalindrome(BigInteger input, boolean isOddLength) {
        String number = String.valueOf(input);
        StringBuilder s = new StringBuilder(number);
        if (isOddLength) {
            s = new StringBuilder(number.substring(0, number.length() - 1));
        }
        String rev = s.reverse(). toString();
        return Long.parseLong(number + rev);
    }
}
```

This Java code does not support operations on large numbers, which can be handled in C++ using `stoll`. If you need to handle larger numbers, you can use `BigInteger` instead of `long`.