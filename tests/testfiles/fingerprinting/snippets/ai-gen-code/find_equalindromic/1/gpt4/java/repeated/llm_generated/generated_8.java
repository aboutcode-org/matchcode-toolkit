import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Solution {

    public long findMinimumCost(int[] values) {
        int size = values.length;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int num : values) arrayList.add(num);
        Collections.sort(arrayList);

        int mid = arrayList.get(size/2);
        int up = mid;
        int down = mid;

        while(!checkPalindrome(up)) up++; 
        while(!checkPalindrome(down)) down--;

        return Math.min(getCost(arrayList,up), getCost(arrayList,down));
    }

    public boolean checkPalindrome(int number) {
        int remainder, total = 0, temp;
        temp = number;
        while (number > 0) {
            remainder = number % 10; 
            total = (total * 10) + remainder;
            number = number / 10;
        }
        return temp == total;
    }

    public long getCost(ArrayList<Integer> arrayList, int reference) {
        long cost = 0;
        for (int num: arrayList) cost += Math.abs(num - reference);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        input = input.substring(1, input.length() - 1);

        // Split the input string into an array of strings
        String[] stringNums = input.split(",");

        // Convert the string array to an array of integers
        int[] numbers = new int[stringNums.length];
        for (int i = 0; i < stringNums.length; i++) {
            numbers[i] = Integer.parseInt(stringNums[i].trim());
        }

        // Create an instance of the Solution class and call the findMinimumCost method
        Solution solution = new Solution();
        long cost = solution.findMinimumCost(numbers);

        // Print the result
        System.out.println(cost);
        scanner.close();
    }
}