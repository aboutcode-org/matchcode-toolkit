import java.util.Arrays;
import java.util.Scanner;

class Main {
    public long getMinimumCost(int[] array) {
        int size = array.length;
        Arrays.sort(array);
        int median = array[size/2];
        int increment = median;
        int decrement = median;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(findCost(array,increment), findCost(array,decrement));
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

    public long findCost(int[] array, int num) {
        long cost = 0;
        for (int i: array) cost += Math.abs(i - num);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine(); 

        input = input.substring(1, input.length() - 1);

        String[] strNumbers = input.split(",");

        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        Main main = new Main();
        long cost = main.getMinimumCost(numbers);

        System.out.println(cost);
        scan.close();
    }
}