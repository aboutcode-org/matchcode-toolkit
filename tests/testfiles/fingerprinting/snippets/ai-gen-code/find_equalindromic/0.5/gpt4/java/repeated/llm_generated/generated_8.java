import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class SolutionClone {
    public long minCost(int[] numbers) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int num : numbers) nums.add(num);
        Collections.sort(nums);
        int numLength = nums.size();
        int mid = nums.get(numLength/2);
        int incNum = mid;
        int decNum = mid;

        while(!checkPalindrome(incNum)) incNum++;
        while(!checkPalindrome(decNum)) decNum--;

        return Math.min(computeCost(nums,incNum), computeCost(nums,decNum));
    }

    public boolean checkPalindrome(int num) {
        int remainder, total = 0, tempNum;
        tempNum = num;
        while (num > 0) {
            remainder = num % 10;
            total = (total * 10) + remainder;
            num = num / 10;
        }
        return tempNum == total;
    }

    public long computeCost(ArrayList<Integer> nums, int targetNum) {
        long costVal = 0;
        for (int num: nums) costVal += Math.abs(num - targetNum);
        return costVal;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String in = scan.nextLine();

        in = in.substring(1, in.length() - 1);
        String[] numStrings = in.split(",");

        int[] nums = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            nums[i] = Integer.parseInt(numStrings[i].trim());
        }

        SolutionClone solutionClone = new SolutionClone();
        long costVal = solutionClone.minCost(nums);

        System.out.println(costVal);
        scan.close();
    }
}