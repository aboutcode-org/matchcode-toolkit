import java.util.Arrays;
import java.util.Scanner;
public class CostCalculator {
    public long findMinimumCost(int[] elements) {
        int size = elements.length;
        Arrays.sort(elements);
        int midElement = elements[size/2];
        int lowerElement = midElement;
        int higherElement = midElement;

        while(!checkPalindrome(lowerElement)) lowerElement--; 
        while(!checkPalindrome(higherElement)) higherElement++;

        return Math.min(computeCost(elements,lowerElement), computeCost(elements,higherElement));
    }

    public boolean checkPalindrome(int value) {
        int remainder, reverse = 0, initialVal;
        initialVal = value;
        while (value > 0) {
            remainder = value % 10; 
            reverse = (reverse * 10) + remainder;
            value = value / 10;
        }
        return initialVal == reverse;
    }

    public long computeCost(int[] elements, int num) {
        long cost = 0;
        for (int ele: elements) cost += Math.abs(ele - num);
        return cost;
    }
    public static void main(String[] argts) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine(); 

        // Remove the "[" and "]" from the str
        str = str.substring(1, str.length() - 1);

        // Split the str into elements
        String[] strNumbers = str.split(",");

        // Convert the elements array to an array of integers
        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        // Create an instance of CostCalculator and call findMinimumCost method
        CostCalculator calculator = new CostCalculator();
        long minCost = calculator.findMinimumCost(numbers);

        // Display the result
        System.out.println(minCost);
        sc.close();
    }
}