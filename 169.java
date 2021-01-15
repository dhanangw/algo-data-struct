/** Majority Element
 * 
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 */

 class Solution {
    public int majorityElement(int[] nums) {
        return getMajority(nums, 0 , nums.length - 1);       
    }
    
    private int getMajority(int[] nums, int leftPoint, int rightPoint){
        // base case: if array size is 1; return the only element as majority element;
        if (leftPoint == rightPoint){
            return nums[leftPoint];
        }
        
        // divide: divide the array into two sides: left and right.
        int midPoint = ((rightPoint - leftPoint) / 2) + leftPoint;
        
        int majorityLeft = getMajority(nums, leftPoint, midPoint);
        int majorityRight = getMajority(nums, midPoint + 1, rightPoint);
        
        // conquer: get count of each respective majority.
        int leftCount = getCount(nums, majorityLeft, leftPoint, midPoint);
        int rightCount = getCount(nums, majorityRight, midPoint + 1, rightPoint);
        
        // combine: return the side which have the most majorityElement count.
        if (leftCount > rightCount){
            return majorityLeft;
        } else {
            return majorityRight;
        }
    }
    
    private int getCount(int[] nums, int targetNumber, int leftPoint, int rightPoint){
        // get count of targetNumber in nums[leftPoint:rightPoint];
        int count = 0;
        for (int i = leftPoint; i <= rightPoint; ++i){
            if(nums[i] == targetNumber){
                count += 1;
            }
        }
        return count;
    }
}

class Solution {
    public int maxSubArray(int[] nums) {
        // Use greedy approach
        int globalSum = nums[0];
        int localSum = nums[0];
        
        for (int i = 1; i < nums.length; ++i){
            // Iterate through the array.
            // If current element is greater than previous element,
            // set beginning of maxSubArray to current element and count localSum.
            // Keeps adding element to localSum until we found element greater than localSum.
            // If we found element greater than localSum, re-initiate localSum.
            localSum = Math.max(nums[i], localSum + nums[i]);
            
            // If localSum greater than globalSum, set globalSum to localSum.
            globalSum = Math.max(localSum, globalSum);
        }
        
        return globalSum;
    }
}

class Solution {
    public int maxSubArray(int[] nums) {
        // use dynamic programming: Kadane's algorithm
        int localSum = nums[0];
        int globalSum = nums[0];
        
        for(int i = 1; i < nums.length; ++i){
            // localSum in i = nums[i] + localSum for n-1.
            localSum = Math.max(nums[i], localSum + nums[i]);
            
            // keep track of localSum using globalSum.
            if (globalSum < localSum){
                globalSum = localSum;
            }
        }
        
        return globalSum;
    }
}