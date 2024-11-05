```java
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        boolean pal(int num) {
            String t = String.valueOf(num);
            return t.equals(new StringBuilder(t).reverse().toString());
        }

        int check(int num) {
            int res = Integer.MAX_VALUE;
            for (int a = num; a > 0; a--) {
                if (pal(a)) {
                    int sum = 0;
                    for (int it : nums) {
                        sum += Math.abs(it - a);
                    }
                    res = sum;
                    break;
                }
            }
            for (int b = num; b < Math.pow(10, 9); b++) {
                if (pal(b)) {
                    int sum = 0;
                    for (int it : nums) {
                        sum += Math.abs(it - b);
                    }
                    res = Math.min(res, sum);
                    break;
                }
            }
            return res;
        }

        return Math.min(check(nums[n / 2]), check(nums[n / 2 - 1]));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        String[] inputArr = inputStr.substring(1, inputStr.length() - 1).split(", ");
        int[] nums = Arrays.stream(inputArr).mapToInt(Integer::parseInt).toArray();

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}
```