Here is the Java equivalent semantic clone of the provided Python code:

```java
import java.util.*;

public class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n / 2], nums), check(nums[n / 2 - 1], nums));
    }

    public boolean pal(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    public int check(int n, int[] nums) {
        int res = 0;
        for (int a = n; a > 0; a--) {
            if (pal(a)) {
                res = calculateSum(a, nums);
                break;
            }
        }

        for (int b = n; b < 10e9; b++) {
            if (pal(b)) {
                res = Math.min(res, calculateSum(b, nums));
                break;
            }
        }
        return res;
    }

    public int calculateSum(int x, int[] nums) {
        int res = 0;
        for (int num : nums) {
            res += Math.abs(num - x);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] nums = new int[size];
        for(int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

Please note that in this Java code, I've replaced the Python `ast.literal_eval` input method with a more traditional reading method, where the user first enters the size of the array, and then the elements.