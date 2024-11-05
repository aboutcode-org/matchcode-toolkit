import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        input = input.substring(1, input.length() - 1);
        String[] strNumbers = input.split(",");

        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        MinCostCalculator minCostCalculator = new MinCostCalculator();
        long cost = minCostCalculator.getMinimumCost(numbers);

        System.out.println(cost);
        in.close();
    }
}

class MinCostCalculator {
    public long getMinimumCost(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int mid = nums[n/2];
        int increase = mid;
        int decrease = mid;

        while(!isPalindrome(increase)) increase++; 
        while(!isPalindrome(decrease)) decrease--;

        return Math.min(getCost(nums,increase), getCost(nums,decrease));
    }

    public boolean isPalindrome(int n) {
        int remainder, sum = 0, temp;
        temp = n;
        while (n > 0) {
            remainder = n % 10; 
            sum = (sum * 10) + remainder;
            n = n / 10;
        }
        return temp == sum;
    }

    public long getCost(int[] nums, int r) {
        long cost = 0;
        for (int num : nums) cost += Math.abs(num - r);
        return cost;
    }
}