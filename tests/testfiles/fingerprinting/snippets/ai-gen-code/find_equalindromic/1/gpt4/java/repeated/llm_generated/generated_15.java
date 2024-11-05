import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PalindromeCost {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); 

        input = input.substring(1, input.length() - 1);
        String[] arr = input.split(",");

        List<Integer> list = new ArrayList<>();
        for (String s : arr) {
            list.add(Integer.parseInt(s.trim()));
        }

        PalindromeCost obj = new PalindromeCost();
        long cost = obj.getMinimumCost(list);

        System.out.println(cost);
        scanner.close();
    }

    public long getMinimumCost(List<Integer> list) {
        Collections.sort(list);
        int n = list.size();
        int median = list.get(n/2);
        int increase = median;
        int decrease = median;

        while(!checkPalindrome(increase)) 
            increase++; 
        while(!checkPalindrome(decrease)) 
            decrease--;

        return Math.min(costCalculation(list, increase), costCalculation(list, decrease));
    }

    public boolean checkPalindrome(int num) {
        int rev=0, remainder, original; 

        original = num; 

        while(num != 0) {
            remainder=num%10;      
            rev=rev*10+remainder;      
            num=num/10;    
        }    
        return original==rev; 
    }

    public long costCalculation(List<Integer> list, int r) {
        long totalCost = 0;
        for (int num: list) 
            totalCost += Math.abs(num - r);
        return totalCost;
    }
}