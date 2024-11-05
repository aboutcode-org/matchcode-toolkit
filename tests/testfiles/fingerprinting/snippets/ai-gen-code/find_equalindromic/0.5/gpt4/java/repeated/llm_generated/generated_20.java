import java.util.Scanner;
import java.util.Arrays;

class Main {
    public long getMinimumCost(int[] array) {
        int length = array.length;
        Arrays.sort(array);
        int middle = array[length/2];
        int increase = middle;
        int decrease = middle;

        while(!checkPalindrome(increase)) increase++; 
        while(!checkPalindrome(decrease)) decrease--;

        return Math.min(calculateTotalCost(array, increase), calculateTotalCost(array, decrease));
    }

    public boolean checkPalindrome(int num) {
        int remainder, sum = 0, temp;
        temp = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return temp == sum;
    }

    public long calculateTotalCost(int[] array, int num) {
        long totalCost = 0;
        for (int i: array) totalCost += Math.abs(i - num);
        return totalCost;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine(); 

        str = str.substring(1, str.length() - 1);

        String[] strNumbers = str.split(",");

        int[] intNumbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            intNumbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        Main main = new Main();
        long totalCost = main.getMinimumCost(intNumbers);

        System.out.println(totalCost);
        scan.close();
    }
}