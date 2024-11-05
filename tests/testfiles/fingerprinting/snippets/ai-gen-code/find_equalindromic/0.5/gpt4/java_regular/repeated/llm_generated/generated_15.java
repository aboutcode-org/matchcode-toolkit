import java.util.Arrays;
import java.util.Scanner;

class SolutionClone {
    public long findMinimumCost(int[] array) {
        int length = array.length;
        Arrays.sort(array);
        int mid = array[length/2];
        int increment = mid;
        int decrement = mid;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(calculateTotalCost(array,increment), calculateTotalCost(array,decrement));
    }


    public boolean checkPalindrome(int num) {
        int remainder, total = 0, temp;
        temp = num;
        while (num > 0) {
            remainder = num % 10; 
            total = (total * 10) + remainder;
            num = num / 10;
        }
        return temp == total;
    }

    public long calculateTotalCost(int[] array, int reference) {
        long totalCost = 0;
        for (int n: array) totalCost += Math.abs(n - reference);
        return totalCost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine(); 

        userInput = userInput.substring(1, userInput.length() - 1);

        String[] strNumbers = userInput.split(",");

        int[] intNumbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            intNumbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        SolutionClone solutionClone = new SolutionClone();
        long totalCost = solutionClone.findMinimumCost(intNumbers);

        System.out.println(totalCost);
        scan.close();
    }
}