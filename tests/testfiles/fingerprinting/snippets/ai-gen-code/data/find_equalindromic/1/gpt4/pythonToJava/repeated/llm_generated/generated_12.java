Below is the Java semantic code clone of the given Python code:

```java
import java.util.*;

public class Solution {
    private int minimumCost(List<Integer> nums) {
        
        Collections.sort(nums);
        int n = nums.size();

        int check(int n) {
            int res = Integer.MAX_VALUE;
            for(int a=n; a>=0; a--){
                if(isPalindrome(a)){
                    res = sumDifference(a, nums);
                    break;
                }
            }
            for(int b=n; b <= Math.pow(10, 9); b++){
                if(isPalindrome(b)){
                    res = Math.min(res, sumDifference(b, nums));
                    break;
                }               
            }
            return res;
        }

        boolean isPalindrome(int n){
            String s = Integer.toString(n);
            return s.equals(new StringBuilder(s).reverse().toString());
        }
        
        int sumDifference(int m, List<Integer> nums){
            int sum = 0;
            for(Integer num : nums){
                sum += Math.abs(num - m);
            }
            return sum;
        }

        return Math.min(check(nums.get(n/2)), check(nums.get(n/2-1)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        List<Integer> nums = new ArrayList<>();
        int n = scanner.nextInt();
        for(int i=0; i<n; i++){
            nums.add(scanner.nextInt());
        }

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```
It has the same semantics as the Python code but in Java language. It reads the integers from the standard input, uses the minimumCost method to compute the minimum cost, and finally prints the result back to the standard output.