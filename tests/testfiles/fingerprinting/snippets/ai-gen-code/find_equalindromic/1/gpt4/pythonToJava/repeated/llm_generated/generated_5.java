Below is the Java implementation of the above Python code.

```java
import java.util.*;
import java.util.stream.*;

class Solution {
    public int minimumCost(int[] nums) {
        
        Arrays.sort(nums);
        int n=nums.length;

        // function to check if number is palindrome
        int pal(int n) {            
            String t=Integer.toString(n);
            return t.equals(new StringBuilder(t).reverse().toString()); 
        }
        
        int check(int n){
            int res = Integer.MAX_VALUE;
            for(int a=n; a>=0; a--){
                if(pal(a)){
                    res=IntStream.of(nums).map(e -> Math.abs(e - a)).sum();
                    break;
                }
            }
            for(int b=n; b<=1_000_000_000; b++){
                if(pal(b)){
                    res=Math.min(res, IntStream.of(nums).map(e -> Math.abs(e - b)).sum());
                    break;
                }
            }
            return res;
        }
        return Math.min(check(nums[n/2]), check(nums[n/2 - 1]));
    }

    public static void main(String[] args) {
        
        // Read input from the user
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        int[] nums = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();

        // Create an instance of the Solution class
        Solution solution = new Solution();
        
        // Call the minimumCost method and print the result
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
``` 

This Java code posted above mirrors the functionality of the Python code provided. It reads an array of integers from the user, processes the array with the `minimumCost` method, and finally outputs the result.