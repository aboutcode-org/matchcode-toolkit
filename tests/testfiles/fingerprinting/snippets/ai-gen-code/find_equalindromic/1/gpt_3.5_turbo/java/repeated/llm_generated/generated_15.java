import java.util.*;
class Solution {
    public long findCost(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        int mid = arr[len/2];
        int incVal = mid;
        int decVal = mid;

        while(!checkPalindrome(incVal)) incVal++; 
        while(!checkPalindrome(decVal)) decVal--;

        return Math.min(calculate(arr,incVal), calculate(arr,decVal));
    }


    public boolean checkPalindrome(int num) {
        int remainder, sum = 0, temp;
        temp = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return temp == sum;
    }

    public long calculate(int[] arr, int res) {
        long c = 0;
        for (int n: arr) c += Math.abs(n - res);
        return c;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String in = scan.nextLine(); 

        in = in.substring(1, in.length() - 1);

        String[] numStr = in.split(",");
        int[] nums = new int[numStr.length];
        for (int i = 0; i < numStr.length; i++) {
            nums[i] = Integer.parseInt(numStr[i].trim());
        }

        Solution sol = new Solution();
        long c = sol.findCost(nums);

        System.out.println(c);
        scan.close();
    }
}