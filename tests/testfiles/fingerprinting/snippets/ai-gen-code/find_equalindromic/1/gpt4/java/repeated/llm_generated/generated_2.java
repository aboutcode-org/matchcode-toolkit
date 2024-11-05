import java.util.Arrays;
import java.util.Scanner;

public class MinCostCalculator {
    public long findMinCost(int[] totalNums) {
        int total = totalNums.length;
        Arrays.sort(totalNums);
        int central = totalNums[total/2];
        int increase = central;
        int decrease = central;

        while(!isPalindrome(increase)) increase++;
        while(!isPalindrome(decrease)) decrease--;

        return Math.min(computeCost(totalNums,increase), computeCost(totalNums,decrease));
    }

    public boolean isPalindrome(int numCheck) {
        int remainder, digitSum = 0, temporary;
        temporary = numCheck;
        while (numCheck > 0) {
            remainder = numCheck % 10;
            digitSum = (digitSum * 10) + remainder;
            numCheck = numCheck / 10;
        }
        return temporary == digitSum;
    }

    public long computeCost(int[] totalNums, int res) {
        long totalCost = 0;
        for (int numInTotal: totalNums) totalCost += Math.abs(numInTotal - res);
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String inputStr = userInput.nextLine();

        inputStr = inputStr.substring(1, inputStr.length() - 1);

        String[] numStr = inputStr.split(",");

        int[] numArr = new int[numStr.length];
        for (int idx = 0; idx < numStr.length; idx++) {
            numArr[idx] = Integer.parseInt(numStr[idx].trim());
        }

        MinCostCalculator ltCal = new MinCostCalculator();
        long finalCost = ltCal.findMinCost(numArr);

        System.out.println(finalCost);
        userInput.close();
    }
}