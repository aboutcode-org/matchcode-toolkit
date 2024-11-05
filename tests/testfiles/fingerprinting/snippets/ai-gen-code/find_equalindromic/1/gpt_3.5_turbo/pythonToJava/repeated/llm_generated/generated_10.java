import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Solution {
    public int minimumCost(List<Integer> nums) {
        
        int n = nums.size();
        
        nums.sort(null);
        
        String pal(int num){
            String t = String.valueOf(num);
            return t.equals(new StringBuilder(t).reverse().toString());
        }
        
        int check(int num) {
            int res = 0;
            for(int a = num; a > 0; a--) {
                if(pal(a)) {
                    res = Arrays.stream(nums.toArray()).mapToInt(it -> Math.abs((int)it - a)).sum();
                    break;
                }
            }
            for(int b = num; b < Math.pow(10, 9); b++) {
                if(pal(b)){
                    res = Math.min(res, Arrays.stream(nums.toArray()).mapToInt(it -> Math.abs((int)it - b)).sum());
                    break;
                }
            }
            return res;
        }

        return Math.min(check(nums.get(n/2)), check(nums.get(n/2-1)));
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input_str = scanner.nextLine();
        List<Integer> nums = new ArrayList<>();
        String[] input = input_str.substring(1, input_str.length() - 1).split(",");
        for(String s : input) {
            nums.add(Integer.parseInt(s.trim()));
        }
        
        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);
        System.out.println(cost);
        
        scanner.close();
    }
}