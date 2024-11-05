import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlternateSolution {

    public long findMinimumCost(List<Integer> elements) {
        Collections.sort(elements);
        int numberCount = elements.size();
        int midValue = elements.get(numberCount/2);
        int increment = midValue;
        int decrement = midValue;

        while(!checkPalindrome(increment)) increment++; 
        while(!checkPalindrome(decrement)) decrement--;

        return Math.min(computeCost(elements, increment), computeCost(elements, decrement));
    }


    public boolean checkPalindrome(int number) {
        int remainder, sum = 0, original;
        original = number;
        while (number > 0) {
            remainder = number % 10; 
            sum = (sum * 10) + remainder;
            number = number / 10;
        }
        return original == sum;
    }

    public long computeCost(List<Integer> elements, int number) {
        long cost = 0;
        for (int element: elements) cost += Math.abs(element - number);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scannerObject = new Scanner(System.in);
        String inputLine = scannerObject.nextLine(); 

        inputLine = inputLine.substring(1, inputLine.length() - 1);

        String[] separatedNumbers = inputLine.split(",");

        List<Integer> numberList = new ArrayList<>();
        for (String number : separatedNumbers) {
            numberList.add(Integer.parseInt(number.trim()));
        }

        AlternateSolution alternateSolution = new AlternateSolution();
        long finalCost = alternateSolution.findMinimumCost(numberList);

        System.out.println(finalCost);
        scannerObject.close();  
    }
}