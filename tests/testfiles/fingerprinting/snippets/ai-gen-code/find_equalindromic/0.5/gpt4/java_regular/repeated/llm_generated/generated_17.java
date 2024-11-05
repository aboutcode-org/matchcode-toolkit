import java.util.Arrays;
import java.util.Scanner;

class Main {
    public long findMinimumCost(int[] array) {
        int size = array.length;
        Arrays.sort(array);
        int median = array[size/2];
        int increase = median;
        int decrease = median;

        while(!isPalindrome(increase)) increase++; 
        while(!isPalindrome(decrease)) decrease--;

        return Math.min(getCost(array,increase), getCost(array,decrease));
    }

    public boolean isPalindrome(int num) {
        int remainder, sum = 0, temp;
        temp = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return temp == sum;
    }

    public long getCost(int[] array, int ref) {
        long cost = 0;
        for (int num: array) cost += Math.abs(num - ref);
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
        long cost = main.findMinimumCost(numbers);

        System.out.println(cost);
        scan.close();
    }
}