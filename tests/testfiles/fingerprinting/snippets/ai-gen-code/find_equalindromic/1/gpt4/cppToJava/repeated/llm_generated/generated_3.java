Below is a semantic clone of the provided C++ code, written in Java.

```java
import java.util.*;
import java.math.BigInteger;
 
public class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE;
        int median = 0;
        int n = nums.length;
        Arrays.sort(nums);
        median = (n % 2 != 0) ? nums[n/2] : (nums[n/2] + nums[n/2 - 1])/2;
        ArrayList<Long> pal = new ArrayList<>();
        String t = Integer.toString(median);
        pal.add((long)Math.pow(10, t.length()-1) - 1);
        pal.add((long)Math.pow(10, t.length()) + 1);
        for(int i = 0; i < t.length()/2; ++i) {
            char[] arr = t.toCharArray();
            arr[t.length() - i - 1] = arr[i];
            t = String.valueOf(arr);
        }
        pal.add(Long.parseLong(t));
        StringBuilder palNext = new StringBuilder(Integer.toString(Integer.parseInt(t.substring(0,(t.length()+1)/2)) + 1));
        String temp = palNext.toString();
        if(t.length()%2!=0){
            palNext = new StringBuilder(palNext.substring(0, palNext.length() - 1));
        }
        palNext.reverse();
        pal.add(Long.parseLong(temp + palNext));
        StringBuilder palPrev = new StringBuilder(Integer.toString(Integer.parseInt(t.substring(0,(t.length()+1)/2)) - 1));
        temp = palPrev.toString();
        if(t.length()%2!=0){
            palPrev = new StringBuilder(palPrev.substring(0, palPrev.length() - 1));
        }
        palPrev.reverse();
        pal.add(Long.parseLong(temp + palPrev));
        for(long p: pal){
            long sum = 0;
            for(int num: nums){
                sum += Math.abs(num - p);
            }
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.substring(1, input.length()-1);
        String[] elems = input.split(",");
        int[] nums = new int[elems.length];
        for(int i=0; i<nums.length; i++){
            nums[i] = Integer.parseInt(elems[i]);
        }
        Solution sol = new Solution();
        long cost = sol.minimumCost(nums);
        System.out.println(cost);
    }
}
```

Please note that the Java version of the code might be slightly different due to the differences between C++ and Java runtime and type handling.