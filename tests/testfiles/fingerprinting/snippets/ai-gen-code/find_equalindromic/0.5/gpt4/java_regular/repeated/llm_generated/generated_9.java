import java.util.Arrays;
import java.util.Scanner;

class PalindromeCost {
    public long findMinCost(int[] values) {
        int size = values.length;
        Arrays.sort(values);
        int mid = values[size/2];
        int increase = mid;
        int decrease = mid;

        while(!checkPalindrome(increase)) increase++; 
        while(!checkPalindrome(decrease)) decrease--;

        return Math.min(computeCost(values,increase), computeCost(values,decrease));
    }


    public boolean checkPalindrome(int number) {
        int remainder, sum = 0, temp;
        temp = number;
        while (number > 0) {
            remainder = number % 10; 
            sum = (sum * 10) + remainder;
            number = number / 10;
        }
        return temp == sum;
    }

    public long computeCost(int[] values, int r) {
        long cost = 0;
        for (int value: values) cost += Math.abs(value - r);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputData = scan.nextLine(); 

        inputData = inputData.substring(1, inputData.length() - 1);

        String[] numStrings = inputData.split(",");

        int[] nums = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            nums[i] = Integer.parseInt(numStrings[i].trim());
        }

        PalindromeCost palindromeCost = new PalindromeCost();
        long minCost = palindromeCost.findMinCost(nums);

        System.out.println(minCost);
        scan.close();
    }
}