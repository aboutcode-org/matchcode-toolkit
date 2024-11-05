import java.util.*;

public class Solution {
    public int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();

        // Check if a number is a palindrome
        // Return true if the number is a palindrome, false otherwise
        // Ex: pal(121) -> true, pal(123) -> false
        // Function converted to Java
        Function<Integer, Boolean> pal = n -> {
            String t = Integer.toString(n);
            return t.equals(new StringBuilder(t).reverse().toString());
        };
        
        // Check the cost of changing all elements in nums to palindrome numbers
        // Return the minimum cost
        // Ex: check(15) -> 3
        // Function converted to Java
        Function<Integer, Integer> check = n -> {
            int res = 0;
            for (int a = n; a > 0; a--) {
                if (pal.apply(a)) {
                    for (int it : nums) {
                        res += Math.abs(it - a);
                    }
                    break;
                }
            }
            for (int b = n; b < Math.pow(10, 9); b++) {
                if (pal.apply(b)) {
                    res = Math.min(res, Arrays.stream(nums.toArray(new Integer[0])).mapToInt(it -> Math.abs(it - b)).sum());
                    break;
                }
            }
            return res;
        };
        
        return Math.min(check.apply(nums.get(n / 2)), check.apply(nums.get(n / 2 - 1)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input_str = scanner.nextLine();
        
        // Convert the input string to a List of Integers
        List<Integer> nums = new ArrayList<>();
        for (String num : input_str.substring(1, input_str.length() - 1).split(", ")) {
            nums.add(Integer.parseInt(num));
        }

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        
        System.out.println(cost);
    }
}