import java.util.Arrays;
import java.util.Scanner;

public class CloneSolution {
    public long calcMinCost(int[] values) {
        int len = values.length;
        Arrays.sort(values);
        int mid = values[len / 2];
        int inc = mid;
        int dec = mid;

        while (!isPalindrome(inc)) inc++;
        while (!isPalindrome(dec)) dec--;

        return Math.min(computeCost(values, inc), computeCost(values, dec));
    }

    public boolean isPalindrome(int num) {
        int remainder, sum = 0, temp;
        temp = num;
        while (num > 0) {
            remainder = num % 10;
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return temp == sum;
    }

    public long computeCost(int[] values, int req) {
        long sumCost = 0;
        for (int val : values) sumCost += Math.abs(val - req);
        return sumCost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();

        in = in.substring(1, in.length() - 1);

        String[] numsAsStr = in.split(",");

        int[] numbers = new int[numsAsStr.length];
        for (int i = 0; i < numsAsStr.length; i++) {
            numbers[i] = Integer.parseInt(numsAsStr[i].trim());
        }

        CloneSolution cloneSolution = new CloneSolution();
        long finalCost = cloneSolution.calcMinCost(numbers);

        System.out.println(finalCost);
        sc.close();
    }
}