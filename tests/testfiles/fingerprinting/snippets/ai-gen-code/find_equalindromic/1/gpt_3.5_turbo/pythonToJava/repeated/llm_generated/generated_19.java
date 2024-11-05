import java.util.*;
import org.python.core.PyFloat;
import org.python.core.PyInteger;
import org.python.util.PythonInterpreter;

public class Solution {
    public int minimumCost(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();

        PythonInterpreter pythonInterpreter = new PythonInterpreter();

        pythonInterpreter.exec("def pal(n):\n" +
                "    return str(n) == str(n)[::-1]\n");

        pythonInterpreter.exec("def check(n, nums):\n" +
                "    res = 0\n" +
                "    for a in range(n, 0, -1):\n" +
                "        if pal(a):\n" +
                "            res=sum(abs(it -a) for it in nums)\n" +
                "            break\n" +
                "    for b in range(n, 1000000000):\n" +
                "        if pal(b):\n" +
                "            res = Math.min(res, sum(Math.abs(it -b) for it in nums))\n" +
                "            break\n" +
                "    return res");

        pythonInterpreter.exec("def main(nums):\n" +
                "    n = len(nums)\n" +
                "    return min(check(nums[n/2], nums), check(nums[n/2 - 1], nums))");

        pythonInterpreter.execfile("Solution.java");

        int result = ((PyInteger) pythonInterpreter.get("main").__call__(new PyList(nums))).asInt();
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Integer> nums = new ArrayList<>();
        String[] inputArray = input.split(",");
        for (String s : inputArray) {
            nums.add(Integer.parseInt(s.trim()));
        }

        Solution solution = new Solution();
        int cost = solution.minimumCost(nums);

        System.out.println(cost);
    }
}