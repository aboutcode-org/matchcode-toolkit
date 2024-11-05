import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();

        boolean pal(int n) {
            String t = String.valueOf(n);
            return t.equals(new StringBuilder(t).reverse().toString());
        }

        int check(int n) {
            int res = Integer.MAX_VALUE;
            for (int a = n; a > 0; a--) {
                if (pal(a)) {
                    int temp = 0;
                    for (int it : nums) {
                        temp += Math.abs(it - a);
                    }
                    res = Math.min(res, temp);
                    break;
                }
            }
            for (int b = n; b < Math.pow(10, 9); b++) {
                if (pal(b)) {
                    int temp = 0;
                    for (int it : nums) {
                        temp += Math.abs(it - b);
                    }
                    res = Math.min(res, temp);
                    break;
                }
            }
            return res;
        }

        return Math.min(check(nums.get(n / 2)), check(nums.get(n / 2 - 1)));
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Read the input string
        String inputStr = br.readLine();

        // Convert the input string to a list of integers
        List<Integer> nums = new ArrayList<>();
        for (String num : inputStr.substring(1, inputStr.length() - 1).split(",")) {
            nums.add(Integer.parseInt(num.trim()));
        }

        // Create an instance of the Solution class
        Solution solution = new Solution();

        // Call the minimumCost method and store the result
        int cost = solution.minimumCost(nums);

        // Print the result
        System.out.println(cost);
    }
}