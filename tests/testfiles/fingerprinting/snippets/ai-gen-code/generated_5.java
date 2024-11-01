import java.util.Arrays;
import java.util.Scanner;

class Main {
    public long findMinimumCost(int[] array) {
        int size = array.length;
        Arrays.sort(array);
        int median = array[size/2];
        int increment = median;
        int decrement = median;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(computeCost(array,increment), computeCost(array,decrement));
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

    public long computeCost(int[] array, int value) {
        long cost = 0;
        for (int num: array) cost += Math.abs(num - value);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine(); 

        userInput = userInput.substring(1, userInput.length() - 1);

        String[] strNumbers = userInput.split(",");

        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        Main main = new Main();
        long cost = main.findMinimumCost(numbers);

        System.out.println(cost);
        scan.close();
    }
}