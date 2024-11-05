import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Solution {
    public long minimumCost(List<Integer> nums) {
        long ans = Long.MAX_VALUE;
        long median = 0;
        int n = nums.size();
        Collections.sort(nums);
        median = (n % 2 == 1) ? nums.get(n / 2) : (nums.get(n / 2) + nums.get(n / 2 - 1)) / 2;

        List<Long> pal = new ArrayList<>();
        String t = String.valueOf(median);
        pal.add((long) Math.pow(10, t.length() - 1) - 1);
        pal.add((long) Math.pow(10, t.length()) + 1);

        StringBuilder sb = new StringBuilder(t);
        for (int i = 0; i < t.length() / 2; i++) {
            sb.setCharAt(t.length() - i - 1, t.charAt(i));
        }
        pal.add(Long.parseLong(sb.toString()));

        String palNext = String.valueOf(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = palNext;
        if (t.length() % 2 == 1) {
            palNext = palNext.substring(0, palNext.length() - 1);
        }
        palNext = temp + new StringBuilder(palNext).reverse().toString();
        pal.add(Long.parseLong(palNext));

        String palPrev = String.valueOf(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = palPrev;
        if (t.length() % 2 == 1) {
            palPrev = palPrev.substring(0, palPrev.length() - 1);
        }
        palPrev = temp + new StringBuilder(palPrev).reverse().toString();
        pal.add(Long.parseLong(palPrev));

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

        List<Integer> nums = new ArrayList<>();
        String[] items = line.split(",");
        for (String item : items) {
            nums.add(Integer.parseInt(item.trim()));
        }

        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}