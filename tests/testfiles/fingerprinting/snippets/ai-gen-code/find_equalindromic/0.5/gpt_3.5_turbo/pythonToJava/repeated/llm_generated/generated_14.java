import java.util.*;
import java.util.stream.*;
import java.io.*;

class Solution {
    public int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();

        Function<Integer, Boolean> pal = (n) -> {
            String t = n.toString();
            return t.equals(new StringBuilder(t).reverse().toString());
        };

        Function<Integer, Integer> check = (n) -> {
            int res = 0;
            for (int a = n; a > 0; a--) {
                if (pal.apply(a)) {
                    res = nums.stream().mapToInt(it -> Math.abs(it - a)).sum();
                    break;
                }
            }
            for (int b = n; b < Math.pow(10, 9); b++) {
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
        String inputStr = scanner.nextLine();
        List<Integer> nums = Arrays.stream(inputStr.split(", ")).map(Integer::parseInt).collect(Collectors.toList());

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}