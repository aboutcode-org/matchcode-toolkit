import java.util.*;

class Solution {
    public long minimumCost(List<Integer> nums) {
        long ans = Long.MAX_VALUE;
        long median = 0;
        int n = nums.size();
        Collections.sort(nums);
        median = (n % 2 == 1) ? nums.get(n / 2) : (nums.get(n / 2) + nums.get(n / 2 - 1)) / 2;
        
        List<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long) Math.pow(10, t.length() - 1) - 1); // case 1: next palindrom with one digit less
        pal.add((long) Math.pow(10, t.length()) + 1);      // case 2: next palindrom with one digit more
        
        // case 3: palidrom with just mirror image of the left half
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < t.length() / 2; i++) {
            builder.append(t.charAt(t.length() - i - 1));
        }
        pal.add(Long.parseLong(builder.toString()));
        
        // case 4: palidrom with mirror image of the left half with left half + 1
        String palNext = Long.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = palNext;
        if(t.length() % 2 == 1) palNext = palNext.substring(0, palNext.length() - 1);
        builder = new StringBuilder(palNext);
        builder.reverse();
        pal.add(Long.parseLong(temp + builder.toString()));
        
        // case 5: palidrom with mirror image of the left half with left half - 1
        String palPrev = Long.toString(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = palPrev;
        if(t.length() % 2 == 1) palPrev = palPrev.substring(0, palPrev.length() - 1);
        builder = new StringBuilder(palPrev);
        builder.reverse();
        pal.add(Long.parseLong(temp + builder.toString()));
        
        for(Long p : pal) {
            long sum = 0;
            for(int num : nums) sum += Math.abs(num - p);
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
        for(String item : items) {
            nums.add(Integer.parseInt(item.trim()));
        }
        
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        
        System.out.println(cost);
    }
}