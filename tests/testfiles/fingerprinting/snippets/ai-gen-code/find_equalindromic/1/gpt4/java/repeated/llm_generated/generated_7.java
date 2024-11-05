import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public long getMinimumCost(List<Integer> numbers) {
        int count = numbers.size();
        Collections.sort(numbers);
        int midNum = numbers.get(count/2);
        int increment = midNum;
        int decrement = midNum;

        while(!isPalindrome(increment)) increment++; 
        while(!isPalindrome(decrement)) decrement--;

        return Math.min(findCost(numbers,increment), findCost(numbers,decrement));
    }


    public boolean isPalindrome(int value) {
        int lastDigit, sum = 0, originalValue;
        originalValue = value;
        while (value > 0) {
            lastDigit = value % 10; 
            sum = (sum * 10) + lastDigit;
            value = value / 10;
        }
        return originalValue == sum;
    }

    public long findCost(List<Integer> numbersList, int number) {
        long totalCost = 0;
        for (Integer num: numbersList) totalCost += Math.abs(num - number);
        return totalCost;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine(); 

        inputString = inputString.substring(1, inputString.length() - 1);

        String[] nums = inputString.split(",");

        List<Integer> numList = new ArrayList<>();
        for (String num : nums) {
            numList.add(Integer.parseInt(num.trim()));
        }

        Main mainObj = new Main();
        long minCost = mainObj.getMinimumCost(numList);

        System.out.println(minCost);
        sc.close();
    }
}