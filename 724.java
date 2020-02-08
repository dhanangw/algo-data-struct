// FIND PIVOT INDEX

// Given an array of integers nums, write a method that returns the "pivot" index of this array.
// We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.
// If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.

// Note:

// The length of nums will be in the range [0, 10000].
// Each element nums[i] will be an integer in the range [-1000, 1000].

class Solution {
    public int pivotIndex(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println("index: " + i);
            int sumBefore = sumBeforePivot(nums, i);
            int sumAfter = sumAfterPivot(nums, i);
            System.out.println("sumBefore: " + sumBefore);
            System.out.println("sumAfter: " + sumAfter);
            System.out.println("\n");

            if (sumBefore == sumAfter) {
                return i;
            }
        }
        return -1;
    }

    public int sumBeforePivot(int[] nums, int pivot) {
        if (pivot == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < pivot; i++) {
            sum = sum + nums[i];
        }
        return sum;
    }

    public int sumAfterPivot(int[] nums, int pivot) {
        int sum = 0;
        for (int i = pivot + 1; i <= (nums.length - 1); i++) {
            sum = sum + nums[i];
        }
        return sum;
    }
}