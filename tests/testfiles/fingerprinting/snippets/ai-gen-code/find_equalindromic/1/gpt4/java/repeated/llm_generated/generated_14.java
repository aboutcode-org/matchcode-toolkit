import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Scanner;

class CloneSolution {
    public long findMinCost(List<Integer> list) {
        int size = list.size();
        Collections.sort(list);
        int mid = list.get(size/2);
        int increase = mid;
        int decrease = mid;

        while(!checkPalindrome(increase)) increase++; 
        while(!checkPalindrome(decrease)) decrease--;

        return Math.min(computeCost(list,increase), computeCost(list, decrease));
    }

    public boolean checkPalindrome(int num) {
        int remainder, total = 0, backup;
        backup = num;

        while (num > 0) {
            remainder = num % 10; 
            total = (total * 10) + remainder;
            num = num / 10;
        }
        return backup == total;
    }

    public long computeCost(List<Integer> list, int result) {
        long cost = 0;
        for (int num: list) cost += Math.abs(num - result);
        return cost;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine(); // Read the input line

        // Remove the square brackets from the input string
        inputStr = inputStr.substring(1, inputStr.length() - 1);

        // Split the input string into an array of strings
        String[] numStrings = inputStr.split(",");

        // Convert the string array to a List of Integers
        List<Integer> numList = new ArrayList<>();
        for(String s : numStrings) {
            numList.add(Integer.parseInt(s.trim()));
        }
        
        // Create a  instance of the CloneSolution class and call the findMinCost method
        CloneSolution clone = new CloneSolution();
        long minCost = clone.findMinCost(numList);

        // Print the result
        System.out.println(minCost);
        sc.close();
    }
}
