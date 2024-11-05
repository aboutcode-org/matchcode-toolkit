import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Solution {
    public int minimumCost(List<Integer> nums) {
        nums.sort(null);
        int n = nums.size();

        boolean pal(int n) {
            String t = Integer.toString(n);
            return t.equals(new StringBuilder(t).reverse().toString());
        }

        int check(int n) {
            int res = Integer.MAX_VALUE;
            for (int a = n; a > 0; a--) {
                if (pal(a)) {
                    res = Arrays.stream(nums.stream().mapToInt(it -> Math.abs(it - a)).toArray()).sum();
                    break;
                }
            }
            for (int b = n; b < Math.pow(10, 9); b++) {
                if (pal(b)) {
                    res = Math.min(res, Arrays.stream(nums.stream().mapToInt(it -> Math.abs(it - b)).toArray()).sum());
                    break;
                }
            }
            return res;
        }

        return Math.min(check(nums.get(n / 2)), check(nums.get(n / 2 - 1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the input string
        String inputStr = scanner.nextLine();
        
        // Convert the input string to a List of integers
        List<Integer> nums = new ArrayList<>();
        Arrays.stream(inputStr.split(", ")).mapToInt(Integer::parseInt).forEach(nums::add);

        // Create an instance of the Solution class
        Solution solution = new Solution();
        
        // Call the minimumCost method and store the result
        int cost = solution.minimumCost(nums);
        
        // Print the result
        System.out.println(cost);
        
        scanner.close();
    }
}