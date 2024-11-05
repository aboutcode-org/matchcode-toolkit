import java.util.*;

class MinCost {
    public long getMinCost(int[] elements) {
        int size = elements.length;
        Arrays.sort(elements);
        int median = elements[size/2];
        int increase = median;
        int decrease = median;

        while(!palindromeCheck(increase)) increase++; 
        while(!palindromeCheck(decrease)) decrease--;

        return Math.min(costCalculation(elements,increase), costCalculation(elements,decrease));
    }

    public boolean palindromeCheck(int num) {
        int remainder, sum = 0, tempVar;
        tempVar = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return tempVar == sum;
    }

    public long costCalculation(int[] elements, int ref) {
        long val = 0;
        for (int ele: elements) val += Math.abs(ele - ref);
        return val;
    }
    public static void main(String[] argc) {
        Scanner sc = new Scanner(System.in);
        String dataInput = sc.nextLine();

        dataInput = dataInput.substring(1, dataInput.length() - 1);

        String[] strArray = dataInput.split(",");

        int[] intArray = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i].trim());
        }

        MinCost minCostObj = new MinCost();
        long costResult = minCostObj.getMinCost(intArray);

        System.out.println(costResult);
        sc.close();
    }
}