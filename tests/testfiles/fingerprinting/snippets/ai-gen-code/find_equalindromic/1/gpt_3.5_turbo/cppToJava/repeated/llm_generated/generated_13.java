import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

class Solution {
    public long minimumCost(List<Integer> nums) {
        long ans = Long.MAX_VALUE;
        long median = 0;
        int n = nums.size();
        Collections.sort(nums);
        median = (n % 2 == 1) ? nums.get(n/2) : (nums.get(n/2) + nums.get(n/2 - 1)) / 2;
        
        List<Long> pal = new ArrayList<>();
        String t = String.valueOf(median);
        pal.add((long)Math.pow(10, t.length()-1) - 1);
        pal.add((long)Math.pow(10, t.length()) + 1);
        
        char[] temp = t.toCharArray();
        for(int i = 0; i < t.length() / 2; ++i) {
            temp[t.length() - i - 1] = temp[i];
        }
        pal.add(Long.parseLong(String.valueOf(temp)));
        
        String pal_next = String.valueOf(Integer.parseInt(t.substring(0, (t.length()+1)/2)) + 1);
        String tempStr = pal_next;
        if(t.length() % 2 == 1) {
            pal_next = pal_next.substring(0, pal_next.length() - 1);
        }
        StringBuilder sb = new StringBuilder(pal_next);
        sb.reverse();
        pal.add(Long.parseLong(tempStr + sb.toString()));
        
        String pal_prev = String.valueOf(Integer.parseInt(t.substring(0, (t.length()+1)/2)) - 1);
        tempStr = pal_prev;
        if(t.length() % 2 == 1) {
            pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        }
        sb = new StringBuilder(pal_prev);
        sb.reverse();
        pal.add(Long.parseLong(tempStr + sb.toString()));
        
        for(Long p: pal) {
            long sum = 0;
            for(int num: nums) {
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
        String[] numsStr = line.split(",");
        List<Integer> nums = new ArrayList<>();
        for(String numStr: numsStr) {
            nums.add(Integer.parseInt(numStr.trim()));
        }

        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        System.out.println(cost);

        scanner.close();
    }
}