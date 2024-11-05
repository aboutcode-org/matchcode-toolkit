import java.util.Scanner;
import java.util.Arrays;

public class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        // Check if a number is palindrome
        java.util.function.Predicate<Integer> pal = (n) -> {
            String t = Integer.toString(n);
            return t.equals(new StringBuilder(t).reverse().toString());
        };

        // Check the cost for a given number
        java.util.function.Function<Integer, Integer> check = (n) -> {
            int res = 0;
            for (int a = n; a > 0; a--) {
                if (pal.test(a)) {
                    res = Arrays.stream(nums).map(it -> Math.abs(it - a)).sum();
                    break;
                }
            }
            for (int b = n; b < 1000000000; b++) {
                if (pal.test(b)) {
                    res = Math.min(res, Arrays.stream(nums).map(it -> Math.abs(it - b)).sum());
                    break;
                }
            }
            return res;
        };

        return Math.min(check.apply(nums[n / 2]), check.apply(nums[n / 2 - 1]));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the input string: ");
        String inputStr = scanner.nextLine();
        int[] nums = Arrays.stream(inputStr.split(", ")).mapToInt(Integer::parseInt).toArray();

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}