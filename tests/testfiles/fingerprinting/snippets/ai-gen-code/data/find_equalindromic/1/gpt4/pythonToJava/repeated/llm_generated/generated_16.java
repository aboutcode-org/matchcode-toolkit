Below is the Java semantic code clone for the above Python code. It performs the same functionality:

```
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public int minimumCost(int[] nums) {

        Arrays.sort(nums);
        int n = nums.length;

        return Math.min(check(nums[n/2], nums), check(nums[n/2-1], nums));
    }

    private boolean pal(int n) {
        String t = Integer.toString(n);
        return new StringBuilder(t).reverse().toString().equals(t);
    }

    private int check(int n, int[] nums) {
        int res = 0;
        for (int a = n; a > 0; a--) {
            if (pal(a)) {
                res = Arrays.stream(nums).map(it -> Math.abs(it - a)).sum();
                break;
            }
        }

        for (int b = n; b < 1000000000; b++) {
            if (pal(b)) {
                res = Math.min(res, Arrays.stream(nums).map(it -> Math.abs(it - b)).sum());
                break;
            }
        }

        return res;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        
        // Reading input
        String[] numStringArray = in.nextLine().split(",");
        int[] nums = new int[numStringArray.length];
        for (int i = 0; i < numStringArray.length; i++) {
            nums[i] = Integer.parseInt(numStringArray[i]);
        }

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);

        in.close();
    }
}
```