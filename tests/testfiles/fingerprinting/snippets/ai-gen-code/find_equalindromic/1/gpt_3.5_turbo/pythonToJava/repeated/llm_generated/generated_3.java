import java.util.*;

public class Solution {
    
    public int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();
        
        String pal = new StringBuilder(String.valueOf(n)).reverse().toString();
        
        int check(int val) {
            for (int a = val; a > 0; a--) {
                if (String.valueOf(a).equals(pal)) {
                    int res = 0;
                    for (int it : nums) {
                        res += Math.abs(it - a);
                    }
                    break;
                }
            }
            for (int b = val; b < Math.pow(10, 9); b++) {
                if (String.valueOf(b).equals(pal)) {
                    int res = 0;
                    for (int it : nums) {
                        res = Math.min(res, Math.abs(it - b));
                    }
                    break;
                }
            }
            return res;
        }

        return Math.min(check(nums.get(n / 2)), check(nums.get(n / 2 - 1)));
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the input string
        String inputStr = scanner.nextLine();
        
        // Convert the input string to a Java list
        List<Integer> nums = new ArrayList<>();
        for (String num : inputStr.substring(1, inputStr.length() - 1).split(", ")) {
            nums.add(Integer.parseInt(num));
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