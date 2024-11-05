import java.util.Scanner;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numbersString = scanner.nextLine();

        numbersString = numbersString.substring(1, numbersString.length() - 1);

        String[] stringArray = numbersString.split(",");

        int[] nums = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            nums[i] = Integer.parseInt(stringArray[i].trim());
        }

        Main m = new Main();
        long result = m.findMinimumCost(nums);

        System.out.println(result);
        scanner.close();
    }

    public long findMinimumCost(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        int mid = nums[length / 2];
        int increase = mid;
        int decrease = mid;

        while(!checkPalindrome(increase)) increase++;
        while(!checkPalindrome(decrease)) decrease--;

        return Math.min(calculateSum(nums,increase), calculateSum(nums,decrease));
    }

    public boolean checkPalindrome(int number) {
        int remainder, sum = 0, temp;
        temp = number;
        while (number > 0) {
            remainder = number % 10;
            sum = (sum * 10) + remainder;
            number = number / 10;
        }
        return temp == sum;
    }

    public long calculateSum(int[] nums, int palindrome) {
        long total = 0;
        for (int num: nums) total += Math.abs(num - palindrome);
        return total;
    }
}