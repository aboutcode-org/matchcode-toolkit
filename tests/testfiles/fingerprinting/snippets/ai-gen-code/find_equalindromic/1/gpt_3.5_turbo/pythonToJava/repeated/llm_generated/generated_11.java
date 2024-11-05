```java
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int minimumCost(List<Integer> nums) {
        nums.sort(null);
        int n = nums.size();

        boolean pal(int n) {
            String t = String.valueOf(n);
            return t.equals(new StringBuilder(t).reverse().toString());
        }

        int check(int n) {
            int res = Integer.MAX_VALUE;
            for (int a = n; a > 0; a--) {
                if (pal(a)) {
                    res = Arrays.stream(nums.toArray(new Integer[0])).mapToInt(it -> Math.abs(it - a)).sum();
                    break;
                }
            }
            for (int b = n; b < (int)Math.pow(10, 9); b++) {
                if (pal(b)) {
                    res = Math.min(res, Arrays.stream(nums.toArray(new Integer[0])).mapToInt(it -> Math.abs(it - b)).sum());
                    break;
                }
            }
            return res;
        }

        return Math.min(check(nums.get(n / 2)), check(nums.get(n / 2 - 1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        
        List<Integer> nums = Arrays.asList(inputString.split(", ")).stream().map(Integer::parseInt).collect(Collectors.toList());
        
        Solution solution = new Solution();
        
        int cost = solution.minimumCost(nums);
        
        System.out.println(cost);
    }
}
```