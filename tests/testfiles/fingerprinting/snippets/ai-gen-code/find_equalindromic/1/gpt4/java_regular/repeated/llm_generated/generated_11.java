import java.util.*;
class FindCost {
    public long calculateMinCost(int[] values) {
        int n = values.length;
        Arrays.sort(values);
        int mid = values[n/2];
        int increase = mid;
        int decrease = mid;

        while(!checkPalindrome(increase)) increase++; 
        while(!checkPalindrome(decrease)) decrease--;

        return Math.min(findCost(values,increase), findCost(values,decrease));
    }


    public boolean checkPalindrome(int num) {
        int rem, sum = 0, tmp;
        tmp = num;
        while (num > 0) {
            rem = num % 10; 
            sum = (sum * 10) + rem;
            num = num / 10;
        }
        return tmp == sum;
    }

    public long findCost(int[] values, int res) {
        long cost = 0;
        for (int val: values) cost += Math.abs(val - res);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        userInput = userInput.substring(1, userInput.length() - 1);

        // Split the input string into an array of strings
        String[] strNumbers = userInput.split(",");

        // Convert the string array to an array of integers
        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        // Create an instance of the Solution class and call the calculateMinCost method
        FindCost findCost = new FindCost();
        long cost = findCost.calculateMinCost(numbers);

        // Print the result
        System.out.println(cost);
        scanner.close();
    }
}