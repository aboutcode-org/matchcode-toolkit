Here is a Java version that performs the same operations as the given Python code:

```java
import java.util.*;

class Solution {
    static int minimumCost(int[] nums) {
        
        Arrays.sort(nums);
        int n = nums.length;

        class Palindrome{
            boolean check(int n) {
                String t = Integer.toString(n);
                return t.equals(new StringBuffer(t).reverse().toString());
            }
        }

        class Check{
            int calculate(int n) {
                Palindrome palindrome = new Palindrome();
                int res = 0;

                for(int a = n; a > 0; a--) {
                    if(palindrome.check(a)) {
                        res = Arrays.stream(nums).map(it -> Math.abs(it - a)).sum();
                        break;
                    }
                }

                for(int b = n; b < Math.pow(10, 9); b++) {
                    if(palindrome.check(b)) {
                        res = Math.min(res, Arrays.stream(nums).map(it -> Math.abs(it - b)).sum());
                        break;
                    }
                }
                
                return res;
            }
        }

        Check check = new Check();
        return Math.min(check.calculate(nums[n/2]), check.calculate(nums[n/2-1]));
    }

    public static void main(String[] args) {
        // Read the input string
        Scanner scanner = new Scanner(System.in);
        String input_str = scanner.nextLine();
        scanner.close();
        
        // Convert the input string to a Java List
        int[] nums = Arrays.stream(input_str.replaceAll("\\[|\\]|\\s", "").split(",")).mapToInt(Integer::parseInt).toArray();

        // Call the minimumCost method and store the result
        int cost = minimumCost(nums);
        
        // Print the result
        System.out.println(cost);
    }
}
```

Please note the usage of inline classes in the Java version to replicate the nested function definitions in Python.