import java.util.Scanner;
import java.util.Arrays;

public class SolutionClone {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        inputString = inputString.substring(1, inputString.length() - 1);

        String[] numbersAsStrings = inputString.split(",");
        int[] convertedNumbers = new int[numbersAsStrings.length];
        for (int index = 0; index < numbersAsStrings.length; index++) {
            convertedNumbers[index] = Integer.parseInt(numbersAsStrings[index].trim());
        }

        SolutionClone solutionSignificantClone = new SolutionClone();
        long resultantCost = solutionSignificantClone.computeMinimumCost(convertedNumbers);

        System.out.println(resultantCost);
        scanner.close();
    }

    long computeMinimumCost(int[] numArr) {
        int arrayLength = numArr.length;
        Arrays.sort(numArr);
        int middleFigure = numArr[arrayLength/2];
        int augment = middleFigure;
        int diminish = middleFigure;

        while(!isPalindrome(augment)) augment++; 
        while(!isPalindrome(diminish)) diminish--;

        return Math.min(costComputation(numArr,augment), costComputation(numArr,diminish));
    }

    boolean isPalindrome(int number) {
        int remainder, sum = 0;
        int tempVar = number;
        while (number > 0) {
            remainder = number % 10; 
            sum = (sum * 10) + remainder;
            number = number / 10;
        }
        return tempVar == sum;
    }

    long costComputation(int[] numArr, int someNumber) {
        long computingCost = 0;
        for (int digit: numArr) computingCost += Math.abs(digit - someNumber);
        return computingCost;
    }
}