import java.util.Arrays;
import java.util.Vector;

class Solution {
    public long minimumCost(Vector<Integer> nums) {
        long ans = Long.MAX_VALUE;
        long median = 0;
        int n = nums.size();
        Integer[] numsArray = nums.toArray(new Integer[0]);
        Arrays.sort(numsArray);
        median = (n % 2 == 1) ? numsArray[n/2] : (numsArray[n/2] + numsArray[n/2 - 1]) / 2;

        //Find out possible palindroms
        Vector<Long> pal = new Vector<Long>();
        String t = String.valueOf(median);
        pal.add((long) (Math.pow(10, t.length()-1) - 1)); //case 1 : next palindrom with one digit less
        pal.add((long) (Math.pow(10, t.length()) + 1));   //case 2: next palindrom with one digit more
        //case 3: palidrom with just mirror image of the left half
        char[] tCharArray = t.toCharArray();
        for(int i = 0; i < t.length()/2; ++i) tCharArray[t.length() - i - 1] = t.charAt(i);
        pal.add(Long.parseLong(String.valueOf(tCharArray)));
        //case 4: palidrom with mirror image of the left half with left half + 1
        String pal_next = String.valueOf(Integer.parseInt(t.substring(0,(t.length()+1)/2)) + 1);
        String temp = pal_next;
        if(t.length()%2 == 1) pal_next = pal_next.substring(0, pal_next.length() - 1);
        pal.add(Long.parseLong(temp + new StringBuilder(pal_next).reverse().toString()));
        //case 5: palidrom with mirror image of the left half with left half - 1
        String pal_prev = String.valueOf(Integer.parseInt(t.substring(0,(t.length()+1)/2)) - 1);
        temp = pal_prev;
        if(t.length()%2 == 1)  pal_prev = pal_prev.substring(0, pal_prev.length() - 1);
        pal.add(Long.parseLong(temp + new StringBuilder(pal_prev).reverse().toString()));
        //find the cost with each palindrom and take the min as answer
        for(long p: pal){
            long sum = 0;
            for(int num: numsArray) sum += Math.abs(num - p);
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine(); // Read the input line

        // Remove the square brackets
        line = line.substring(1, line.length() - 1);

        Vector<Integer> nums = new Vector<Integer>();
        String[] numsArray = line.split(",");
        for(String numStr : numsArray) {
            nums.add(Integer.parseInt(numStr.trim()));
        }

        // Instantiate the Solution class and call the minimumCost method
        Solution solution = new Solution();
        long cost = solution.minimumCost(nums);

        // Print the result
        System.out.println(cost);
    }
}