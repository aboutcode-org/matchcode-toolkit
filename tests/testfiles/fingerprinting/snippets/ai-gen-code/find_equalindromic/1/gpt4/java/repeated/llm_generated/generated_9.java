import java.util.Scanner;
import java.util.Arrays;

public class MinCost {
    public static void main(String args[]) {
        Scanner myScanner = new Scanner(System.in);
        String str = myScanner.nextLine();

        str = str.substring(1, str.length() - 1);
        String[] strNums = str.split(",");

        int[] intNums = new int[strNums.length];
        for (int i = 0; i < strNums.length; i++) {
            intNums[i] = Integer.parseInt(strNums[i].trim());
        }

        MinCost myCost = new MinCost();
        long finalCost = myCost.findMinCost(intNums);
        System.out.println(finalCost);

        myScanner.close();
    }

    public long findMinCost(int[] givenNums) {
        int size = givenNums.length;
        Arrays.sort(givenNums);
        int mid = givenNums[size / 2];
        int incMid = mid;
        int decMid = mid;

        while (!isPalindrome(incMid)) incMid++;
        while (!isPalindrome(decMid)) decMid--;

        return Math.min(costCalculation(givenNums, incMid), costCalculation(givenNums, decMid));
    }

    public long costCalculation(int[] givenNums, int r) {
        long totalCost = 0;
        for (int t : givenNums) totalCost += Math.abs(t - r);
        return totalCost;
    }

    public boolean isPalindrome(int value) {
        int remainder, sum = 0, origValue = value;
        while (value > 0) {
            remainder = value % 10;
            sum = (sum * 10) + remainder;
            value = value / 10;
        }

        return origValue == sum;
    }
}
