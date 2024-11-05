import java.util.Arrays;
import java.util.Scanner;

class Main {
    public long findMinimumCost(int[] elements) {
        int size = elements.length;
        Arrays.sort(elements);
        int median = elements[size/2];
        int increment = median;
        int decrement = median;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(computeCost(elements,increment), computeCost(elements,decrement));
    }


    public boolean checkPalindrome(int num) {
        int remainder, total = 0, backup;
        backup = num;
        while (num > 0) {
            remainder = num % 10; 
            total = (total * 10) + remainder;
            num = num / 10;
        }
        return backup == total;
    }

    public long computeCost(int[] elements, int num) {
        long cost = 0;
        for (int element: elements) cost += Math.abs(element - num);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine(); 

        userInput = userInput.substring(1, userInput.length() - 1);

        String[] strNumbers = userInput.split(",");

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