import java.util.Arrays;
import java.util.Scanner;

class Main {
    public long findMinimumCost(int[] array) {
        int size = array.length;
        Arrays.sort(array);
        int median = array[size/2];
        int increase = median;
        int decrease = median;

        while(!checkPalindrome(increase)) increase++; 
        while(!checkPalindrome(decrease)) decrease--;

        return Math.min(computeCost(array,increase), computeCost(array,decrease));
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
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); 

        input = input.substring(1, input.length() - 1);

        String[] strNumbers = input.split(",");

        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        Main main = new Main();
        long cost = main.findMinimumCost(numbers);

        System.out.println(cost);
        scanner.close();
    }
}