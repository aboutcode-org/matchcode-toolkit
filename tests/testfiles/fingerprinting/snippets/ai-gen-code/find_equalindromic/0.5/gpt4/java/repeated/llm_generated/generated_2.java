import java.util.Scanner;
import java.util.Arrays;

public class MinCost {

    public static long getMinCost(int[] numbers) {
        int length = numbers.length;
        Arrays.sort(numbers);
        int median = numbers[length/2];
        int increase = median;
        int decrease = median;

        while(!checkPalindrome(increase)) increase++; 
        while(!checkPalindrome(decrease)) decrease--;

        return Math.min(findCost(numbers,increase), findCost(numbers,decrease));
    }


    public static boolean checkPalindrome(int num) {
        int remainder, sum = 0, temp;
        temp = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return temp == sum;
    }

    public static long findCost(int[] numbers, int r) {
        long totalCost = 0;
        for (int num: numbers) totalCost += Math.abs(num - r);
        return totalCost;
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputLine = scan.nextLine(); 

        inputLine = inputLine.substring(1, inputLine.length() - 1);
        String[] strNums = inputLine.split(",");
        int[] nums = new int[strNums.length];
        for (int i = 0; i < strNums.length; i++) {
            nums[i] = Integer.parseInt(strNums[i].trim());
        }

        long minCost = getMinCost(nums);
        System.out.println(minCost);
        scan.close();
    }
}