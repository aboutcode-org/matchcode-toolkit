import java.util.*;

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
                    res = Math.min(res, nums.stream().mapToInt(it -> Math.abs(it - a)).sum());
                    break;
                }
            }
            for (int b = n; b < Math.pow(10, 9); b++) {
                if (pal(b)) {
                    res = Math.min(res, nums.stream().mapToInt(it -> Math.abs(it - b)).sum());
                    break;
                }
            }
            return res;
        }

        return Math.min(check(nums.get(n / 2)), check(nums.get(n / 2 - 1)));
    }

    public static void main(String[] args) {
        // Read the input string
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        
        // Convert the input string to a Java List
        List<Integer> nums = new ArrayList<>();
        for (String numStr : inputStr.split(",")) {
            nums.add(Integer.parseInt(numStr.trim()));
        }
        
        // Create an instance of the Solution class
        Solution solution = new Solution();
        
        // Call the minimumCost method and store the result
        int cost = solution.minimumCost(nums);
        
        // Print the result
        System.out.println(cost);
        
        scanner.close();
    }
}