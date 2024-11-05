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
        String t = Long.toString(median);
        pal[0] = (long) (Math.pow(10, t.length() - 1) - 1);
        pal[1] = (long) (Math.pow(10, t.length()) + 1);
        
        StringBuilder sb = new StringBuilder(t);
        for (int i = 0; i < t.length() / 2; i++) {
            sb.setCharAt(t.length() - i - 1, t.charAt(i));
        }
        pal[2] = Long.parseLong(sb.toString());
        
        String palNext = Long.toString(Long.parseLong(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = palNext;
        if (t.length() % 2 == 1) {
            palNext = palNext.substring(0, palNext.length() - 1);
        }
        palNext = temp + new StringBuilder(palNext).reverse().toString();
        pal[3] = Long.parseLong(palNext);
        
        String palPrev = Long.toString(Long.parseLong(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = palPrev;
        if (t.length() % 2 == 1) {
            palPrev = palPrev.substring(0, palPrev.length() - 1);
        }
        palPrev = temp + new StringBuilder(palPrev).reverse().toString();
        pal[4] = Long.parseLong(palPrev);
        
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