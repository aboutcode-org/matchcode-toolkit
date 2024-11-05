Below is the semantic clone of the given code in Java:

```Java
import java.math.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

class Solution {
    long minimumCost(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        long median = (n % 2 == 0) ? (nums[n / 2] + nums[n / 2 - 1]) / 2 : nums[n / 2];

        List<Long> pals = new ArrayList<>();
        String t = Long.toString(median);
        pals.add((long) Math.pow(10, t.length() - 1) - 1);
        pals.add((long) Math.pow(10, t.length()) + 1);

        for (int i = 0; i < t.length() / 2; ++i)
            t = t.substring(0, t.length() - i - 1) + t.charAt(i) + t.substring(t.length() - i);

        pals.add(Long.parseLong(t));
        String pal_next = Long.toString(Long.parseLong(t.substring(0, (t.length() + 1) / 2)) + 1);
        String temp = pal_next;

        if (t.length() % 2 != 0) pal_next = pal_next.substring(0, pal_next.length() - 1);
        StringBuilder sb = new StringBuilder(pal_next);
        pal_next = sb.reverse().toString();

        pals.add(Long.parseLong(temp + pal_next));

        String pal_prev = Long.toString(Long.parseLong(t.substring(0, (t.length() + 1) / 2)) - 1);
        temp = pal_prev;
        if (t.length() % 2 != 0) pal_prev = pal_prev.substring(0, pal_prev.length() - 1);

        sb = new StringBuilder(pal_prev);
        pal_prev = sb.reverse().toString();

        pals.add(Long.parseLong(temp + pal_prev));

        AtomicLong ans = new AtomicLong(Long.MAX_VALUE);

        pals.forEach(p -> {
            long sum = 0;
            for (int num : nums) sum += Math.abs(num - p);
            ans.set(Math.min(ans.get(), sum));
        });

        return ans.get();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> nums = new ArrayList<>();

        // Parsing the comma separated inputs
        String line = scanner.nextLine();
        Scanner lineScanner = new Scanner(line);

        lineScanner.useDelimiter(",");
        while (lineScanner.hasNext()) {
            nums.add(lineScanner.nextInt());
        }
        lineScanner.close();

        Solution solution = new Solution();
        System.out.println(solution.minimumCost(nums.stream().mapToInt(i -> i).toArray()));
    }
}
```

Note: This Java code will provide the same output as the C++ code for the same input.