Here is a Java semantic code clone of the provided Python code:

```java

import java.util.*;

class Solution {
  public int minimumCost(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length;

    List<Integer> lim = new ArrayList<>(Arrays.asList(nums[n / 2], nums[n / 2 - 1]));

    int ret = Integer.MAX_VALUE;
    for (int m : lim) {
      int min = m;
      while (!isPalindrome(min)) {
        min--;
      }
      int sum = 0;
      for (int num : nums) {
        sum += Math.abs(num - min);
      }
      ret = Math.min(ret, sum);
      int max = m;
      while (!isPalindrome(max)) {
        max++;
      }
      sum = 0;
      for (int num : nums) {
        sum += Math.abs(num - max);
      }
      ret = Math.min(ret, sum);
    }

    return ret;
  }

  private boolean isPalindrome(int x) {
    if (x < 0) return false;
    int div = 1;
    while (x / div >= 10) {
      div *= 10;
    }
    while (x > 0) {
      int left = x / div;
      int right = x % 10;
      if (left != right) return false;
      x = (x % div) / 10;
      div /= 100;
    }
    return true;
  }
}

public class Main {
  public static void main(String[] args)  {
    Scanner scanner = new Scanner(System.in);

    // Convert the input string to an array    
    int[] nums = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

    // Create an instance of the Solution class
    Solution solution = new Solution();

    // Call the minimumCost method and store the result
    int cost = solution.minimumCost(nums);

    // Print the result
    System.out.println(cost);
  }
}
```