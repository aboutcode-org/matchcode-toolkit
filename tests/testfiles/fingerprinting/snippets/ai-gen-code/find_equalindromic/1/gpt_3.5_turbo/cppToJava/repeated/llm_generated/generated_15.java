import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public long minimumCost(List<Integer> nums) {
        long ans = Long.MAX_VALUE;
        long median = 0;
        long n = nums.size();
        nums.sort(null);
        median = (n % 2 == 1) ? nums.get((int) n/2) : (nums.get((int) n/2) + nums.get((int) n/2 - 1)) / 2;

        List<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long) (Math.pow(10, t.length() - 1) - 1));
        pal.add((long) (Math.pow(10, t.length()) + 1));

        StringBuilder sb = new StringBuilder(t);
        for (int i = 0; i < t.length() / 2; i++) {
            sb.setCharAt(t.length() - i - 1, t.charAt(i));
        }
        pal.add(Long.parseLong(sb.toString()));

        String pal_next = Long.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if (t.length() % 2 == 1) {
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        }
        pal_next = temp + new StringBuilder(pal_next).reverse().toString();
        pal.add(Long.parseLong(pal_next));

        String pal_prev = Long.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if (t.length() % 2 == 1) {
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        }
        pal_prev = temp + new StringBuilder(pal_prev).reverse().toString();
        pal.add(Long.parseLong(pal_prev));

        for (Long p : pal) {
            long sum = 0;
            for (int num : nums) {
                sum += Math.abs(num - p);
            }
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        line = line.substring(1, line.length() - 1);
        String[] numArr = line.split(",");
        List<Integer> nums = new ArrayList<>();
        for (String num : numArr) {
            nums.add(Integer.parseInt(num.trim()));
        }

        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}