import java.util.Scanner;
import java.util.Arrays;

public class CloneSolution {
    public long getMinCost(int[] array) {
        int size = array.length;
        Arrays.sort(array);
        int mid = array[size/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(findCost(array,increment), findCost(array,decrement));
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

    public long findCost(int[] array, int r) {
        long cost = 0;
        for (int n: array) cost += Math.abs(n - r);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); 

        input = input.substring(1, input.length() - 1);

        String[] strNumbers = input.split(",");

        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        CloneSolution cloneSolution = new CloneSolution();
        long cost = cloneSolution.getMinCost(numbers);

        System.out.println(cost);
        scanner.close();
    }
}