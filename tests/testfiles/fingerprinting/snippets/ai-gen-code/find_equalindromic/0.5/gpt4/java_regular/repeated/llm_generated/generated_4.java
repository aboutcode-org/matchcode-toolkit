import java.util.Arrays;
import java.util.Scanner;

class CostCalculator {
    public long computeMinimumCost(int[] array) {
        int size = array.length;
        Arrays.sort(array);
        int median = array[size/2];
        int increment = median;
        int decrement = median;

        while(!isPalindrome(increment)) increment++; 
        while(!isPalindrome(decrement)) decrement--;

        return Math.min(findCost(array,increment), findCost(array,decrement));
    }


    public boolean isPalindrome(int num) {
        int remainder, total = 0, temporary;
        temporary = num;
        while (num > 0) {
            remainder = num % 10; 
            total = (total * 10) + remainder;
            num = num / 10;
        }
        return temporary == total;
    }

    public long findCost(int[] array, int r) {
        long cost = 0;
        for (int num: array) cost += Math.abs(num - r);
        return cost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputLine = scan.nextLine(); 

        inputLine = inputLine.substring(1, inputLine.length() - 1);

        String[] stringNumbers = inputLine.split(",");

        int[] numbers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i].trim());
        }

        CostCalculator costCalculator = new CostCalculator();
        long cost = costCalculator.computeMinimumCost(numbers);

        System.out.println(cost);
        scan.close();
    }
}