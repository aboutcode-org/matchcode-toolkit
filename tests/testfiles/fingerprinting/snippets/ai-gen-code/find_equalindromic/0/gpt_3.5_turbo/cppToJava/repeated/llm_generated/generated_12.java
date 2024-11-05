import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE;
        long median = 0;
        int n = nums.length;
        Arrays.sort(nums);
        median = (n % 2 == 1) ? nums[n/2] : (nums[n/2] + nums[n/2 - 1]) / 2;
        
        // Find out possible palindromes
        long[] pal = new long[5];
        String t = Long.toString(median);
        pal[0] = (long) Math.pow(10, t.length()-1) - 1; // case 1: next palindrome with one digit less
        pal[1] = (long) Math.pow(10, t.length()) + 1;   // case 2: next palindrome with one digit more
        
        // case 3: palindrome with just mirror image of the left half
        StringBuilder sb = new StringBuilder(t);
        for (int i = 0; i < t.length()/2; i++) {
            sb.setCharAt(t.length() - i - 1, t.charAt(i));
        }
        pal[2] = Long.parseLong(sb.toString());
        
        // case 4: palindrome with mirror image of the left half with left half + 1
        String palNext = Long.toString(Long.parseLong(t.substring(0,(t.length()+1)/2)) + 1);
        String temp = palNext;
        if (t.length() % 2 == 1) {
            palNext = palNext.substring(0, palNext.length() - 1);
        }
        pal[3] = Long.parseLong(temp + new StringBuilder(palNext).reverse().toString());
        
        // case 5: palindrome with mirror image of the left half with left half - 1
        String palPrev = Long.toString(Long.parseLong(t.substring(0,(t.length()+1)/2)) - 1);
        temp = palPrev;
        if (t.length() % 2 == 1) {
            palPrev = palPrev.substring(0, palPrev.length() - 1);
        }
        pal[4] = Long.parseLong(temp + new StringBuilder(palPrev).reverse().toString());
        
        // find the cost with each palindrome and take the min as answer
        for (long p : pal) {
            long sum = 0;
            for (int num : nums) {
                sum += Math.abs(num - p);
            }
            ans = Math.min(ans, sum);
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine(); // Read the input line
        
        // Remove the square brackets
        line = line.substring(1, line.length() - 1);
        
        String[] numsStr = line.split(",");
        int[] nums = new int[numsStr.length];
        for (int i = 0; i < numsStr.length; i++) {
            nums[i] = Integer.parseInt(numsStr[i].trim());
        }
        
        // Instantiate the Solution class and call the minimumCost method
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        
        // Print the result
        System.out.println(cost);
    }
}