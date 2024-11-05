from typing import List
import ast
class Solution:
    def minimumCost(self, nums: List[int]) -> int:
        
        nums.sort()
        n=len(nums)

        def pal(n):            
            t=str(n)
            return t==t[::-1]
        
        def check(n):
            for a in range(n,0,-1):
                if pal(a):
                    res=sum(abs(it -a) for it in nums)
                    break
            for b in range(n,10**9):
                if pal(b):
                    res=min(res,sum(abs(it -b) for it in nums))
                    break
            return res


        return min(check(nums[n//2]),check(nums[n//2-1]))

def main():
    # Read the input string
    input_str = input("")
    
    # Convert the input string to a Python list
    nums = ast.literal_eval(input_str)
    
    # Create an instance of the Solution class
    solution = Solution()
    
    # Call the minimumCost method and store the result
    cost = solution.minimumCost(nums)
    
    # Print the result
    print(cost)

if __name__ == "__main__":
    main()