import java.util.*;
import java.util.stream.*;

class Solution {
    public long minimumCost(List<Integer> nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.size();
        Collections.sort(nums);
        median = (n % 2 == 1) ? nums.get(n / 2) : (nums.get(n / 2) + nums.get(n / 2 - 1)) / 2;
        
        List<Long> pal = new ArrayList<>();
        String t = String.valueOf(median);
        pal.add((long) (Math.pow(10, t.length() - 1) - 1)); // case 1
        pal.add((long) (Math.pow(10, t.length()) + 1)); // case 2
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t.length() / 2; i++) {
            sb.append(t.charAt(t.length() - i - 1));
        }
        String palindrome = sb.toString();
        pal.add(Long.parseLong(palindrome));
        
        String palNext = String.valueOf(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) + 1);
        if (t.length() % 2 == 1) {
            palNext = palNext.substring(0, palNext.length() - 1);
        }
        String temp = palNext;
        palNext = new StringBuilder(palNext).reverse().toString();
        pal.add(Long.parseLong(temp + palNext));
        
        String palPrev = String.valueOf(Integer.parseInt(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = palPrev;
        if (t.length() % 2 == 1) {
            palPrev = palPrev.substring(0, palPrev.length() - 1);
        }
        palPrev = new StringBuilder(palPrev).reverse().toString();
        pal.add(Long.parseLong(temp + palPrev));
        
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
        
        List<Integer> nums = Arrays.stream(line.split(","))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList());
                                    
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        
        System.out.println(cost);
    }
}