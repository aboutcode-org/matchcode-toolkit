Here is the Java version of the given Python code:

```java
import java.util.*;
import java.math.*;

public class Solution {
    public int minimumCost(Integer nums[]) {
        
        Arrays.sort(nums);
        int n = nums.length;

        class Helper {
            private boolean isPalindrome(Integer num) {
                String str = num.toString();
                return str.equals(new StringBuilder(str).reverse().toString());
            }
            
            public int check(int num) {
                int res = 0;
                for(int a = num; a > 0; a--) {
                    if(isPalindrome(a)) {
                        res = sumAbsDiff(nums, a);
                        break;
                    }
                }
                for(int b = num; b < Math.pow(10, 9); b++) {
                    if(isPalindrome(b)){
                        res = Math.min(res, sumAbsDiff(nums, b));
                        break;
                    }
                }
                return res;
            }
            
            private int sumAbsDiff(Integer[] array, int number) {
                int sum = 0;
                for(int item: array) {
                    sum += Math.abs(item - number);
                }
                return sum;
            }
        }

        Helper helper = new Helper();
        return Math.min(helper.check(nums[n / 2]), helper.check(nums[n / 2 - 1]));
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String input = scanner.nextLine();
        
        String inputArray[] = input.split(", ");
        Integer nums[] = new Integer[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            nums[i] = Integer.parseInt(inputArray[i]);
        }

        Solution solution = new Solution();

        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```

In the `main` function, the Python `input()` function is replaced by a Scanner in Java, which reads the input as a string. The string is then split by ", " to an array of Strings, and each String item is parsed into an Integer array. The rest of the logic, calculations and outputs are the same as in the Python code.