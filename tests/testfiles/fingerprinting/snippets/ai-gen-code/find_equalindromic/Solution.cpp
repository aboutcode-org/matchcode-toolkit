#include <vector>
#include <algorithm>
#include <string>
#include <complex>
#include <climits>
#include <iostream>
#include <sstream>
using namespace std;    

class Solution {
public:
    long long minimumCost(vector<int>& nums) {
        long long ans = LONG_MAX, median = 0, n = nums.size();
        sort(nums.begin(), nums.end());
        median = (n %2 )?nums[n/2]: (nums[n/2] + nums[n/2 - 1])/2;
        //Find out possible palindroms
        vector<long long> pal;
        string t = to_string(median);
        pal.push_back(pow(10, t.size()-1) - 1); //case 1 : next palindrom with one digit less
        pal.push_back(pow(10, t.size()) + 1);   //case 2: next palindrom with one digit more
        //case 3: palidrom with just mirror image of the left half
        for(int i = 0; i < t.size()/2; ++i) t[t.size() - i - 1] = t[i];
        pal.push_back(stoll(t));
        //case 4: palidrom with mirror image of the left half with left half + 1
        string pal_next = to_string(stoi(t.substr(0,(t.size()+1)/2)) + 1);
        string temp = pal_next;
        if(t.size()%2) pal_next = pal_next.substr(0, pal_next.size() - 1);
        reverse(pal_next.begin(), pal_next.end());
        pal.push_back(stoll(temp + pal_next));
        //case 5: palidrom with mirror image of the left half with left half - 1
        string pal_prev = to_string(stoi(t.substr(0,(t.size()+1)/2)) - 1);
        temp = pal_prev;
        if(t.size()%2)  pal_prev = pal_prev.substr(0, pal_prev.size() - 1);
        reverse(pal_prev.begin(), pal_prev.end());
        pal.push_back(stoll(temp + pal_prev));
        //find the cost with each palindrom and take the min as answer
        for(auto p: pal){
        long long sum = 0;
        for(auto num: nums) sum += abs(num - p);
        ans = min(ans, sum);
        }
        return ans;
    }
};

int main() {
    std::string line;
    std::getline(std::cin, line); // Read the input line
    
    // Remove the square brackets
    line = line.substr(1, line.size() - 2);

    std::vector<int> nums;
    std::stringstream ss(line);
    std::string item;

    // Parse the integers from the string
    while (std::getline(ss, item, ',')) {
        nums.push_back(std::stoi(item));
    }

    // Instantiate the Solution class and call the minimumCost method
    Solution solution;
    long long cost = solution.minimumCost(nums);

    // Print the result
    std::cout << cost << std::endl;

    return 0;
}