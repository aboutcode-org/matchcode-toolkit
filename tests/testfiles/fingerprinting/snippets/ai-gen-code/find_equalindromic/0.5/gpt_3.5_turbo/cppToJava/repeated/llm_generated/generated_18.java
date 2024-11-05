import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE;
        long median = 0;
        int n = nums.length;
        Arrays.sort(nums);
        median = (n % 2 == 1) ? nums[n / 2] : (nums[n / 2] + nums[n / 2 - 1]) / 2;
        
        long[] pal = new long[5];
        String t = String.valueOf(median);
        pal[0] = (long) (Math.pow(10, t.length() - 1) - 1);
        pal[1] = (long) (Math.pow(10, t.length()) + 1);
        
        StringBuilder sb = new StringBuilder(t);
        for (int i = 0; i < t.length() / 2; i++) {
            sb.setCharAt(t.length() - i - 1, t.charAt(i));
        }
        pal[2] = Long.parseLong(sb.toString());
        
        String pal_next = String.valueOf(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if (t.length() % 2 == 1) {
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        }
        pal_next = temp + new StringBuilder(pal_next).reverse().toString();
        pal[3] = Long.parseLong(pal_next);
        
        String pal_prev = String.valueOf(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if (t.length() % 2 == 1) {
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        }
        pal_prev = temp + new StringBuilder(pal_prev).reverse().toString();
        pal[4] = Long.parseLong(pal_prev);
        
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
        String line = scanner.nextLine();
        line = line.substring(1, line.length() - 1);
        String[] parts = line.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }
        
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        
        System.out.println(cost);
    }
}