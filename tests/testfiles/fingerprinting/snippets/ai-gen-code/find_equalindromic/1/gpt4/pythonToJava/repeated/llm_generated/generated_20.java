import java.util.*;

public class Solution {

    public int minimumCost(List<Integer> nums){
        Collections.sort(nums);
        int n=nums.size();

        PalindromeInteger pal=new PalindromeInteger();
        minimumDifference minDiff=new minimumDifference();
        
        return Math.min(minDiff.check(nums.get(n/2), nums, n, pal),minDiff.check(nums.get(n/2 - 1), nums, n, pal));
    }

    class PalindromeInteger{
        boolean palindrome(int n){
            String t=Integer.toString(n);
            return t.equals(new StringBuilder(t).reverse().toString());
        }  
    }

    class minimumDifference{
        int check(int n, List<Integer> nums, int size, PalindromeInteger pal){
            int res=0;
            for(int a=n;a>0;a--){
                if(pal.palindrome(a)){
                    res=sumDiff(a, nums, size);
                    break;
                }
            }
            for(int b=n;b<Math.pow(10,9);b++){
                if(pal.palindrome(b)){
                    res=Math.min(res,sumDiff(b, nums, size));
                    break;
                }
            }
            return res;
        }

        int sumDiff(int k, List<Integer> nums, int size){
            int res=0;
            for(int it=0;it<size;it++)
                res+=Math.abs(nums.get(it)-k);
            return res;
        }

    }

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String str=scanner.next();
        List<Integer> nums=new ArrayList<Integer>();
        for(String s:str.split(",")){
            nums.add(Integer.parseInt(s.trim()));
        }
        Solution obj= new Solution();
        System.out.println(obj.minimumCost(nums));
    }
}