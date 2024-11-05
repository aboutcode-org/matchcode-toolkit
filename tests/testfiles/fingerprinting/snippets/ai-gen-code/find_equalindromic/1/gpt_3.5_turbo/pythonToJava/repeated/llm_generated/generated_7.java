```java
import java.util.*;

class Solution {
    public int minimumCost(List<Integer> nums) {
        
        Collections.sort(nums);
        int n = nums.size();

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

        return Integer.min(check(nums.get(n/2)), check(nums.get(n/2-1)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input_str = scanner.nextLine();
        List<Integer> nums = new ArrayList<>();
        String[] inputArr = input_str.split(",");
        for (String numStr : inputArr) {
            nums.add(Integer.parseInt(numStr.trim()));
        }
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
    }
}
```