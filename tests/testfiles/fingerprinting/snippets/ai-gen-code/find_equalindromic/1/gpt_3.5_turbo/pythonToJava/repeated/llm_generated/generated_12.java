import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();

        String palCheck = (n) -> {
            String t = String.valueOf(n);
            return t.equals(new StringBuilder(t).reverse().toString());
        };

        int check = (n) -> {
            int res = 0;
            for (int a = n; a > 0; a--) {
                if (palCheck.check(a)) {
                    for (Integer it : nums) {
                        res += Math.abs(it - a);
                    }
                    break;
                }
            }
            for (int b = n; b < Math.pow(10, 9); b++) {
                if (palCheck.check(b)) {
                    int sumDiff = 0;
                    for (Integer it : nums) {
                        sumDiff += Math.abs(it - b);
                    }
                    res = Math.min(res, sumDiff);
                    break;
                }
            }
            return res;
        };

        return Math.min(check.check(nums.get(n / 2)), check.check(nums.get(n / 2 - 1)));
    }

    public static void main(String[] args) {
        // Read the input string
        Scanner scan = new Scanner(System.in);
        String inputStr = scan.nextLine();
        scan.close();

        // Convert the input string to a Java list
        List<Integer> nums = new ArrayList<>();
        for (String str : inputStr.split(",")) {
            nums.add(Integer.parseInt(str));
        }

        // Create an instance of the Solution class
        Solution solution = new Solution();

        // Call the minimumCost method and store the result
        int cost = solution.minimumCost(nums);

        // Print the result
        System.out.println(cost);
    }
}