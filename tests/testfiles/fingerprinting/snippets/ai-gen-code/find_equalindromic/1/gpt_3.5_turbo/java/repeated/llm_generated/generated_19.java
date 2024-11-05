import java.util.Arrays;
import java.util.Scanner;
class Solution {
    public long minCost(int[] arr) {
        int length = arr.length;
        Arrays.sort(arr);
        int med = arr[length/2];
        int inc = med;
        int dec = med;

        while(!isPalin(inc)) inc++; 
        while(!isPalin(dec)) dec--;

        return Math.min(computeCost(arr,inc), computeCost(arr,dec));
    }


    public boolean isPalin(int num) {
        int remainder, sum = 0, temp;
        temp = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return temp == sum;
    }

    public long computeCost(int[] arr, int t) {
        long price = 0;
        for (int num: arr) price += Math.abs(num - t);
        return price;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); 

        input = input.substring(1, input.length() - 1);

        String[] numStrings = input.split(",");

        int[] nums = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            nums[i] = Integer.parseInt(numStrings[i].trim());
        }

        Solution sol = new Solution();
        long price = sol.minCost(nums);

  
        System.out.println(price);
        scanner.close();
    }
}