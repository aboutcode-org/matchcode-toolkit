import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        boolean pal(int num) {
            String t = String.valueOf(num);
            return t.equals(new StringBuilder(t).reverse().toString());
        }

        int check(int num) {
            int res = Integer.MAX_VALUE;
            for (int a = num; a > 0; a--) {
                if (pal(a)) {
                    res = Arrays.stream(nums).map(it -> Math.abs(it - a)).sum();
                    break;
                }
            }
            for (int b = num; b < Math.pow(10, 9); b++) {
                if (pal(b)) {
                    res = Math.min(res, Arrays.stream(nums).map(it -> Math.abs(it - b)).sum());
                    break;
                }
            }
            return res;
        }

        return Math.min(check(nums[n / 2]), check(nums[n / 2 - 1]));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        int[] nums = Arrays.stream(inputStr.split(", ")).mapToInt(Integer::parseInt).toArray();
        
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        
        System.out.println(cost);
    }
}