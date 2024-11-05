Below is a Java version of your Python code. Some differences to note are the for loop increment/decrement placeholders, the list/array conversion, and the function calling parameters.

```java
import java.util.*;

public class Solution {
    public int minimumCost(int[] nums) {
        
        Arrays.sort(nums);
        int n = nums.length;

        int check(int n){
            int res = Integer.MAX_VALUE;
            for (int a = n; a > 0; a--){
                if (isPal(a)){
                    res = sumDiff(nums, a);
                    break;
                }   
            }
            for (int b = n; b < Integer.MAX_VALUE; b++){
                if (isPal(b)){
                    res = Math.min(res, sumDiff(nums, b));
                    break;
                }   
            }
            return res;
        }

        boolean isPal(int num){
            String number = Integer.toString(num);
            String reversed = new StringBuilder(number).reverse().toString();
            return number.equals(reversed);
        }

        int sumDiff(int[] nums, int num){
            int total = 0;
            for (int i : nums){
                total += Math.abs(i - num);
            }
            return total;
        }

        return Math.min(check(nums[n/2]), check(nums[n/2 - 1]));
    }

    public static void main(String[] args){
        // Read the input string
        Scanner scanner = new Scanner(System.in);
        String numsStr = scanner.nextLine();
        scanner.close();
        
        // Convert the input string to a Java list
        int[] nums = Arrays.stream(numsStr.substring(1, numsStr.length()-1).split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}
```
Please note that the maximum value for an integer in Java is capped at `Integer.MAX_VALUE` which is equivalent to 2147483647. This is smaller than 10^9 used in your Python code. So this value was used instead. The same caveat is applicable to the for loop as well. Using a value larger than `Integer.MAX_VALUE` will result in an overflow.