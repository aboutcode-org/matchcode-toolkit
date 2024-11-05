import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    public int minimumCost(List<Integer> nums) {
        nums.sort(null);
        int n = nums.size();
        
        // Inner method to check if a number is a palindrome
        boolean pal(int num) {
            String t = String.valueOf(num);
            return t.equals(new StringBuilder(t).reverse().toString());
        }
        
        // Inner method to calculate the cost
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

        return Math.min(check(nums.get(n / 2)), check(nums.get(n / 2 - 1)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Convert the input String to a list of integers
        List<Integer> nums = new ArrayList<>();
        for (String num : input.split(",")) {
            nums.add(Integer.parseInt(num.trim()));
        }

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}