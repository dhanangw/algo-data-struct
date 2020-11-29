"""
485. Max Consecutive Ones

Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
"""
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        max_window = 0
        current_window = 0
        for num in nums:
            if num == 1:
                current_window += 1;
                if max_window < current_window:
                    max_window += 1
            elif num == 0:
                current_window = 0;
        return max_window
                
        