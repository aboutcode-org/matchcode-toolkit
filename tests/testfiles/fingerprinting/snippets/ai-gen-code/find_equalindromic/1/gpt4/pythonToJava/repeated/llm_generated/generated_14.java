```Java
import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
    public int minimumCost(int[] nums) {
        
        Arrays.sort(nums);
        int n = nums.length;

        class Inner {
            private boolean pal(int n) {
                String t = Integer.toString(n);
                return t.equals(new StringBuilder(t).reverse().toString());
            }
            
            public int check(int n) {
                int res = Integer.MAX_VALUE;
                for (int a = n; a > 0; a--) {
                    if (pal(a)) {
                        res = IntStream.of(nums).map(it -> Math.abs(it - a)).sum();
                        break;
                    }
                }
                for (int b = n; b < Math.pow(10,9); b++) {
                    if (pal(b)) {
                        res = Math.min(res, IntStream.of(nums).map(it -> Math.abs(it - b)).sum());
                        break;
                    }
                }
                return res;
            }
        }

        Inner inner = new Inner();
        return Math.min(inner.check(nums[n/2]), inner.check(nums[n/2 - 1]));
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};  // Array from the input
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```