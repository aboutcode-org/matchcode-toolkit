import java.util.Arrays;
import java.util.Scanner;
class Solution {
    public long calculateMinimumCost(int[] numArr) {
        int len = numArr.length;
        Arrays.sort(numArr);
        int mid = numArr[len/2];
        int incr = mid;
        int decr = mid;

        while(!checkIfPal(incr)) incr++; 
        while(!checkIfPal(decr)) decr--;

        return Math.min(computeCost(numArr,incr), computeCost(numArr,decr));
    }

    public boolean checkIfPal(int num) {
        int remainder, sum = 0, temporary;
        temporary = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return temporary == sum;
    }

    public long computeCost(int[] numArr, int res) {
        long totalCost = 0;
        for (int x: numArr) totalCost += Math.abs(x - res);
        return totalCost;
    }

    public static void main(String[] arguments) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine(); 

        userInput = userInput.substring(1, userInput.length() - 1);

        String[] stringsArr = userInput.split(",");

        int[] intArr = new int[stringsArr.length];
        for (int j = 0; j < stringsArr.length; j++) {
            intArr[j] = Integer.parseInt(stringsArr[j].trim());
        }

        Solution solver = new Solution();
        long result = solver.calculateMinimumCost(intArr);

        System.out.println(result);
        scanner.close();
    }
}