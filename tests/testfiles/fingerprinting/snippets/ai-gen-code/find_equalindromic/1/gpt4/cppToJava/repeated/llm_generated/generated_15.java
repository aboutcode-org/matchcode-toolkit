Below is a semantic code clone of the provided C++ code written in Java:

```java
import java.math.BigInteger;
import java.util.*;
import java.lang.Math;

public class Main {
    static long minimumCost(long[] nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.length;
        Arrays.sort(nums);
        median = (n % 2) != 0 ? nums[n / 2] : (nums[n / 2] + nums[n / 2 - 1]) / 2;

        List<BigInteger> pal = new ArrayList<>();

        String t = String.valueOf(median);
        pal.add(BigInteger.valueOf((long)Math.pow(10, t.length() - 1)).subtract(BigInteger.ONE)); 
        pal.add(BigInteger.valueOf((long)Math.pow(10, t.length())).add(BigInteger.ONE));

        StringBuffer sb = new StringBuffer(t);
        String reversedHalf = sb.reverse().toString();

        for(int i = 0; i < t.length() / 2; i++)
            t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);
        pal.add(new BigInteger(t));

        String pal_next = Integer.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if(t.length() % 2 != 0)
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        sb = new StringBuffer(pal_next);
        pal_next = sb.reverse().toString();
        pal.add(new BigInteger(temp + pal_next));

        String pal_prev = Integer.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if(t.length() % 2 != 0)
             pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        sb = new StringBuffer(pal_prev);
        pal_prev = sb.reverse().toString();
        pal.add(new BigInteger(temp + pal_prev));

        for(BigInteger p: pal) {
            long sum = 0;
            for(long num: nums) sum += Math.abs(num - p.longValue());
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] line = input.nextLine().replace("[", "").replace("]", "").split(",");
        long[] nums = new long[line.length];
        for(int i = 0; i < line.length; i++)
            nums[i] = Long.parseLong(line[i]);
        System.out.println(minimumCost(nums));
    }
}
```
Given that Java does not support the same String manipulation functions as C++, a StringBuffer was used to replace the reverse method on the string. Furthermore, features such as auto are also not present in Java, so concrete types have been used instead. Finally, Java does not have equivalent functions to stoll and stoi, so equivalents from the available Java standard library were used.