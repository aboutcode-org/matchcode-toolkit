import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public long minimumCost(List<Integer> nums) {
        long ans = Long.MAX_VALUE;
        long median = 0;
        int n = nums.size();
        
        // Sort the input array
        Integer[] arr = nums.toArray(new Integer[0]);
        Arrays.sort(arr);
        
        // Calculate the median
        median = (n % 2 == 1) ? arr[n/2] : (arr[n/2] + arr[n/2 - 1])/2;
        
        // Find out possible palindromes
        List<Long> pal = new ArrayList<>();
        String t = String.valueOf(median);
        pal.add((long) (Math.pow(10, t.length()-1) - 1)); //next palindrome with one digit less
        pal.add((long) (Math.pow(10, t.length()) + 1));   //next palindrome with one digit more
        
        //Palindrome with just mirror image of the left half
        StringBuilder sb = new StringBuilder(t);
        for(int i = 0; i < t.length()/2; ++i){
            sb.setCharAt(t.length() - i - 1, t.charAt(i));
        }
        pal.add(Long.parseLong(sb.toString()));
        
        //Palindrome with mirror image of the left half with left half + 1
        String palNext = String.valueOf(Integer.parseInt(t.substring(0,(t.length()+1)/2)) + 1);
        String temp = palNext;
        if(t.length()%2 == 1){
            palNext = palNext.substring(0, palNext.length() - 1);
        }
        StringBuilder mirrorNext = new StringBuilder(palNext).reverse();
        pal.add(Long.parseLong(temp + mirrorNext.toString()));
        
        //Palindrome with mirror image of the left half with left half - 1
        String palPrev = String.valueOf(Integer.parseInt(t.substring(0,(t.length()+1)/2)) - 1);
        temp = palPrev;
        if(t.length()%2 == 1){
            palPrev = palPrev.substring(0, palPrev.length() - 1);
        }
        StringBuilder mirrorPrev = new StringBuilder(palPrev).reverse();
        pal.add(Long.parseLong(temp + mirrorPrev.toString()));
        
        // Calculate cost with each palindrome and take the minimum as answer
        for(long p: pal){
            long sum = 0;
            for(int num: arr){
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
        
        // Remove the square brackets
        line = line.substring(1, line.length() - 1);
        
        // Parse the integers from the string
        String[] arr = line.split(",");
        List<Integer> nums = new ArrayList<>();
        for (String s : arr) {
            nums.add(Integer.parseInt(s));
        }
        
        // Instantiate the Solution class and call the minimumCost method
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);
        
        // Print the result
        System.out.println(cost);
        
        scanner.close();
    }
}