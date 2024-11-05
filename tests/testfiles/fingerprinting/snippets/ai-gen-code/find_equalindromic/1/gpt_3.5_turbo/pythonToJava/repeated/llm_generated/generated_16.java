import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Solution {
    public int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();

        // Palindrome check function
        Function<Integer, Boolean> pal = (Integer num) -> {
            String t = num.toString();
            return t.equals(new StringBuilder(t).reverse().toString());
        };

        // Check function
        Function<Integer, Integer> check = (Integer num) -> {
            int res = 0;
            for (int a = num; a > 0; a--) {
                if (pal.apply(a)) {
                    res = nums.stream().mapToInt(it -> Math.abs(it - a)).sum();
                    break;
                }
            }
            for (int b = num; b < Math.pow(10, 9); b++) {
                if (pal.apply(b)) {
                    res = Math.min(res, nums.stream().mapToInt(it -> Math.abs(it - b)).sum());
                    break;
                }
            }
            return res;
        };

        return Math.min(check.apply(nums.get(n / 2)), check.apply(nums.get(n / 2 - 1)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the list of integers: ");
        String input = scanner.nextLine();
        List<Integer> nums = new ArrayList<>(List.of(input.split(", ")).stream().map(Integer::parseInt).toList());

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);

        System.out.println("Minimum cost: " + cost);
    }
}