Below is the semantically identical code for the given python code written in Java:

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        scanner.close();
        String[] arr = inputStr.replaceAll("[^\\d.]", "").split("");
        int[] nums = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = Integer.parseInt(arr[i]);
        }
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}

class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.min(check(nums[n / 2], nums), check(nums[n / 2 - 1], nums));
    }

    private static boolean pal(int n) {
        String t = Integer.toString(n);
        StringBuilder str = new StringBuilder(t);
        return t.equals(str.reverse().toString());
    }

    private static int check(int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (pal(a)) {
                res = Arrays.stream(nums).map(it -> Math.abs(it - a)).sum();
                break;
            }
        }
        for (int b = n; b < Math.pow(10, 9); b++) {
            if (pal(b)) {
                res = Math.min(res, Arrays.stream(nums).map(it -> Math.abs(it - b)).sum());
                break;
            }
        }
        return res;
    }
}
``` 

In Python, list comprehensions and slicing are common. But, these features are not there in Java. So, Streams API and StringBuilder are used to achieve the same.

Please note that you may need java 8 or above for this code to work because as per the code above, 'Stream' is used which included in java 8.