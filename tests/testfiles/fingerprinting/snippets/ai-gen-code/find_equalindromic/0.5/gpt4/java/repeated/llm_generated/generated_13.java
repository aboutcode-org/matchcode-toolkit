import java.util.Scanner;
import java.util.Arrays;

public class PalindromeCost {
    public long findMinCost(int[] array) {
        int size = array.length;
        Arrays.sort(array);
        int mid = array[size/2];
        int inc = mid;
        int dec = mid;

        while(!checkPalindrome(inc)) inc++; 
        while(!checkPalindrome(dec)) dec--;

        return Math.min(computeCost(array,inc), computeCost(array,dec));
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

    public long computeCost(int[] array, int num) {
        long cost = 0;
        for (int i: array) cost += Math.abs(i - num);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine(); 

        line = line.substring(1, line.length() - 1);

        String[] strNumbers = line.split(",");

        int[] intNumbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            intNumbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        PalindromeCost pc = new PalindromeCost();
        long minCost = pc.findMinCost(intNumbers);

        System.out.println(minCost);
        scan.close();
    }
}