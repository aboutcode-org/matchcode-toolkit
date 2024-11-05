import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public long minimumCost(List<Integer> nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.size();
        Collections.sort(nums);
        median = (n % 2 == 1) ? nums.get(n/2) : (nums.get(n/2) + nums.get(n/2 - 1)) / 2;
        
        List<Long> pal = new ArrayList<>();
        String t = String.valueOf(median);
        pal.add((long) (Math.pow(10, t.length()-1) - 1)); 
        pal.add((long) (Math.pow(10, t.length()) + 1));   
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t.length()/2; ++i) {
            sb.append(t.charAt(t.length() - i - 1));
        }
        String mirror = sb.toString();
        pal.add(Long.parseLong(mirror));
        
        String pal_next = String.valueOf(Integer.parseInt(t.substring(0,(t.length()+1)/2)) + 1);
        String temp = pal_next;
        if(t.length()%2 == 1) pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal_next = temp + new StringBuilder(pal_next).reverse().toString();
        pal.add(Long.parseLong(pal_next));
        
        String pal_prev = String.valueOf(Integer.parseInt(t.substring(0,(t.length()+1)/2)) - 1);
        temp = pal_prev;
        if(t.length()%2 == 1)  pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal_prev = temp + new StringBuilder(pal_prev).reverse().toString();
        pal.add(Long.parseLong(pal_prev));
        
        for(long p : pal) {
            long sum = 0;
            for(int num : nums) {
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

        List<Integer> nums = new ArrayList<>();
        String[] items = line.split(",");
        for (String item : items) {
            nums.add(Integer.parseInt(item.trim()));
        }

        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        System.out.println(cost);
        scanner.close();
    }
}