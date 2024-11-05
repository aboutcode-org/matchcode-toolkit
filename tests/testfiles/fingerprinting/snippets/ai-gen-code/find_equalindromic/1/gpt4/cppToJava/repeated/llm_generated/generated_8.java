The Java version will be different due to the Java syntax and the Java libraries available. Here is a semantic code clone in Java:

```java
import java.util.*;
import java.math.BigInteger;

public class Solution {
    public BigInteger minimumCost(List<BigInteger> nums) {
        BigInteger ans = BigInteger.valueOf(Long.MAX_VALUE), median = BigInteger.ZERO;
        int n = nums.size();
        Collections.sort(nums);
        median = (n %2 != 0)? nums.get(n/2): (nums.get(n/2).add(nums.get(n/2 - 1))).divide(BigInteger.valueOf(2));
        
        //Find out possible palindroms
        List<BigInteger> pal = new ArrayList<>();
        String t = median.toString();
        pal.add(BigInteger.TEN.pow(t.length()-1).subtract(BigInteger.ONE));
        pal.add(BigInteger.TEN.pow(t.length()+1));

        for(int i = 0; i < t.length()/2; ++i) {
            t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i, t.length());
        }
        pal.add(new BigInteger(t));
        
        String pal_next = new BigInteger(t.substring(0,(t.length()+1)/2)).add(BigInteger.ONE).toString();
        String temp = pal_next;
        if(t.length()%2!=0) pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal_next = new StringBuilder(pal_next).reverse().toString();
        pal.add(new BigInteger(temp + pal_next));
        
        String pal_prev = new BigInteger(t.substring(0,(t.length()+1)/2)).subtract(BigInteger.ONE).toString();
        temp = pal_prev;
        if(t.length()%2!=0) pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal_prev = new StringBuilder(pal_prev).reverse().toString();
        pal.add(new BigInteger(temp + pal_prev));
        
        for(BigInteger p: pal){
            BigInteger sum = BigInteger.ZERO;
            for(BigInteger num: nums) sum = sum.add(num.subtract(p).abs());
            ans = ans.min(sum);
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String line = reader.nextLine();
        line = line.substring(1, line.length() - 2);
        
        List<BigInteger> nums = new ArrayList<>();
        for (String item : line.split(",")) {
            nums.add(new BigInteger(item.trim()));
        }
        
        Solution solution = new Solution();
        BigInteger cost = solution.minimumCost(nums);
        
        System.out.println(cost);
    }
}
```

As Java doesn't support unsigned int and long long, BigInteger has been used for large number operation. This code essentially does the same thing as the C++ code, but there are differences due to the different programming languages.