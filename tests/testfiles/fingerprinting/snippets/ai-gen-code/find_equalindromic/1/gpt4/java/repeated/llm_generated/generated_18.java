import java.util.Scanner;
import java.util.Arrays;
class NewSolution {
    public long getMinimumCost(int[] arr) {
        int size = arr.length;
        Arrays.sort(arr);
        int med = arr[size/2];
        int add = med;
        int sub = med;
        while(!isPalindrome(add)) add++; 
        while(!isPalindrome(sub)) sub--;  
        return Math.min(computeCost(arr,add), computeCost(arr,sub));
    }

    public boolean isPalindrome(int number) {  
        int remainder, sum = 0, temp;
        temp = number;
        while (number > 0) {
            remainder = number % 10; 
            sum = (sum * 10) + remainder;
            number = number / 10; 
        }
        return temp == sum;
    }

    public long computeCost(int[] arr, int m) {  
        long cost = 0;
        for (int i: arr) cost += Math.abs(i - m);
        return cost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine(); 
        in = in.substring(1, in.length() - 1);
        String[] strNumbers = in.split(",");
        int[] nums = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            nums[i] = Integer.parseInt(strNumbers[i].trim());
        }
        NewSolution newSolution = new NewSolution();
        long z = newSolution.getMinimumCost(nums);
        System.out.println(z);
        sc.close();
    }
}