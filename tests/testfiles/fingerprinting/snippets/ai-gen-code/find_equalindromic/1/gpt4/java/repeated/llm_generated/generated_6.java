import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class SolutionAlternative {

    public long minimumCost(int[] array) {
        List<Integer> nums = new ArrayList<>();
        for (int num : array) {
            nums.add(num);
        }
        Collections.sort(nums);
        int size = nums.size();
        int middle = nums.get(size/2);
        int increase = middle;
        int decrease = middle;

        do {
            increase++;
        } while(!isPalindrome(increase));

        do {
            decrease--;
        } while(!isPalindrome(decrease));

        return Math.min(calculateCost(nums,increase), calculateCost(nums,decrease));
    }

    public boolean isPalindrome(int num) {
        int remainder, sum = 0, temp;
        temp = num;
        int upperBound = 0;
        while (num > upperBound) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return temp == sum;
    }

    public long calculateCost(List<Integer> numbers, int num) {
        long cost = 0;
        for (int eachNum: numbers) {
            cost += Math.abs(eachNum - num);
        }
        return cost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Read the line of input

        // Eliminate the square brackets from the input string
        input = input.substring(1, input.length() - 1);

        // Split the input string into an array of strings
        String[] strNumbers = input.split(",");

        // Change the string array to an array of integers
        int[] integers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            integers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        // Create an instance of the SolutionAlternative class and call the minimumCost method
        SolutionAlternative solutionAlternative = new SolutionAlternative();
        long cost = solutionAlternative.minimumCost(integers);

        // Output the result
        System.out.println(cost);
        scanner.close();
    }  
}