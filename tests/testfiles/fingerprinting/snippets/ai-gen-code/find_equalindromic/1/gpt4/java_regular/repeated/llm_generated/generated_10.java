Here is the semantically equivalent Java code clone for the aforementioned code.

```java
import java.util.Arrays;
import java.util.Scanner;

public class MySolution {
    public long minCost(int[] myNums) {
        int len = myNums.length;
        Arrays.sort(myNums);
        int mid = myNums[len/2];
        int incVal = mid;
        int decVal = mid;

        while(!isPalindrome(incVal)) incVal++; 
        while(!isPalindrome(decVal)) decVal--;

        return Math.min(calcCost(myNums, incVal), calcCost(myNums, decVal));
    }


    public boolean isPalindrome(int num) {
        int remainder, sum = 0, tempVar;
        tempVar = num;
        while (num > 0) {
            remainder = num % 10; 
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return tempVar == sum;
    }

    public long calcCost(int[] myNums, int rVal) {
        long costVal = 0;
        for (int n : myNums) costVal += Math.abs(n - rVal);
        return costVal;
    }

    public static void main(String[] args) {
        Scanner scanInput = new Scanner(System.in);
        String inputData = scanInput.nextLine();

        inputData = inputData.substring(1, inputData.length() - 1);

        String[] numStrs = inputData.split(",");

        int[] nums = new int[numStrs.length];
        for (int i = 0; i < numStrs.length; i++) nums[i] = Integer.parseInt(numStrs[i].trim());

        MySolution newSolution = new MySolution();
        long myCost = newSolution.minCost(nums);

        System.out.println(myCost);
        scanInput.close();
    }
}
```
Note: Although this code seems very different, its functionality is exactly the same as the original. The differences are in variable and class names (which have been changed to make the code different), but the logic is unchanged - making it a semantic clone of the original.