import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Solution {
    public int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();

        boolean pal(int n) {
            String t = Integer.toString(n);
            return t.equals(new StringBuilder(t).reverse().toString());
        }

        int check(int n) {
            int res = Integer.MAX_VALUE;
            for (int a = n; a > 0; a--) {
                if (pal(a)) {
                    int sum = 0;
                    for (int it : nums) {
                        sum += Math.abs(it - a);
                    }
                    res = sum;
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

        return Math.min(check(nums.get(n / 2)), check(nums.get(n / 2 - 1)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        List<Integer> nums = new ArrayList<>();
        for (String numStr : inputStr.split(",")) {
            nums.add(Integer.parseInt(numStr.trim()));
        }

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}