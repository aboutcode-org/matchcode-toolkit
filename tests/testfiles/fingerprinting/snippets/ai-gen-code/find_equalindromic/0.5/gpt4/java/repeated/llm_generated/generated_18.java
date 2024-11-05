import java.util.Arrays;
import java.util.Scanner;

public class MinCostCalculator {
    public long getMinimumCost(int[] inputNums) {
        int length = inputNums.length;
        Arrays.sort(inputNums);
        int mid = inputNums[length/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(computeCost(inputNums,increment), computeCost(inputNums,decrement));
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

    public long computeCost(int[] inputNums, int num) {
        long totalCost = 0;
        for (int n: inputNums) totalCost += Math.abs(n - num);
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputData = scan.nextLine(); 

        inputData = inputData.substring(1, inputData.length() - 1);
        String[] stringNums = inputData.split(",");

        int[] nums = new int[stringNums.length];
        for (int i = 0; i < stringNums.length; i++) {
            nums[i] = Integer.parseInt(stringNums[i].trim());
        }

        MinCostCalculator minCostCalculator = new MinCostCalculator();
        long minCost = minCostCalculator.getMinimumCost(nums);

        System.out.println(minCost);
        scan.close();
    }
}