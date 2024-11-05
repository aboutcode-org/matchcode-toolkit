import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Solution {
    public long minimumCost(List<Integer> nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.size();
        Collections.sort(nums);
        median = (n %2 != 0) ? nums.get(n/2) : (nums.get(n/2) + nums.get(n/2 - 1))/2;
        
        List<Long> pal = new ArrayList<>();
        String t = String.valueOf(median);
        pal.add((long)Math.pow(10, t.length()-1) - 1);
        pal.add((long)Math.pow(10, t.length()) + 1);
        
        StringBuilder sb = new StringBuilder(t);
        for(int i = 0; i < t.length()/2; i++) {
            sb.setCharAt(t.length() - i - 1, t.charAt(i));
        }
        pal.add(Long.parseLong(sb.toString()));
        
        String pal_next = String.valueOf(Integer.parseInt(t.substring(0,(t.length()+1)/2)) + 1);
        String temp = pal_next;
        if(t.length()%2 != 0) pal_next = pal_next.substring(0, pal_next.length() - 1);
        StringBuilder sbNext = new StringBuilder(pal_next);
        sbNext.reverse();
        pal.add(Long.parseLong(temp + sbNext.toString()));
        
        String pal_prev = String.valueOf(Integer.parseInt(t.substring(0,(t.length()+1)/2)) - 1);
        temp = pal_prev;
        if(t.length()%2 != 0) pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        StringBuilder sbPrev = new StringBuilder(pal_prev);
        sbPrev.reverse();
        pal.add(Long.parseLong(temp + sbPrev.toString()));
        
        for(long p: pal) {
            long sum = 0;
            for(int num: nums) sum += Math.abs(num - p);
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

        List<Integer> nums = new ArrayList<>();
        String[] items = line.split(",");
        for(String item : items) {
            nums.add(Integer.parseInt(item.trim()));
        }

        // Instantiate the Solution class and call the minimumCost method
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        // Print the result
        System.out.println(cost);
    }
}