import java.util.Arrays;
import java.util.Scanner;
class CloneSolution {
    public long calculateTotalCost(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        int median = nums[length/2];
        int increment = median;
        int decrement = median;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(calculateFinalCost(nums,increment), calculateFinalCost(nums,decrement));
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

    public long calculateFinalCost(int[] nums, int r) {
        long totalCost = 0;
        for (int number: nums) totalCost += Math.abs(number - r);
        return totalCost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputLine = scan.nextLine(); 

        inputLine = inputLine.substring(1, inputLine.length() - 1);

        String[] numStrings = inputLine.split(",");

        int[] values = new int[numStrings.length];
        for (int j = 0; j < numStrings.length; j++) {
            values[j] = Integer.parseInt(numStrings[j].trim());
        }

        CloneSolution cloneSolution = new CloneSolution();
        long result = cloneSolution.calculateTotalCost(values);

        System.out.println(result);
        scan.close();
    }
}