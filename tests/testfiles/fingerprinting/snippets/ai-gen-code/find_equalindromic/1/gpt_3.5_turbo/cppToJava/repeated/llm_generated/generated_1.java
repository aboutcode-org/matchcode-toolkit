import java.util.Arrays;
import java.util.ArrayList;

class Solution {
    public long minimumCost(int[] nums) {
        long ans = Long.MAX_VALUE;
        long median = 0;
        int n = nums.length;
        Arrays.sort(nums);
        median = (n % 2 == 1) ? nums[n/2] : (nums[n/2] + nums[n/2 - 1]) / 2;
        
        // Find out possible palindroms
        ArrayList<Long> pal = new ArrayList<>();
        String t = String.valueOf(median);
        pal.add((long)Math.pow(10, t.length() - 1) - 1); // case 1 : next palindrom with one digit less
        pal.add((long)Math.pow(10, t.length()) + 1); // case 2: next palindrom with one digit more
        
        // case 3: palindrom with just mirror image of the left half
        StringBuilder sb = new StringBuilder(t);
        for(int i = 0; i < t.length() / 2; i++) {
            sb.setCharAt(t.length() - i - 1, t.charAt(i));
        }
        pal.add(Long.parseLong(sb.toString()));
        
        // case 4: palidrom with mirror image of the left half with left half + 1
        String pal_next = String.valueOf(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;
        if(t.length() % 2 == 1) {
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        }
        StringBuilder sbNext = new StringBuilder(pal_next);
        pal.add(Long.parseLong(temp + sbNext.reverse().toString()));
        
        // case 5: palidrom with mirror image of the left half with left half - 1
        String pal_prev = String.valueOf(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if(t.length() % 2 == 1) {
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        }
        StringBuilder sbPrev = new StringBuilder(pal_prev);
        pal.add(Long.parseLong(temp + sbPrev.reverse().toString()));
        
        // find the cost with each palindrom and take the min as answer
        for(Long p : pal) {
            long sum = 0;
            for(int num : nums) {
                sum += Math.abs(num - p);
            }
            ans = Math.min(ans, sum);
        }
        
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 4, 5}; // Example input
        long cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}