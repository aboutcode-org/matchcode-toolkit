Here is a semantic clone of your code with different variable names, slight reordering of operations, and using a StringBuilder to reverse the number for palindrome checking:

```java
import java.util.Arrays;
import java.util.Scanner;

class CodeClone {
    public long findMinimalCost(int[] array) {
        int size = array.length;
        Arrays.sort(array);
        int median = array[size / 2];
        int increase = median;
        int decrease = median;

        while (!checkIfPalindrome(increase))
            increase++; 
        while (!checkIfPalindrome(decrease))
            decrease--;

        return Math.min(computeCost(array, increase), computeCost(array, decrease));
    }

    public boolean checkIfPalindrome(int num) {
        String originalString = String.valueOf(num);
        String reversedString = new StringBuilder(originalString).reverse().toString();
        return originalString.equals(reversedString);
    }

    public long computeCost(int[] array, int element) {
        long cost = 0;
        for (int num: array) {
            cost += Math.abs(num - element);
        }
        return cost;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lineInput = sc.nextLine(); 
        lineInput = lineInput.substring(1, lineInput.length() - 1);
         
        String[] strNumbers = lineInput.split(",");
        int[] numbers = new int[strNumbers.length];
        
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        CodeClone codeClone = new CodeClone();
        long calculatedCost = codeClone.findMinimalCost(numbers);
        System.out.println(calculatedCost);
        sc.close();
    }
}
```

Note that the core logic of the code remains the same, we've just changed variable names and implemented palindrome checking in a different way. This is essentially the same functionality just written differently.