import java.util.Arrays;
import java.util.Scanner;

class CostCalculator {
    public long calculateMinimumCost(int[] numbers) {
        int size = numbers.length;
        Arrays.sort(numbers);
        int mid = numbers[size/2];
        int increase = mid;
        int decrease = mid;

        while(!isPalindrome(increase)) increase++; 
        while(!isPalindrome(decrease)) decrease--;

        return Math.min(getCost(numbers,increase), getCost(numbers,decrease));
    }


    public boolean isPalindrome(int num) {
        int remainder, sum = 0, temporary;
        temporary = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return temporary == sum;
    }

    public long getCost(int[] numbers, int r) {
        long cost = 0;
        for (int num: numbers) cost += Math.abs(num - r);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        input = input.substring(1, input.length() - 1);

        String[] numStrings = input.split(",");

        int[] nums = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            nums[i] = Integer.parseInt(numStrings[i].trim());
        }

        CostCalculator costCalculator = new CostCalculator();
        long cost = costCalculator.calculateMinimumCost(nums);

        System.out.println(cost);
        scanner.close();
    }
}