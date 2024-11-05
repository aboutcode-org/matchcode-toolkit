import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE;
        long median = 0;
        int n = nums.length;
        Arrays.sort(nums);
        median = (n % 2 == 1) ? nums[n / 2] : (nums[n / 2] + nums[n / 2 - 1]) / 2;
        // Find out possible palindroms
        long[] pal = new long[5];
        String t = Long.toString(median);
        pal[0] = (long) Math.pow(10, t.length() - 1) - 1; // case 1 : next palindrom with one digit less
        pal[1] = (long) Math.pow(10, t.length()) + 1; // case 2: next palindrom with one digit more
        // case 3: palidrom with just mirror image of the left half
        StringBuilder sb = new StringBuilder(t);
        for (int i = 0; i < t.length() / 2; i++) {
            sb.setCharAt(t.length() - i - 1, t.charAt(i));
        }
        pal[2] = Long.parseLong(sb.toString());
        // case 4: palidrom with mirror image of the left half with left half + 1
        String pal_next = Long.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if (t.length() % 2 == 1) {
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        }
        StringBuilder sb1 = new StringBuilder(pal_next);
        pal[3] = Long.parseLong(temp + sb1.reverse().toString());
        // case 5: palidrom with mirror image of the left half with left half - 1
        String pal_prev = Long.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if (t.length() % 2 == 1) {
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        }
        StringBuilder sb2 = new StringBuilder(pal_prev);
        pal[4] = Long.parseLong(temp + sb2.reverse().toString());
        // find the cost with each palindrom and take the min as answer
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

        String[] parts = line.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }

        // Instantiate the Solution class and call the minimumCost method
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        // Print the result
        System.out.println(cost);
    }
}