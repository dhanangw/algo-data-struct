# Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

# Example:

# Input:  [1,2,3,4]
# Output: [24,12,8,6]
# Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

# Note: Please solve it without division and in O(n).
# Follow up:
# Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

class Solution:
    def productExceptSelfWithoutFollowUp(self, nums: List[int]) -> List[int]:
        list_before = [0] * len(nums)
        list_after = [0] * len(nums)
        answer = [0] * len(nums)
        
        list_before[0] = 1
        for i in range(1, len(nums)):
            list_before[i] = nums[i-1] * list_before[i-1]

        list_after[len(nums)-1] = 1
        for i in range(len(nums)-2, -1, -1):
            list_after[i] = nums[i+1] * list_after[i+1]
        
        for i in range(0, len(nums)):
            answer[i] = list_before[i] * list_after[i]
        
        return answer
            