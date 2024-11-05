import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();

        return Math.min(check(nums.get(n / 2)), check(nums.get(n / 2 - 1)));
    }

    private boolean pal(int n) {
        String t = Integer.toString(n);
        return t.equals(new StringBuilder(t).reverse().toString());
    }

    private int check(int n) {
        int res = Integer.MAX_VALUE;
        for (int a = n; a > 0; a--) {
            if (pal(a)) {
                int sum = 0;
                for (int it : nums) {
                    sum += Math.abs(it - a);
                }
                res = Math.min(res, sum);
                break;
            }
        }
        for (int b = n; b < Math.pow(10, 9); b++) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String input = scanner.nextLine();
        List<Integer> nums = new ArrayList<>();
        for (String num : input.trim().split(",")) {
            nums.add(Integer.parseInt(num.trim()));
        }

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}