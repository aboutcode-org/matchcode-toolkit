import java.util.Arrays;
import java.util.Scanner;

class Duplicate {
    public static long findMinimumCost(int[] arr) {
        int count = arr.length;
        Arrays.sort(arr);
        int midpoint = arr[count/2];
        int increase = midpoint;
        int decrease = midpoint;

        while(!isPalindrome(increase)) increase++; 
        while(!isPalindrome(decrease)) decrease--;

        return Math.min(computeCost(arr, increase), computeCost(arr, decrease));
    }


    public static boolean isPalindrome(int x) {
        int rem, total = 0, temp;
        temp = x;
        while (x > 0) {
            rem = x % 10; 
            total = (total * 10) + rem;
            x = x / 10;
        }
        return temp == total;
    }

    public static long computeCost(int[] arr, int val) {
        long cost = 0;
        for (int ele: arr) cost += Math.abs(ele - val);
        return cost;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        str = str.substring(1, str.length() - 1);
        String[] strNumbers = str.split(",");
        int[] intNumbers = new int[strNumbers.length];
        
        for (int i = 0; i < strNumbers.length; i++) {
            intNumbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        Duplicate duplicateInstance = new Duplicate();
        long minCost = duplicateInstance.findMinimumCost(intNumbers);
        System.out.println(minCost);
        sc.close();
    }
}