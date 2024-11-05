Here is a semantic clone of the above code. The functionality remains the same, but the structure and variable names are different.

```java
import java.util.Arrays;
import java.util.Scanner;

class Main {
    public long getMinCost(int[] arr) {
        int size = arr.length;
        Arrays.sort(arr);
        int mid = arr[size/2];
        int up = mid;
        int down = mid;

        while(!isPalindrome(up)) up++;
        while(!isPalindrome(down)) down--;

        return Math.min(findCost(arr,up), findCost(arr,down));
    }

    public boolean isPalindrome(int num) {
        int remainder, total = 0, temporary;
        temporary = num;
        while (num > 0) {
            remainder = num % 10;
            total = (total * 10) + remainder;
            num = num / 10;
        }
        return temporary == total;
    }

    public long findCost(int[] arr, int ref) {
        long cost = 0;
        for (int i: arr) cost += Math.abs(i - ref);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        inputLine = inputLine.substring(1, inputLine.length() - 1);
        String[] strNumbers = inputLine.split(",");

        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i].trim());
        }

        Main main = new Main();
        long cost = main.getMinCost(numbers);

        System.out.println(cost);
        scanner.close();
    }
}
```