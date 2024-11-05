import java.util.Arrays;
import java.util.Scanner;

public class SolutionClone {
    public long findMinimumCost(int[] numbers) {
        int size = numbers.length;
        Arrays.sort(numbers);
        int mid = numbers[size/2];
        int increase = mid;
        int decrease = mid;

        while(!checkPalindrome(increase)) increase++; 
        while(!checkPalindrome(decrease)) decrease--;

        return Math.min(getCost(numbers,increase), getCost(numbers,decrease));
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

    public long getCost(int[] numbers, int r) {
        long cost = 0;
        for (int num: numbers) cost += Math.abs(num - r);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine(); 

        userInput = userInput.substring(1, userInput.length() - 1);

        String[] strNumbers = userInput.split(",");

        int[] nums = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            nums[i] = Integer.parseInt(strNumbers[i].trim());
        }

        SolutionClone solutionClone = new SolutionClone();
        long minCost = solutionClone.findMinimumCost(nums);

        System.out.println(minCost);
        scan.close();
    }
}