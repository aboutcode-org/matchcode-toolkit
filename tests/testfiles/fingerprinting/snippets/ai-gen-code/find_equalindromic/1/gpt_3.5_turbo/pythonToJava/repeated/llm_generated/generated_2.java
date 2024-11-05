import java.util.*;

public class Solution {
    public int minimumCost(List<Integer> nums) {
        
        Collections.sort(nums);
        int n = nums.size();

        boolean pal(int x) {
            String t = Integer.toString(x);
            return t.equals(new StringBuilder(t).reverse().toString());
        }
        
        int check(int x) {
            int res = Integer.MAX_VALUE;
            for (int a = x; a > 0; a--) {
                if (pal(a)) {
                    int sum = 0;
                    for (int it : nums) {
                        sum += Math.abs(it - a);
                    }
                    res = sum;
                    break;
                }
            }
            for (int b = x; b < Math.pow(10, 9); b++) {
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

        return Math.min(check(nums.get(n / 2)), check(nums.get(n / 2 - 1)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the input string
        String input_str = scanner.nextLine();
        
        // Convert the input string to a list of integers
        List<Integer> nums = new ArrayList<>();
        for (String num : input_str.split(",")) {
            nums.add(Integer.parseInt(num.trim()));
        }
        
        // Create an instance of the Solution class
        Solution solution = new Solution();
        
        // Call the minimumCost method and store the result
        int cost = solution.minimumCost(nums);
        
        // Print the result
        System.out.println(cost);
        
        scanner.close();
    }
}