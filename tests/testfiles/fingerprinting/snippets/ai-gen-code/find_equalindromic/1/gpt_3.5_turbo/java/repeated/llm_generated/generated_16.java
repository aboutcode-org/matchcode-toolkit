import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long findMinCost(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        int mid = arr[len/2];
        int inc = mid;
        int dec = mid;

        while(!isPalindrome(inc)) inc++; 
        while(!isPalindrome(dec)) dec--;

        return Math.min(calculateTotalCost(arr,inc), calculateTotalCost(arr,dec));
    }

    public boolean isPalindrome(int num) {
        int rev, sum = 0, temp;
        temp = num;
        while (num > 0) {
            rev = num % 10; 
            sum = (sum * 10) + rev;
            num = num / 10;
        }
        return temp == sum;
    }

    public long calculateTotalCost(int[] arr, int val) {
        long result = 0;
        for (int n: arr) result += Math.abs(n - val);
        return result;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputString = scan.nextLine(); 
        inputString = inputString.substring(1, inputString.length() - 1); 
        String[] numStrings = inputString.split(",");
        int[] numArr = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            numArr[i] = Integer.parseInt(numStrings[i].trim());
        }
        Solution sol = new Solution();
        long minCost = sol.findMinCost(numArr);
        System.out.println(minCost);
        scan.close();
    }
}