import java.util.*;
import java.io.*;

class Solution {
    public long minimumCost(ArrayList<Integer> nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.size();
        Collections.sort(nums);
        median = (n % 2 == 1) ? nums.get(n / 2) : (nums.get(n / 2) + nums.get(n / 2 - 1)) / 2;

        ArrayList<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long) (Math.pow(10, t.length() - 1) - 1));
        pal.add((long) (Math.pow(10, t.length()) + 1));

        StringBuilder sb = new StringBuilder(t);
        for (int i = 0; i < t.length() / 2; i++) {
            sb.setCharAt(t.length() - i - 1, t.charAt(i));
        }
        pal.add(Long.parseLong(sb.toString()));

        String pal_next = Integer.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if (t.length() % 2 == 1) {
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        }
        StringBuilder sb1 = new StringBuilder(pal_next);
        pal_next = sb1.reverse().toString();
        pal.add(Long.parseLong(temp + pal_next));

        String pal_prev = Integer.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if (t.length() % 2 == 1) {
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        }
        StringBuilder sb2 = new StringBuilder(pal_prev);
        pal_prev = sb2.reverse().toString();
        pal.add(Long.parseLong(temp + pal_prev));

        for (long p : pal) {
            long sum = 0;
            for (int num : nums) {
                sum += Math.abs(num - p);
            }
            ans = Math.min(ans, sum);
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        // Remove the square brackets
        line = line.substring(1, line.length() - 1);

        StringTokenizer st = new StringTokenizer(line, ",");
        ArrayList<Integer> nums = new ArrayList<>();
        while (st.hasMoreTokens()) {
            nums.add(Integer.parseInt(st.nextToken()));
        }

        // Instantiate the Solution class and call the minimumCost method
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        // Print the result
        System.out.println(cost);
    }
}