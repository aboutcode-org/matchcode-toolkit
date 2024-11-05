import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine(); // Read the input line

        // Remove the square brackets
        line = line.substring(1, line.length() - 1);

        List<Integer> nums = new ArrayList<>();
        String[] numsStr = line.split(",");
        for (String numStr : numsStr) {
            nums.add(Integer.parseInt(numStr));
        }

        // Instantiate the Solution class and call the minimumCost method
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        // Print the result
        System.out.println(cost);
    }

    public long minimumCost(List<Integer> nums) {
        long ans = Long.MAX_VALUE, median = 0;
        int n = nums.size();
        Collections.sort(nums);

        median = (n % 2 == 1) ? nums.get(n / 2) : (nums.get(n / 2) + nums.get(n / 2 - 1)) / 2;

        // Find out possible palindroms
        List<Long> pal = new ArrayList<>();
        String t = Long.toString(median);
        pal.add((long) (Math.pow(10, t.length() - 1) - 1)); // case 1: next palindrom with one digit less
        pal.add((long) (Math.pow(10, t.length()) + 1)); // case 2: next palindrom with one digit more

        // case 3: palidrom with just mirror image of the left half
        StringBuilder sb = new StringBuilder(t);
        for (int i = 0; i < t.length() / 2; i++) {
            sb.setCharAt(t.length() - i - 1, t.charAt(i));
        }
        pal.add(Long.parseLong(sb.toString()));

        // case 4: palidrom with mirror image of the left half with left half + 1
        String palNext = Long.toString(Long.parseLong(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = palNext;
        if (t.length() % 2 == 1) palNext = palNext.substring(0, palNext.length() - 1);
        palNext = temp + new StringBuilder(palNext).reverse().toString();
        pal.add(Long.parseLong(palNext));

        // case 5: palidrom with mirror image of the left half with left half - 1
        String palPrev = Long.toString(Long.parseLong(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = palPrev;
        if (t.length() % 2 == 1) palPrev = palPrev.substring(0, palPrev.length() - 1);
        palPrev = temp + new StringBuilder(palPrev).reverse().toString();
        pal.add(Long.parseLong(palPrev));

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