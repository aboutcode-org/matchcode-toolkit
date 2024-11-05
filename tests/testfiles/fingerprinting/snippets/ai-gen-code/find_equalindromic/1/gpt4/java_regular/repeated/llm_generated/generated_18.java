import java.util.Arrays;
import java.util.Scanner;

class ImplementSolution {
    public long computeMinimumCost(int[] vals) {
        int len = vals.length;
        Arrays.sort(vals);
        int median = vals[len / 2];
        int increment = median;
        int decrement = median;

        while (!checkPalindrome(increment)) increment++;
        while (!checkPalindrome(decrement)) decrement--;

        return Math.min(getCost(vals, increment), getCost(vals, decrement));
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

    public long getCost(int[] vals, int value) {
        long cost = 0;
        for (int num : vals) cost += Math.abs(num - value);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scanInput = new Scanner(System.in);
        String inputData = scanInput.nextLine(); 

        inputData = inputData.substring(1, inputData.length() - 1);

        String[] strNumbers = inputData.split(",");

        int[] intNumbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            intNumbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        ImplementSolution newSolution = new ImplementSolution();
        long resultantCost = newSolution.computeMinimumCost(intNumbers);

        System.out.println(resultantCost);
        scanInput.close();
    }
}