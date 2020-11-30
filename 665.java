/**
 * Given an array nums with n integers, your task is to check if it could become
 * non-decreasing by modifying at most 1 element.
 * 
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for
 * every i (0-based) such that (0 <= i <= n - 2).
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [4,2,3] Output: true Explanation: You could modify the first 4
 * to 1 to get a non-decreasing array. 
 * 
 * Example 2:
 * 
 * Input: nums = [4,2,1] Output: false Explanation: You can't get a
 * non-decreasing array by modify at most one element. *
 */
class Solution {
    // taken from
    // https://leetcode.com/problems/non-decreasing-array/discuss/106826/JavaC++-Simple-greedy-like-solution-with-explanation
    public boolean checkPossibility(int[] nums) {
        int count = 0;

        for (int i = 1; i < nums.length && count <= 1; i++) {
            if (nums[i - 1] > nums[i]) {
                count += 1;
                if (i - 2 < 0 || nums[i - 2] <= nums[i]) {
                    nums[i - 1] = nums[i];
                } else {
                    nums[i] = nums[i - 1];
                }
            }
        }
        return count <= 1;
    }
}