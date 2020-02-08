// Largest Number At Least Twice of Others

// In a given integer array nums, there is always exactly one largest element.
// Find whether the largest element in the array is at least twice as much as every other number in the array.
// If it is, return the index of the largest element, otherwise return -1.

// Note:

// nums will have a length in the range [1, 50].
// Every nums[i] will be an integer in the range [0, 99].

class Solution {
    public int dominantIndex(int[] nums) {
        // 1. find the largest number, and it's index
        int maxValue = 0;
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                maxIndex = i;
            }
        }

        System.out.println("maxValue: " + maxValue);
        System.out.println("maxIndex: " + maxIndex);

        // 2. compare with other number in array.
        // 2.1 if largest number is not at least as twice as the element, then print -1
        // 2.2 if every element is at least as twice as the element, return index
        boolean valid = true;
        for (int i = 0; i < nums.length; i++) {
            if (i == maxIndex) {
                continue;
            }

            if ((nums[i] * 2) <= maxValue) {
                continue;
            } else {
                valid = false;
            }

        }

        if (valid) {
            return maxIndex;
        } else {
            return -1;
        }
    }
}