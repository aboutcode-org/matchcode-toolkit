import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Answer {

    public long getMinimumCost(List<Integer> nums) {
        int size = nums.size();
        Collections.sort(nums);
        int mid = nums.get(size/2);
        int increased = mid;
        int decreased = mid;

        while(!testPal(increased)) increased++;
        while(!testPal(decreased)) decreased--;

        return Math.min(findCost(nums,increased), findCost(nums,decreased));
    }


    public boolean testPal(int num) {
        int remainder, total = 0, backup;
        backup = num;
        while (num > 0) {
            remainder = num % 10;
            total = (total * 10) + remainder;
            num = num / 10;
        }
        return backup == total;
    }

    public long findCost(List<Integer> nums, int changed) {
        long overallCost = 0;
        for (int x: nums) overallCost += Math.abs(x - changed);
        return overallCost;
    }
    public static void main(String[] args) {
        Scanner scannerObject = new Scanner(System.in);
        String inputLine = scannerObject.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        inputLine = inputLine.substring(1, inputLine.length() - 1);

        // Split the input string into an array of strings
        String[] stringArray = inputLine.split(",");

        // Convert the string array to List of integers
        List<Integer> numberList = new ArrayList<>();
        for (String s : stringArray) {
            numberList.add(Integer.parseInt(s.trim()));
        }

        // Create an instance of the Solution class and call the minimumCost method
        Answer obj = new Answer();
        long minCost = obj.getMinimumCost(numberList);

        // Print the result
        System.out.println(minCost);
        scannerObject.close();
    }
}