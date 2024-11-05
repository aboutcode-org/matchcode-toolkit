import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.Arrays;

class CostCalculator {
    public long calculateMinimumCost(int[] inputNumbers) {
        int numSize = inputNumbers.length;
        Arrays.sort(inputNumbers);
        int midElement = inputNumbers[numSize/2];
        int movingUp = midElement;
        int movingDown = midElement;

        while(!checkPalindromicity(movingUp)) movingUp++; 
        while(!checkPalindromicity(movingDown)) movingDown--;

        return Math.min(getCost(inputNumbers,movingUp), getCost(inputNumbers,movingDown));
    }


    public boolean checkPalindromicity(int inp) {
        int reminder, sumValue = 0, tempCheck;
        tempCheck = inp;
        while (inp > 0) {
            reminder = inp % 10; 
            sumValue = (sumValue * 10) + reminder;
            inp = inp / 10;
        }
        return tempCheck == sumValue;
    }

    public long getCost(int[] inputArr, int checkNum) {
        long calculatedCost = 0;
        for (int num: inputArr) calculatedCost += Math.abs(num - checkNum);
        return calculatedCost;
    }
    public static void main(String[] inputArgs) {
        Scanner scanObject = new Scanner(System.in);
        String inputString = scanObject.nextLine(); 

        inputString = inputString.substring(1, inputString.length() - 1);

        String[] splittedNumbers = inputString.split(",");

        int[] parsedNumbers = new int[splittedNumbers.length];
        IntStream.range(0, splittedNumbers.length).
                forEach(i -> parsedNumbers[i] = Integer.parseInt(splittedNumbers[i].trim()));

        CostCalculator costCalculator = new CostCalculator();
        long finalCost = costCalculator.calculateMinimumCost(parsedNumbers);

        System.out.println(finalCost);
        scanObject.close();
    }
}