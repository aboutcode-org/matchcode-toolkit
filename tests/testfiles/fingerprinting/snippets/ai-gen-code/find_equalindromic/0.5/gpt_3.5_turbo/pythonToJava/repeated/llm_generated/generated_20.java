```java
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minimumCost(List<Integer> nums) {
        
        nums.sort(null);
        int n = nums.size();

        boolean pal(int num) {
            String t = String.valueOf(num);
            return t.equals(new StringBuilder(t).reverse().toString());
        }
        
        int check(int num) {
            int res = 0;
            for (int a = num; a > 0; a--) {
                if (pal(a)) {
                    res = Arrays.stream(nums.stream().mapToInt(it -> Math.abs(it - a)).toArray()).sum();
                    break;
                }
            }
            for (int b = num; b < Math.pow(10, 9); b++) {
                if (pal(b)) {
                    res = Math.min(res, Arrays.stream(nums.stream().mapToInt(it -> Math.abs(it - b)).toArray()).sum());
                    break;
                }
            }
            return res;
        }

        return Math.min(check(nums.get(n / 2)), check(nums.get(n / 2 - 1)));
    }

    public static void main(String[] args) {
        // Read the input string
        Scanner scanner = new Scanner(System.in);
        String input_str = scanner.nextLine();
        
        // Convert the input string to a List of Integers
        List<Integer> nums = Arrays.stream(input_str.split(", ")).map(Integer::parseInt).collect(Collectors.toList());
        
        // Create an instance of the Solution class
        Solution solution = new Solution();
        
        // Call the minimumCost method and store the result
        int cost = solution.minimumCost(nums);
        
        // Print the result
        System.out.println(cost);
    }
}
```