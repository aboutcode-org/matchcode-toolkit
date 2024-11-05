import java.util.*;
import java.lang.*;
import org.apache.commons.lang3.StringUtils;

class Solution {
    public int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();

        Function<Integer, Boolean> pal = (n) -> {
            String t = Integer.toString(n);
            return t.equals(StringUtils.reverse(t));
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

        // Read the input string
        String inputStr = scanner.nextLine();

        // Convert the input string to a List of Integers
        List<Integer> nums = Arrays.stream(inputStr.split(",")).map(Integer::parseInt).collect(Collectors.toList());

        // Create an instance of the Solution class
        Solution solution = new Solution();

        // Call the minimumCost method and store the result
        int cost = solution.minimumCost(nums);

        // Print the result
        System.out.println(cost);
    }
}