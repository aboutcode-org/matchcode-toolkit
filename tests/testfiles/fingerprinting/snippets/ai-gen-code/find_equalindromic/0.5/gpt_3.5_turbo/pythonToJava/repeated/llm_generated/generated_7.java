import java.util.*;

class Solution {
    public int minimumCost(List<Integer> nums) {
        
        Collections.sort(nums);
        int n = nums.size();

        boolean pal(int num) {
            String t = String.valueOf(num);
            return t.equals(new StringBuilder(t).reverse().toString());
        }
        
        int check(int num) {
            int res = 0;
            for (int a = num; a > 0; a--) {
                if (pal(a)) {
                    res = nums.stream().mapToInt(it -> Math.abs(it - a)).sum();
                    break;
                }
            }
            for (int b = num; b < Math.pow(10, 9); b++) {
                if (pal(b)) {
                    res = Math.min(res, nums.stream().mapToInt(it -> Math.abs(it - b)).sum());
                    break;
                }
            }
            return res;
        }

        return Math.min(check(nums.get(n/2)), check(nums.get(n/2-1)));
    }

    public static void main(String[] args) {
        // Read the input string
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        
        // Convert the input string to a List of Integers
        List<Integer> nums = Arrays.asList(inputStr.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        
        // Create an instance of the Solution class
        Solution solution = new Solution();
        
        // Call the minimumCost method and store the result
        int cost = solution.minimumCost(nums);
        
        // Print the result
        System.out.println(cost);
        
        scanner.close();
    }
}