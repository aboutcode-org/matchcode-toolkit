import java.util.Arrays; 
import java.util.Scanner;
class SolutionClone {
    public long getMinimumCost(int[] array) {
        int length = array.length; 
        Arrays.sort(array); 
        int mid = array[length/2];
        int increase = mid; 
        int decrease = mid;
        while(!checkPalindrome(increase)) increase++; 
        while(!checkPalindrome(decrease)) decrease--;
        return Math.min(getCost(array,increase), getCost(array,decrease));
    }

    public boolean checkPalindrome(int num) {
        int remainder, total = 0, temp; 
        temp = num;
        while (num > 0) {
            remainder = num % 10; 
            total = (total * 10) + remainder; 
            num = num / 10;
        }
        return temp == total;
    }

    public long getCost(int[] array, int res) {
        long sum = 0; 
        for (int each: array) sum += Math.abs(each - res); 
        return sum;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine(); 

        in = in.substring(1, in.length() - 1);

        String[] stringNumbers = in.split(",");

        int[] ints = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            ints[i] = Integer.parseInt(stringNumbers[i].trim());
        }

        SolutionClone solutionClone = new SolutionClone(); 
        long cost = solutionClone.getMinimumCost(ints);

        System.out.println(cost);
        sc.close();
    }
}