import java.util.*;

class Solution {
    public int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();

        String pal = (String n) -> {
            String t = String.valueOf(n);
            return t.equals(new StringBuilder(t).reverse().toString());
        };

        int check = (int n) -> {
            int res = 0;
            for (int a = n; a > 0; a--) {
                if (pal.check(a)) {
                    int sum = 0;
                    for (int it : nums) {
                        sum += Math.abs(it - a);
                    }
                    res = sum;
                    break;
                }
            }
            for (int b = n; b < Math.pow(10, 9); b++) {
                if (pal.check(b)) {
                    int sum = 0;
                    for (int it : nums) {
                        sum += Math.abs(it - b);
                    }
                    res = Math.min(res, sum);
                    break;
                }
            }
            return res;
        };

        return Math.min(check.check(nums.get(n / 2)), check.check(nums.get(n / 2 - 1)));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<Integer> nums = Arrays.stream(input.split(","))
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList());
        
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        
        System.out.println(cost);
    }
}