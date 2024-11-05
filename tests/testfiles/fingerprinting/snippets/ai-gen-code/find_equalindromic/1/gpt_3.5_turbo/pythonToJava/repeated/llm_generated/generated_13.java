```java
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int minimumCost(List<Integer> nums) {
        Integer[] numsArray = nums.toArray(new Integer[nums.size()]);
        Arrays.sort(numsArray);
        int n = numsArray.length;

        boolean pal(int n) {
            String t = String.valueOf(n);
            return t.equals(new StringBuilder(t).reverse().toString());
        }

        int check(int n) {
            int res = 0;
            for (int a = n; a > 0; a--) {
                if (pal(a)) {
                    for (int it : numsArray) {
                        res += Math.abs(it - a);
                    }
                    break;
                }
            }
            for (int b = n; b < Math.pow(10, 9); b++) {
                if (pal(b)) {
                    int temp = 0;
                    for (int it : numsArray) {
                        temp += Math.abs(it - b);
                    }
                    res = Math.min(res, temp);
                    break;
                }
            }
            return res;
        }

        return Math.min(check(numsArray[n/2]), check(numsArray[n/2-1]));
    }

    public static void main(String[] args) {
        String inputStr = new java.util.Scanner(System.in).nextLine();
        List<Integer> nums = Arrays.stream(inputStr.replace("[", "").replace("]", "").split(", "))
                .map(Integer::parseInt)
                .toList();

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}
```
```