import java.util.*;

class Solution {
    public int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();
        
        // Check if a number is a palindrome
        Function<Integer, Boolean> pal = (n) -> {
            String t = String.valueOf(n);
            return t.equals(new StringBuilder(t).reverse().toString());
        };
        
        // Function to calculate the cost
        Function<Integer, Integer> check = (n) -> {
            int res = Integer.MAX_VALUE;
            for (int a = n; a > 0; a--) {
                if (pal.apply(a)) {
                    int sum = 0;
                    for (int it : nums) {
                        sum += Math.abs(it - a);
                    }
                    res = sum;
                    break;
                }
            }
            for (int b = n; b < Math.pow(10, 9); b++) {
                if (pal.apply(b)) {
                    int sum = 0;
                    for (int it : nums) {
                        sum += Math.abs(it - b);
                    }
                    res = Math.min(res, sum);
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
        String input = scanner.nextLine();
        
        // Convert the input string to a List of Integers
        List<Integer> nums = Arrays.stream(input.split(","))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList());
        
        // Create an instance of the Solution class
        Solution solution = new Solution();
        
        // Call the minimumCost method and store the result
        int cost = solution.minimumCost(nums);
        
        // Print the result
        System.out.println(cost);
        
        scanner.close();
    }
}